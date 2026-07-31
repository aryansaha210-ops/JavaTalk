package com.aryan.javatalk.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("hh:mm a");

    public static String getCurrentTime() {
        return LocalTime.now().format(FORMATTER);
    }
}
