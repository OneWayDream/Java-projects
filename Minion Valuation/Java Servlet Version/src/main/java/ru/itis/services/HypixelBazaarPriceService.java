package ru.itis.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import org.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.exceptions.GetPricesDataException;

public class HypixelBazaarPriceService implements PricesService {

    protected  String connection_key;

    private static final Logger logger = LoggerFactory.getLogger(
            HypixelBazaarPriceService.class);

    public HypixelBazaarPriceService(String connection_key) {
        this.connection_key = connection_key;
    }

    public Pair<Double,Double> getPriceById(String id) throws GetPricesDataException {
        URLConnection urlConnection = null;
        InputStream in = null;
        try{
            URI uri = new URI("https://api.hypixel.net/skyblock/bazaar/product?key=" + connection_key +
                    "&productId=" + id);
            urlConnection = uri.toURL().openConnection();
            in = urlConnection.getInputStream();
            String data = new BufferedReader(new InputStreamReader(in,
                    StandardCharsets.UTF_8)).lines().reduce((s1,s2)->s1+s2).orElse("");
            JSONObject reader = new JSONObject(data);

            JSONObject productId = reader.getJSONObject("product_info").getJSONArray("sell_summary").getJSONObject(0);
            Double sell_price = productId.getDouble("pricePerUnit");
            productId = reader.getJSONObject("product_info").getJSONArray("buy_summary").getJSONObject(0);
            logger.info("For product " + id + " was successfully received bazaar prices.");
            Double buy_price = productId.getDouble("pricePerUnit");
            return new Pair<>(buy_price, sell_price);
        } catch (URISyntaxException | IOException ex){
            throw new GetPricesDataException(ex);
        } finally {
            if (in!=null){
                try{
                    in.close();
                } catch (IOException ex){
                    //ignore
                }
            }
        }
    }

    public List<String> getAllId() throws GetPricesDataException {
        URLConnection urlConnection = null;
        InputStream in = null;
        try{
            URI uri = new URI("https://api.hypixel.net/skyblock/bazaar/products?key=" + connection_key);
            urlConnection = uri.toURL().openConnection();
            in = urlConnection.getInputStream();
            String data = new BufferedReader(new InputStreamReader(in,
                    StandardCharsets.UTF_8)).lines().reduce((s1,s2)->s1+s2).orElse("");
            JSONObject reader = new JSONObject(data);
            JSONArray productIds = reader.getJSONArray("productIds");
            List<String> result = new ArrayList<>();
            for (int i = 0; i<productIds.length(); i++){
                result.add(productIds.getString(i));
            }
            logger.info("The list of all items was successfully received.");
            return result;
        } catch (URISyntaxException | IOException ex){
            throw new GetPricesDataException(ex);
        } finally {
            if (in!=null){
                try{
                    in.close();
                } catch (IOException ex){
                    //ignore
                }
            }
        }
    }
}
