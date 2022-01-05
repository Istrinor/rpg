package com.game.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RpgDateTimeUtils {

    public static LocalDate millisToLocalDateInDefaultTimeZoneOrNull(Long dateInMillis) {
        LocalDate result;
        if (dateInMillis == null) {
            result = null;
        } else {
            LocalDateTime localDateTime = Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
            System.out.println(localDateTime);
            result = localDateTime.toLocalDate();
        }
        return result;
    }

}
