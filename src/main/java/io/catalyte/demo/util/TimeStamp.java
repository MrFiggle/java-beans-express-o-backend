package io.catalyte.demo.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {

    public TimeStamp(){}

    /**
     * get a String representation of the current date, time, and timezone
     *
     * @return timestamp in the format: yyyy-MM-dd HH:mm:ss z
     */
    public String getTimeStamp() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return now.format(formatter);
    }
}