package ru.itis.javalab.data.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.data.dto.BazaarItemDto;
import ru.itis.javalab.data.services.BazaarItemsService;

import java.util.List;

@Component
@Slf4j
public class HypixelDataUpdateUnit implements Runnable{

    protected HypixelDataUpdateService hypixelDataUpdateService;
    protected BazaarItemsService bazaarItemsService;
    protected HypixelTop3Updater hypixelTop3Updater;
    protected Boolean isWork;

    @Value("${hypixel.update_time}")
    protected Long updateTime;

    public HypixelDataUpdateUnit(HypixelDataUpdateService hypixelDataUpdateService, BazaarItemsService bazaarItemsService,
                                 HypixelTop3Updater hypixelTop3Updater){
        this.hypixelDataUpdateService = hypixelDataUpdateService;
        this.bazaarItemsService = bazaarItemsService;
        this.hypixelTop3Updater = hypixelTop3Updater;
        this.isWork = true;
    }

    @Override
    public void run() {
        try{
            while(isWork){
                List<BazaarItemDto> data= bazaarItemsService.findAll();
                hypixelDataUpdateService.updateData();
                for (BazaarItemDto item: data) {
                    BazaarItemDto bazaarItemDto = hypixelDataUpdateService.getInfoByName(item.getName());
                    bazaarItemsService.update(bazaarItemDto);
                }
                hypixelTop3Updater.updateTop3Bazaar();
                log.info("Bazaar prices db was successfully updated. Updater is going to sleep for " + updateTime);
                this.doSleep(updateTime, true);
            }
        } catch (Exception ex){
            log.warn("Hypixel data updater got " + ex.getClass().getSimpleName() + " exception.");
        } finally {
            if (isWork){
                this.doSleep(updateTime, true);
            } else {
                log.info("Updater got unexpected exception, but continue to work.");
            }
        }
    }

    public void doSleep(Long time, boolean isEnd){
        try{
            Thread.sleep(time);
            if (isEnd){
                this.run();
            }
        } catch (InterruptedException e) {
            log.error("Updater stopped to work.");
        }
    }

    public void stop(){
        this.isWork = false;
    }
}
