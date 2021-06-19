package ru.itis.utils;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.exceptions.GetPricesDataException;
import ru.itis.exceptions.MinionsDataRepositoryException;
import ru.itis.models.Item;
import ru.itis.services.MinionsDataService;
import ru.itis.services.PricesService;

import javax.servlet.ServletContext;
import java.util.List;

public class MinionsBazaarPricesUpdater implements Runnable {

    protected MinionsDataService minionsDataService;
    protected boolean isWork;
    protected Long timeBetweenUpdates;
    protected ServletContext servletContext;
    protected PricesService pricesService;

    private static final Logger logger = LoggerFactory.getLogger(
            MinionsBazaarPricesUpdater.class);

    public MinionsBazaarPricesUpdater(MinionsDataService minionsDataService, PricesService pricesService, boolean isWork, Long timeBetweenUpdates, ServletContext servletContext){
        this.minionsDataService = minionsDataService;
        this.isWork = isWork;
        this.timeBetweenUpdates = timeBetweenUpdates;
        this.servletContext = servletContext;
        this.pricesService = pricesService;
    }

    @Override
    public void run() {
        try{
            while(isWork){
                List<Item> data= minionsDataService.getBazaarPricesList();
                for (Item item: data) {
                    Pair<Double, Double> pair = pricesService.getPriceById(item.getItemName().trim());
                    minionsDataService.updateBazaarPricesEntry(Item.builder()
                            .itemName(item.getItemName())
                            .buyBazaarPrice(pair.getKey())
                            .sellBazaarPrice(pair.getValue())
                            .build());
                    doSleep(1000L, false);
                    logger.info("Item " + item.getItemName() + " was successfully updated");
                }
                Top3Updater.updateData(minionsDataService.getAllMinions(), minionsDataService.getAllProductions(),
                        minionsDataService.getBazaarPricesList(), true, servletContext);
                servletContext.setAttribute("dataDownloadError", null);
                logger.info("Bazaar prices db was successfully updated. Updater is going to sleep for " + timeBetweenUpdates);
                this.doSleep(timeBetweenUpdates, true);
            }
        } catch (MinionsDataRepositoryException | GetPricesDataException ex){
            servletContext.setAttribute("dataDownloadError", true);
        } finally {
            if (isWork){
                this.doSleep(timeBetweenUpdates, true);
            } else {
                servletContext.setAttribute("dataDownloadError", true);
                logger.info("Updater got unexpected exception, but continue to work.");
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
            servletContext.setAttribute("dataDownloadError", true);
            logger.error("Updater stopped to work.");
        }
    }
}
