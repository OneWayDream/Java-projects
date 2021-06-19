package ru.itis.javalab.data.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.data.dto.BazaarItemDto;
import ru.itis.javalab.data.exceptions.HypixelGetDataException;
import ru.itis.javalab.data.exceptions.JsonParsingException;

@Component
public class HypixelDataUpdateService {

    @Value("${hypixel.api_key}")
    protected String apiKey;

    @Value("${hypixel.bazaar_update_url}")
    protected String bazaarUpdateUrl;

    @Value("${hypixel.key_parameter_name}")
    protected String keyParamName;

    protected OkHttpClient client = new OkHttpClient();

    protected JSONObject jsonObject;

    public void updateData(){
        try{
            Request accessTokenRequest = new Request.Builder()
                    .url(bazaarUpdateUrl + "?" + keyParamName + apiKey)
                    .build();
            Response response = client.newCall(accessTokenRequest).execute();
            jsonObject = new JSONObject(response.body().string()).getJSONObject("products");
        } catch (Exception ex) {
            throw new HypixelGetDataException(ex);
        }
    }

    public BazaarItemDto getInfoByName(String name){
        try{
            JSONObject itemData = jsonObject.getJSONObject(name);
            Double sellPrice = itemData.getJSONArray("sell_summary").getJSONObject(0).getDouble("pricePerUnit");
            Double buyPrice = itemData.getJSONArray("buy_summary").getJSONObject(0).getDouble("pricePerUnit");
            return BazaarItemDto.builder()
                    .name(name)
                    .buyPrice(buyPrice)
                    .sellPrice(sellPrice)
                    .build();
        } catch (Exception ex){
            throw new JsonParsingException(ex);
        }
    }

}
