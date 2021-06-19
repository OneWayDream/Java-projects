package ru.itis.javalab.data.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.data.repositories.MinionUpgradesRepository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
@Component
public class TimeBetweenActionsConverter implements AttributeConverter<List<Double>, String> {

    @Autowired
    protected MinionUpgradesRepository upgradesRepository;

    @Override
    public String convertToDatabaseColumn(List<Double> attribute) {
        StringBuilder result = new StringBuilder();
        for (Double d : attribute){
            result.append(d).append(";");
        }
        return result.toString();
    }

    @Override
    public List<Double> convertToEntityAttribute(String dbData) {
        if ((dbData != null) && (!dbData.equals(""))){
            return Arrays.stream(dbData.split(";")).map(Double::valueOf).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
