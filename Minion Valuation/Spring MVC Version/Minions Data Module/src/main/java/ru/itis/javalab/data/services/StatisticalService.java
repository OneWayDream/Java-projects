package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.Top3BazaarInfo;
import ru.itis.javalab.data.dto.Top3NpsInfo;

import java.util.List;

public interface StatisticalService {

    List<Top3BazaarInfo> getBazaarTop3();

    List<Top3NpsInfo> getNpsTop3();

}
