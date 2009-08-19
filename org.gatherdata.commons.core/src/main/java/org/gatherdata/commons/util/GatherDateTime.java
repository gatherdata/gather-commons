package org.gatherdata.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GatherDateTime {

	public static final int MIN_ACCEPTABLE_YEAR = 1900;
	public static final int MAX_ACCEPTABLE_YEAR = 2100;
	
	public static final SimpleDateFormat YEAR_MONTH_DAY = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat YEAR_MONTH = new SimpleDateFormat("yyyy-MM");
	public static final SimpleDateFormat YEAR_MONTH_DAY_TIME = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	public static final SimpleDateFormat MONTH_DAY_YEAR = new SimpleDateFormat("MM/dd/yyyy");

	public static GregorianCalendar parse(String dateTimeString) throws ParseException {
		GregorianCalendar parsedCalendar = null;

		try {
			parsedCalendar = parseAsYearMonthDayTime(dateTimeString);
		} catch (Exception e) {;}
		if (parsedCalendar == null)	{
			try {
				parsedCalendar = parseAsYearMonthDay(dateTimeString);
			} catch (Exception e) {;}
		}
		if (parsedCalendar == null)	{
			try {
				parsedCalendar = parseAsMonthDayYear(dateTimeString);
			} catch (Exception e) {;}
		}
		if (parsedCalendar == null) {
			try {
				parsedCalendar = parseAsYearOnly(dateTimeString);
			} catch (Exception e) { ; }
		}
		
		return parsedCalendar;
	}

	public static GregorianCalendar parseAsYearOnly(String dateTimeString) {
		GregorianCalendar yearOnlyCal = null;

		int yearValue = Integer.parseInt(dateTimeString);
		if ((yearValue > MIN_ACCEPTABLE_YEAR) && (yearValue < MAX_ACCEPTABLE_YEAR)) { 
			yearOnlyCal = new GregorianCalendar();
			yearOnlyCal.clear();
			yearOnlyCal.set(Calendar.YEAR, yearValue);
		} else {
			throw new NumberFormatException(yearValue + "is outside of acceptable range for year value (" +
					MIN_ACCEPTABLE_YEAR + "-" + MAX_ACCEPTABLE_YEAR + ")");
		}
		return yearOnlyCal;
	}

	public static GregorianCalendar parseAsYearMonthDay(String yearMonthDay) throws ParseException {
		Date asDate = YEAR_MONTH_DAY.parse(yearMonthDay);
		
		GregorianCalendar asCal = new GregorianCalendar();
		asCal.setTime(asDate);
		
		// clear first, then only set year,month,day
		GregorianCalendar yearMonthDayCal = new GregorianCalendar();
		yearMonthDayCal.setLenient(false); // complain about bad dates
		yearMonthDayCal.clear();
		yearMonthDayCal.set(Calendar.YEAR, asCal.get(Calendar.YEAR));
		yearMonthDayCal.set(Calendar.MONTH, asCal.get(Calendar.MONTH));
		yearMonthDayCal.set(Calendar.DAY_OF_MONTH, asCal.get(Calendar.DAY_OF_MONTH));
		
		return yearMonthDayCal;
	}

	public static GregorianCalendar parseAsMonthDayYear(String monthDayYear) throws ParseException {
		Date asDate = MONTH_DAY_YEAR.parse(monthDayYear);
		
		GregorianCalendar asCal = new GregorianCalendar();
		asCal.setTime(asDate);
		
		// clear first, then only set year,month,day
		GregorianCalendar yearMonthDayCal = new GregorianCalendar();
		yearMonthDayCal.setLenient(false); // complain about bad dates
		yearMonthDayCal.clear();
		yearMonthDayCal.set(Calendar.YEAR, asCal.get(Calendar.YEAR));
		yearMonthDayCal.set(Calendar.MONTH, asCal.get(Calendar.MONTH));
		yearMonthDayCal.set(Calendar.DAY_OF_MONTH, asCal.get(Calendar.DAY_OF_MONTH));
		
		return yearMonthDayCal;
	}

	public static GregorianCalendar parseAsYearMonthDayTime(String yearMonthDay) throws ParseException {
		Date asDate = YEAR_MONTH_DAY_TIME.parse(yearMonthDay);
		
		GregorianCalendar generalCal = new GregorianCalendar();
		generalCal.setTime(asDate);
		
		// clear first, then only set year,month,day
		GregorianCalendar specificCal = new GregorianCalendar();
		specificCal.setLenient(false); // complain about bad dates
		specificCal.clear();
		specificCal.set(Calendar.YEAR, generalCal.get(Calendar.YEAR));
		specificCal.set(Calendar.MONTH, generalCal.get(Calendar.MONTH));
		specificCal.set(Calendar.DAY_OF_MONTH, generalCal.get(Calendar.DAY_OF_MONTH));
		specificCal.set(Calendar.HOUR_OF_DAY, generalCal.get(Calendar.HOUR_OF_DAY));
		specificCal.set(Calendar.MINUTE, generalCal.get(Calendar.MINUTE));
		
		return specificCal;
	}
	
	/**
	 * Uses the default DateFormat#getDateTimeInstance() to produce a formatted string from a Calendar.
	 * 
	 * @param calendar
	 * @return date-time formatted string representation of the calendar
	 */
	public static String format(Calendar calendar) {
		if (calendar == null) return "";
		
		String formattedCalendar = null;
		if (calendar.isSet(Calendar.YEAR)) {
		    if (calendar.isSet(Calendar.MONTH)) {
		        if (calendar.isSet(Calendar.DAY_OF_MONTH)) {
		            if (calendar.isSet(Calendar.HOUR_OF_DAY)) {
		                formattedCalendar = YEAR_MONTH_DAY_TIME.format(calendar.getTime());
		            } else {
		                formattedCalendar = YEAR_MONTH_DAY.format(calendar.getTime());
		            }
		        } else {
		            formattedCalendar = YEAR_MONTH.format(calendar.getTime());
		        }
		    } else {
		        formattedCalendar = Integer.toString(calendar.get(Calendar.YEAR));
		    }
		} else {
		    
		}
		return formattedCalendar;
	}
}
