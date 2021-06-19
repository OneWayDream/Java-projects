package ru.itis.javalab.data.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ru.itis.javalab.data.dto.MinionDto;
import ru.itis.javalab.data.exceptions.BazaarTop3UpdateException;
import ru.itis.javalab.data.services.MinionsService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class HypixelTop3Updater {

    @Autowired
    protected MinionsService minionsService;

    @Qualifier("Top-3-properties")
    @Autowired
    protected Properties properties;

    @Autowired
    protected MinionsProfitCalculator calculator;

    @Value("${hypixel.top3.filename}")
    protected String top3FileName;

    public void updateTop3Bazaar(){
        List<MinionDto> minions = minionsService.findAll();
        List<MinionResult> results = minions.stream()
                .map(minionDto -> new MinionResult(
                        minionDto,
                        calculator.calculate(minionDto, 11, null, null, null, false).getResult())
                )
                .sorted((a, b) -> b.getProfit().compareTo(a.getProfit()))
                .collect(Collectors.toList());
        properties.setProperty("top1bazaar", results.get(0).getMinionDto().getName());
        properties.setProperty("top1bazaarProfit", String.valueOf(results.get(0).getProfit().doubleValue()));
        properties.setProperty("top2bazaar", results.get(1).getMinionDto().getName());
        properties.setProperty("top2bazaarProfit", String.valueOf(results.get(1).getProfit().doubleValue()));
        properties.setProperty("top3bazaar", results.get(2).getMinionDto().getName());
        properties.setProperty("top3bazaarProfit", String.valueOf(results.get(2).getProfit().doubleValue()));
        try{
            properties.store(new FileOutputStream(ResourceUtils.getFile("classpath:" + top3FileName)), null);
        } catch (IOException ex){
            throw new BazaarTop3UpdateException(ex);
        }
    }

    @Data
    @AllArgsConstructor
    protected class MinionResult{
        protected MinionDto minionDto;
        protected Double profit;
    }

}
