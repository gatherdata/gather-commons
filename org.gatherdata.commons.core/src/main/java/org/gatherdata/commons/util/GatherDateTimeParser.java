/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class GatherDateTimeParser {

    public static final int MIN_ACCEPTABLE_YEAR = 1900;
    public static final int MAX_ACCEPTABLE_YEAR = 2100;

    public static final DateTimeFormatter YEAR_MONTH_DAY = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter YEAR_MONTH = DateTimeFormat.forPattern("yyyy-MM");
    public static final DateTimeFormatter MONTH_DAY_YEAR = DateTimeFormat.forPattern("MM/dd/yyyy");
    
    public static final DateTimeFormatter ISO_DATE_TIME = ISODateTimeFormat.dateTime();
    public static final DateTimeFormatter YEAR_MONTH_DAY_TIME = DateTimeFormat.forPattern("yyyy-MM-dd-HH:mm");
    
    public static DateTime parseDateTime(String dateTimeString) throws ParseException {
        DateTime parsedDateTime = null;

        try {
            parsedDateTime = ISO_DATE_TIME.parseDateTime(dateTimeString);
        } catch (Exception e) {
            ;
        }
        if (parsedDateTime == null) {
            try {
                parsedDateTime = YEAR_MONTH_DAY_TIME.parseDateTime(dateTimeString);
            } catch (Exception e) {
                ;
            }
        }
        return parsedDateTime;
    }

    public static LocalDate parseDate(String dateString) throws ParseException {
        LocalDate parsedDate = null;

        if (parsedDate == null) {
            try {
                parsedDate = YEAR_MONTH_DAY.parseDateTime(dateString).toLocalDate();
            } catch (Exception e) {
                ;
            }
        }
        if (parsedDate == null) {
            try {
                parsedDate = MONTH_DAY_YEAR.parseDateTime(dateString).toLocalDate();
            } catch (Exception e) {
                ;
            }
        }
        if (parsedDate == null) {
            try {
                parsedDate = parseAsYearOnly(dateString);
            } catch (Exception e) {
                ;
            }
        }

        return parsedDate;
    }

    public static LocalDate parseAsYearOnly(String dateTimeString) {
        LocalDate yearOnlyCal = null;

        int yearValue = Integer.parseInt(dateTimeString);
        if ((yearValue > MIN_ACCEPTABLE_YEAR) && (yearValue < MAX_ACCEPTABLE_YEAR)) {
            yearOnlyCal = new LocalDate(yearValue, 1, 1);
        } else {
            throw new NumberFormatException(yearValue + "is outside of acceptable range for year value ("
                    + MIN_ACCEPTABLE_YEAR + "-" + MAX_ACCEPTABLE_YEAR + ")");
        }
        return yearOnlyCal;
    }

    public static String format(DateTime dateTimeToPrint) {
        return ISO_DATE_TIME.print(dateTimeToPrint);
    }

    public static String format(LocalDate dateToPrint) {
        return YEAR_MONTH_DAY.print(dateToPrint);
    }

}
