package ru.itis.utils;

import java.util.Arrays;

public class StringRefactor {

    public static String remakeString(String str){
        String result;
        if (str.contains("_")){
            result = Arrays.stream(str.split("_")).map(String::toLowerCase).reduce((s1,s2)->s1 +" "+ s2).orElse("");

        } else {
            result = str.toLowerCase();
        }
        return result.replaceFirst(result.charAt(0) + "", (result.charAt(0) + "").toUpperCase()).trim();
    }

}
