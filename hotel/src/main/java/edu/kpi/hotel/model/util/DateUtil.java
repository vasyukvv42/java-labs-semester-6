package edu.kpi.hotel.model.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final int EXPIRATION_DAYS_DELTA = -1;

    public static Date getExpiryDate(Date reserveFrom) {
        var calendar = Calendar.getInstance();
        calendar.setTime(reserveFrom);
        calendar.add(Calendar.DATE, EXPIRATION_DAYS_DELTA);
        return calendar.getTime();
    }

    public static int getDifferenceInDays(Date from, Date to) {
        long diff = to.getTime() - from.getTime();
        var convert = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return (int) Math.ceil(convert);
    }
}