package ru.itis.javalab.data.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataUpdateInitializationBean implements InitializingBean {

    @Autowired
    protected HypixelDataUpdateUnit hypixelDataUpdateUnit;

    @Value("${hypixel.is_update_bazaar_data}")
    protected Boolean isUpdateBazaarData;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (isUpdateBazaarData){
            Thread thread = new Thread(hypixelDataUpdateUnit);
            thread.start();
        }
    }
}
