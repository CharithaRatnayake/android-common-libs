package com.jachdev.commonlibs.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd 00:00:00";
    public static final String DATE_FORMAT_2 = "dd MMM. yyyy / HH:mm";
    public static final String DATE_FORMAT_COMMON = "dd MMM yyyy";
    private static final String TAG = DateTimeUtil.class.getSimpleName();

    public static String convertUnixToCustomFormat(String format) {
        if (format == null)
            format = DEFAULT_DATE_FORMAT;

        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date(getUnixTimestamp()));
    }

    public static String unixToDateTime(long unix, String format) {
        if (format == null)
            format = DEFAULT_DATE_FORMAT;

        return new SimpleDateFormat(format, Locale.getDefault()).format(unix);
    }

    public static String dateTimeToCustomFormat(String format, Calendar calendar) {
        if (format == null)
            format = DEFAULT_DATE_FORMAT;

        return new SimpleDateFormat(format, Locale.getDefault()).format(calendar.getTime());
    }

    public static String convertDateFormat(String date, String currentFormat, String newFormat) {
        if (date == null || date.isEmpty())
            date = convertUnixToCustomFormat(DEFAULT_DATE_FORMAT);
        try {
            DateFormat dateFormat = new SimpleDateFormat(currentFormat, Locale.ENGLISH);

            Date dateObject = dateFormat.parse(date);

            dateFormat = new SimpleDateFormat(newFormat, Locale.ENGLISH);

            return dateFormat.format(dateObject);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }


    public static String get12Hour() {
        return convertUnixToCustomFormat("h");
    }

    public static String getMinute() {
        return convertUnixToCustomFormat("m");
    }

    public static String getSecond() {
        return convertUnixToCustomFormat("s");
    }

    public static String getDateInMonth() {
        return convertUnixToCustomFormat("d");
    }

    public static String getDayNameInWeek() {
        return convertUnixToCustomFormat("E");
    }

    private static long getUnixTimestamp() {
        return System.currentTimeMillis();
    }
}
