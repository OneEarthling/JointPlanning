package com.example.jointplanning.util;

public class LongUtil {
    /**
     * Преобразование в long
     * @param value значение
     * @return возвращается long
     */
    public static long convertToLong(Object value){
        return Long.parseLong(String.valueOf(value));
    }
}

