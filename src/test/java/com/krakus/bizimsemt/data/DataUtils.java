package com.krakus.bizimsemt.data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DataUtils {
    public static LocalDateTime birthDate(long years) {
        return LocalDateTime.now().minusYears(years);
    }
    public static List<String> addresses() {
        return Arrays.asList("Lorem ipsum 13, Rotterdam", "Dolor sit amet 12, Rotterdam");
    }
}
