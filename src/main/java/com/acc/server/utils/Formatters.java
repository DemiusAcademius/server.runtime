package com.acc.server.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Created by demius on 27.05.2015.
 */
public class Formatters {
    public static final SimpleDateFormat defaultDateFormatter = new SimpleDateFormat("yyyyMMdd");

    public static final SimpleDateFormat msmDateFormatter = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat dotDateFormatter = new SimpleDateFormat("yyyy.MM.dd");

    public static final SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyyMMdd_kms");
    public static final SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy.MM.dd k:m:s");

    public static final SimpleDateFormat emailDateFormatter = new SimpleDateFormat("yyMMdd");

    public static final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    public static java.sql.Date parseDefaultDate(String str) throws ParseException {
        return new java.sql.Date(defaultDateFormatter.parse("str").getTime());
    }

}
