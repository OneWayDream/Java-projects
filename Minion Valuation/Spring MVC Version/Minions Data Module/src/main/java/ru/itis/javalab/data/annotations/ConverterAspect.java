package ru.itis.javalab.data.annotations;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

import java.util.List;

public class ConverterAspect {

    public static void handle(FormatterRegistry registry, List<Object> classes){
        for (Object o : classes) {
            registry.addConverter((Converter) o);
        }
    }
}
