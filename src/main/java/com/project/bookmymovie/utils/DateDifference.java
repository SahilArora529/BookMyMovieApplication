package com.project.bookmymovie.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateDifference {
    public static int differenceBetweenDates(LocalDateTime initialDay , LocalDateTime finalDay){
        long difference = ChronoUnit.SECONDS.between(initialDay, finalDay);
        int daysBetween = (int) (difference / (60*60*24));
        return daysBetween;
    }
}
