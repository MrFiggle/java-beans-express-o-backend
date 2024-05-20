package io.catalyte.demo.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {
    
    public TimeStamp(){}

    public String getTimeStamp() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return now.format(formatter);
    }
}
