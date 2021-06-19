package ru.itis.javalab.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.Top3BazaarInfo;
import ru.itis.javalab.data.dto.Top3NpsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Qualifier("Top-3-properties")
    @Autowired
    protected Properties top3Properties;

    @Override
    public List<Top3BazaarInfo> getBazaarTop3() {
        List<Top3BazaarInfo> top3BazaarInfos = new ArrayList<>();
        top3BazaarInfos.add(Top3BazaarInfo.builder()
                .minionName(top3Properties.getProperty("top1bazaar"))
                .place(1)
                .profit(Double.valueOf(top3Properties.getProperty("top1bazaarProfit")))
                .build()
                );
        top3BazaarInfos.add(Top3BazaarInfo.builder()
                .minionName(top3Properties.getProperty("top2bazaar"))
                .place(2)
                .profit(Double.valueOf(top3Properties.getProperty("top2bazaarProfit")))
                .build()
        );
        top3BazaarInfos.add(Top3BazaarInfo.builder()
                .minionName(top3Properties.getProperty("top3bazaar"))
                .place(3)
                .profit(Double.valueOf(top3Properties.getProperty("top3bazaarProfit")))
                .build()
        );
        return top3BazaarInfos;
    }

    @Override
    public List<Top3NpsInfo> getNpsTop3() {
        List<Top3NpsInfo> top3NpsInfos = new ArrayList<>();
        top3NpsInfos.add(Top3NpsInfo.builder()
                .minionName(top3Properties.getProperty("top1nps"))
                .place(1)
                .profit(Double.valueOf(top3Properties.getProperty("top1npsProfit")))
                .build()
        );
        top3NpsInfos.add(Top3NpsInfo.builder()
                .minionName(top3Properties.getProperty("top2nps"))
                .place(2)
                .profit(Double.valueOf(top3Properties.getProperty("top2npsProfit")))
                .build()
        );
        top3NpsInfos.add(Top3NpsInfo.builder()
                .minionName(top3Properties.getProperty("top3nps"))
                .place(3)
                .profit(Double.valueOf(top3Properties.getProperty("top3npsProfit")))
                .build()
        );
        return top3NpsInfos;
    }

}
