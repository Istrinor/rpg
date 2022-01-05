package com.game.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

    public static Date millisToDateInDefaultTimeZoneOrNull(Long dateInMillis) {
        Date result;
        if (dateInMillis == null) {
            result = null;
        } else {
            result = new Date(dateInMillis);
        }
        return result;
    }

}
