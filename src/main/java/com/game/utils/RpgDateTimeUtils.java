package com.game.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class RpgDateTimeUtils {

    public static LocalDate millisToLocalDateInDefaultTimeZoneOrNull(Long dateInMillis) {
        LocalDate result;
        if (dateInMillis == null) {
            result = null;
        } else {
            result = Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return result;
    }

}
