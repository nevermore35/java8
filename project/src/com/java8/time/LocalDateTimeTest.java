package com.java8.time;

import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORB;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeTest {

    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(of);
        LocalDateTime of1 = LocalDateTime.of(2020, 12, 20, 17, 46, 30, 235);
        System.out.println(of1);
        LocalDateTime plus = now.plusYears(2);
        System.out.println(plus);
        LocalDateTime localDateTime = now.minusDays(10);
        System.out.println(localDateTime);
        int dayOfMonth = now.getDayOfMonth();
        System.out.println(dayOfMonth);
        Month month = now.getMonth();
        int monthValue = now.getMonthValue();
        System.out.println(month);
        System.out.println(monthValue);
    }
    @Test
    public void test2(){
        Instant now = Instant.now();
        System.out.println(now);
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(now.toEpochMilli());
        Instant instant = Instant.ofEpochSecond(1000);
        System.out.println(instant);
        Instant instant1 = Instant.ofEpochMilli(1100);
        System.out.println(instant1);
    }
    @Test
    public void test3(){
        Instant now = Instant.now();
        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Instant now1 = Instant.now();
        Duration between = Duration.between(now, now1);
        System.out.println(between.toMillis());
        System.out.println(between.getSeconds());

        LocalTime now2 = LocalTime.now();
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalTime now3 = LocalTime.now();
        System.out.println(Duration.between(now3,now2).toMillis());
        LocalDate now4 = LocalDate.of(2015,12,12);
        LocalDate now5 = LocalDate.now();
        System.out.println(Period.between(now4,now5).getYears());
        System.out.println(Period.between(now4,now5).getDays());
    }
    @Test
    public void test4(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.withDayOfMonth(10);
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = now.withDayOfYear(15);
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2 = now.withHour(10);
        System.out.println(localDateTime2);
        LocalDateTime with = now.with(TemporalAdjusters.ofDateAdjuster((x) -> x.plusYears(2)));
        System.out.println(with);
        LocalDateTime with1 = now.with(TemporalAdjusters.next(DayOfWeek.of(3)));
        System.out.println(with1);
        //自定义下一个工作日
        LocalDateTime with2 = now.with((x) -> {
            LocalDateTime x1 = (LocalDateTime) x;
            if (x1.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                return x1.plusDays(3);
            } else if (x1.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                return x1.plusDays(2);
            } else {
                return x1.plusDays(1);
            }
        });
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(with2.format(dtf));
    }
    @Test
    public void test5(){
        DateTimeFormatter basicIsoDate = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(basicIsoDate);
        System.out.println(format);
    }
}
