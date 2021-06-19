package ru.itis.services;


import javafx.util.Pair;
import ru.itis.exceptions.GetPricesDataException;

import java.util.List;

public interface PricesService {

   Pair<Double,Double> getPriceById(String id) throws GetPricesDataException;
   List<String> getAllId() throws GetPricesDataException;

}
