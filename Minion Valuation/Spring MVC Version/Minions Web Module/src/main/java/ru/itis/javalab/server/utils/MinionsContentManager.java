package ru.itis.javalab.server.utils;

import com.squareup.okhttp.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.server.dto.main.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MinionsContentManager {

    @Value("{hypixel.top3.filename}")
    protected String top3FileName;

    @Value("${data_server.url}")
    protected String dataServerUrl;

    @Value("${data_server.refresh_token_url}")
    protected String refreshTokenUrl;

    @Value("${data_server.access_token_url}")
    protected String accessTokenUrl;

    @Value("${data_server.login_param_name}")
    protected String loginParamName;

    @Value("${data_server.password_param_name}")
    protected String passwordParamName;

    @Value("${data_server.refresh_token_response_param_name}")
    protected String refreshTokenResponseParamName;

    @Value("${data_server.refresh_token_param_name}")
    protected String refreshTokenParamName;

    @Value("${data_server.access_token_response_param_name}")
    protected String accessTokenResponseParamName;

    @Value("${data_server.access_token_header_name}")
    protected String accessTokenHeaderName;

    @Value("${data_server.minion_url}")
    protected String minionUrl;

    @Value("${data_server.fuel_url}")
    protected String fuelUrl;

    @Value("${data_server.upgrade_url}")
    protected String upgradeUrl;

    protected OkHttpClient client;

    @Value("${data_server.login}")
    protected String login;

    @Value("${data_server.password}")
    protected String password;

    @Value("${data_server.top_3_bazaar}")
    protected String top3BazaarUrl;

    @Value("${data_server.top_3_nps}")
    protected String top3NpsUrl;

    @Value("${data_server.update_top3_time}")
    protected Long top3UpdateTime;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    protected String refreshToken;
    protected String accessToken;

    //todo entities
    protected List<MinionCalculateData> minionsDataList;
    protected List<FuelCalculateData> fuelsDataList;
    protected List<UpgradeCalculateData> upgradesDataList;

    //todo entities
    protected List<Top3BazaarInfo> top3BazaarData;
    protected List<Top3NpsInfo> top3NpsData;

    protected Thread top3UpdateThread;

    protected Boolean isConnectionError;
    protected Boolean isUpdateTop3Error;

    protected Integer faultTries;

    @Data
    protected class Top3UpdateThread implements Runnable{

        protected MinionsContentManager minionsContentManager;
        protected Boolean isWork;

        public Top3UpdateThread(MinionsContentManager minionsContentManager){
            this.minionsContentManager = minionsContentManager;
            isWork = true;
        }

        @Override
        public void run() {
            while (isWork){
                updateBazaarTop3();
                updateNpsTop3();
                waitInfoUpdate();
            }
        }

        protected void updateBazaarTop3(){
            Response response = minionsContentManager.executeGetRequest(minionsContentManager.getTop3BazaarUrl());
            if ((response != null) && (response.isSuccessful())){
                try{
                    JSONArray jsonData = new JSONArray(response.body().string());
                    List<Top3BazaarInfo> result = new ArrayList<>();
                    for (int i = 0; i < jsonData.length(); i++){
                        JSONObject top3BazaarInfoDto = jsonData.getJSONObject(i);
                        result.add(Top3BazaarInfo.builder()
                                .minionName(getReadableString(top3BazaarInfoDto.getString("minionName")))
                                .place(top3BazaarInfoDto.getInt("place"))
                                .imageName(top3BazaarInfoDto.getString("minionName"))
                                .profit(top3BazaarInfoDto.getDouble("profit"))
                                .build());
                    }
                    result = result.stream().sorted(Comparator.comparingInt(Top3BazaarInfo::getPlace)).collect(Collectors.toList());
                    minionsContentManager.top3BazaarData = result;
                } catch (Exception ex){
                    minionsContentManager.isConnectionError = true;
                }
            } else {
                boolean b = fixTokens();
                if ((b)&&(faultTries<=5)){
                    faultTries++;
                    updateBazaarTop3();
                } else {
                    minionsContentManager.isConnectionError = true;
                }
            }
        }

        protected void updateNpsTop3(){
            Response response = minionsContentManager.executeGetRequest(minionsContentManager.getTop3NpsUrl());
            if ((response != null) && (response.isSuccessful())){
                try{
                    JSONArray jsonData = new JSONArray(response.body().string());
                    List<Top3NpsInfo> result = new ArrayList<>();
                    for (int i = 0; i < jsonData.length(); i++){
                        JSONObject top3NpsInfoDto = jsonData.getJSONObject(i);
                        result.add(Top3NpsInfo.builder()
                                .minionName(getReadableString(top3NpsInfoDto.getString("minionName")))
                                .place(top3NpsInfoDto.getInt("place"))
                                .imageName(top3NpsInfoDto.getString("minionName"))
                                .profit(top3NpsInfoDto.getDouble("profit"))
                                .build());
                    }
                    result = result.stream().sorted(Comparator.comparingInt(Top3NpsInfo::getPlace)).collect(Collectors.toList());
                    minionsContentManager.top3NpsData = result;
                } catch (Exception ex){
                    minionsContentManager.isConnectionError = true;
                }
            } else {
                boolean b = fixTokens();
                if ((b)&&(faultTries<=5)){
                    faultTries++;
                    updateNpsTop3();
                } else {
                    minionsContentManager.isConnectionError = true;
                }
            }
        }

        protected void waitInfoUpdate(){
            try{
                Thread.sleep(minionsContentManager.getTop3UpdateTime());
            } catch (InterruptedException e) {
                isWork = false;
            }
        }

    }

    public void init(){

        client = new OkHttpClient();
        isConnectionError = false;

        getRefreshToken();
        getAccessToken();

        top3UpdateThread = new Thread(new Top3UpdateThread(this));
    }

    public void updateMinionsInfo(){
        updateMinionsList();
        updateFuelsList();
        updateUpgradesList();
    }

    public void startUpdateTop3Thread(){
        top3UpdateThread.start();
    }

    public void stopUpdateTop3Thread(){
        top3UpdateThread.interrupt();
    }

    public boolean getUpdateTop3Thread(){
        return top3UpdateThread.isAlive();
    }

    protected void getRefreshToken(){
        try{
            JSONObject body = new JSONObject()
                    .put(loginParamName, login)
                    .put(passwordParamName, password);
            RequestBody requestBody = RequestBody.create(JSON, body.toString());
            Request request = new Request.Builder()
                    .url(dataServerUrl + refreshTokenUrl)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject jsonData = new JSONObject(response.body().string());
            refreshToken = jsonData.getString(refreshTokenResponseParamName);
            isConnectionError = false;
        } catch (Exception ex) {
            isConnectionError = true;
        }
    }

    protected void getAccessToken(){
        try{
            JSONObject body = new JSONObject()
                    .put(refreshTokenParamName, refreshToken);
            RequestBody requestBody = RequestBody.create(JSON, body.toString());
            Request request = new Request.Builder()
                    .url(dataServerUrl + accessTokenUrl)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject jsonData = new JSONObject(response.body().string());
            accessToken = jsonData.getString(accessTokenResponseParamName);
            isConnectionError = false;
        } catch (Exception ex) {
            isConnectionError = true;
        }
    }

    protected void updateMinionsList(){
        Response response = executeGetRequest(minionUrl);
        if ((response != null) && (response.isSuccessful())){
            try{
                JSONArray jsonData = new JSONArray(response.body().string());
                ArrayList<MinionCalculateData> result = new ArrayList<>();
                for (int i = 0; i < jsonData.length(); i++){
                    JSONObject minionDto = jsonData.getJSONObject(i);
                    result.add(MinionCalculateData.builder()
                            .name(getReadableString(minionDto.getString("name")))
                            .optionValue(minionDto.getString("name"))
                            .imageName(minionDto.getString("name"))
                            .build());
                }
                minionsDataList = result;
            } catch (Exception ex){
                isConnectionError = true;
            }
        } else {
            boolean b = fixTokens();
            if ((b)&&(faultTries<=5)){
                faultTries++;
            } else {
                isConnectionError = true;
            }
        }
    }

    protected void updateFuelsList(){
        Response response = executeGetRequest(fuelUrl);
        if ((response != null) && (response.isSuccessful())){
            try{
                JSONArray jsonData = new JSONArray(response.body().string());
                ArrayList<FuelCalculateData> result = new ArrayList<>();
                for (int i = 0; i < jsonData.length(); i++){
                    JSONObject fuelDto = jsonData.getJSONObject(i);
                    result.add(FuelCalculateData.builder()
                            .name(getReadableString(fuelDto.getString("name")))
                            .optionValue(fuelDto.getString("name"))
                            .imageName(fuelDto.getString("name"))
                            .build());
                }
                fuelsDataList = result;
            } catch (Exception ex){
                isConnectionError = true;
            }
        } else {
            boolean b = fixTokens();
            if ((b)&&(faultTries<=5)){
                faultTries++;
            } else {
                isConnectionError = true;
            }
        }
    }

    protected void updateUpgradesList(){
        Response response = executeGetRequest(upgradeUrl);
        if ((response != null) && (response.isSuccessful())){
            try{
                JSONArray jsonData = new JSONArray(response.body().string());
                ArrayList<UpgradeCalculateData> result = new ArrayList<>();
                for (int i = 0; i < jsonData.length(); i++){
                    JSONObject minionDto = jsonData.getJSONObject(i);
                    result.add(UpgradeCalculateData.builder()
                            .name(getReadableString(minionDto.getString("name")))
                            .optionValue(minionDto.getString("name"))
                            .imageName(minionDto.getString("name"))
                            .build());
                }
                upgradesDataList = result;
            } catch (Exception ex){
                isConnectionError = true;
            }
        } else {
            boolean b = fixTokens();
            if ((b)&&(faultTries<=5)){
                faultTries++;
            } else {
                isConnectionError = true;
            }
        }
    }

    protected Response executeGetRequest(String url){
        try{
            Request request = new Request.Builder()
                    .url(dataServerUrl + url)
                    .header(accessTokenHeaderName, accessToken)
                    .build();
            return client.newCall(request).execute();
        } catch (Exception ex){
            isConnectionError = true;
            return null;
        }
    }

    protected boolean fixTokens(){
        getAccessToken();
        if (isConnectionError){
            getRefreshToken();
            if (isConnectionError) {
                return false;
            } else {
                getAccessToken();
                return !isConnectionError;
            }
        } else {
            return true;
        }
    }

    protected String getReadableString(String str){
        String result;
        if (str.contains("_")){
            result = Arrays.stream(str.split("_")).map(String::toLowerCase).reduce((s1, s2)->s1 +" "+ s2).orElse("");
        } else {
            result = str.toLowerCase();
        }
        return result.replaceFirst(result.charAt(0) + "", (result.charAt(0) + "").toUpperCase()).trim();
    }

}
