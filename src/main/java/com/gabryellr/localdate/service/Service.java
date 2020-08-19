package com.gabryellr.localdate.service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@org.springframework.stereotype.Service
public class Service {

    public static final int LIMIT_DAYS = 20;
    private final Clock clock;

    public Service(Clock clock) {
        this.clock = clock;
    }

    public boolean hasMoreThan20Days(LocalDate localDate) {
        LocalDate now = LocalDate.now(clock);
        long differenceDays = ChronoUnit.DAYS.between(now, localDate);

        return differenceDays > LIMIT_DAYS;
    }


    public long getSecondsDifference(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now(clock);

        return ChronoUnit.SECONDS.between(now, localDateTime);
    }

}