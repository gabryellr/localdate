package com.gabryellr.localdate.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    public static final String AMERICA_SAO_PAULO = "America/Sao_Paulo";
    @InjectMocks
    private Service service;

    @Mock
    private Clock clock;

    private Clock fixedClock;

    @Test
    public void givenTwoDatesWhenDifferentiatingWithMore20DaysThenWeGetTrue() {
        //Mock LocalDate.now(clock) in Service
        Clock clock = Clock.fixed(LocalDate.of(2020, 9, 15).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(clock.instant()).when(this.clock).instant();
        doReturn(clock.getZone()).when(this.clock).getZone();

        fixedClock = Clock.fixed(LocalDate.of(2020, 8, 20).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        boolean result = service.hasMoreThan20Days(LocalDate.now(fixedClock));

        Assert.assertTrue("Condition must be true when difference has more than 20 days", result);
    }

    @Test
    public void givenTwoDateTimeWhenDifferentiatingInSecondsThenWeGet50Seconds() {
        //Mock LocalDate.now(clock) in Service
        Clock clock = Clock.fixed(LocalDateTime.of(2020, 1, 28, 20, 16, 0)
                .atZone(ZoneId.of(AMERICA_SAO_PAULO)).toInstant(), ZoneId.systemDefault());
        doReturn(clock.instant()).when(this.clock).instant();
        doReturn(clock.getZone()).when(this.clock).getZone();

        LocalDateTime plus50Seconds = LocalDateTime.now(clock).plusSeconds(50);

        long secondsDifference = service.getSecondsDifference(plus50Seconds);

        Assert.assertEquals(50L, secondsDifference);
    }

}