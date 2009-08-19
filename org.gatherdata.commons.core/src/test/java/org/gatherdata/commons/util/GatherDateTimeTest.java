package org.gatherdata.commons.util;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;

import org.gatherdata.commons.util.GatherDateTime;
import org.junit.Test;

public class GatherDateTimeTest {

	private static final String YEAR_ONLY_VALID_STRING = "1920";
	private static final String YEAR_ONLY_INVALID_STRING = "19Twenty";
	private static final String YEAR_ONLY_OUTOFBOUNDS_STRING = "-30208";

	private static final String YEAR_MONTH_DAY_VALID_STRING = "1969-01-07";
	private static final String YEAR_MONTH_DAY_INVALID_STRING = "19690107";

	private static final String MONTH_DAY_YEAR_VALID_STRING = "05/11/1985";
	private static final String MONTH_DAY_YEAR_INVALID_STRING = "301-07-1969";
	
	private static final String YEAR_MONTH_DAY_TIME_VALID_STRING = "2009-01-22-05:14";
	private static final String YEAR_MONTH_DAY_TIME_INVALID_STRING = "19690107:05:32";

	@Test
	public void shouldParseAsYearOnly() throws Exception {
		Calendar parsedCalendar = GatherDateTime.parseAsYearOnly(YEAR_ONLY_VALID_STRING);
		assertTrue(parsedCalendar.isSet(Calendar.YEAR));
		assertFalse(parsedCalendar.isSet(Calendar.MONTH));
	}
	
	@Test (expected = NumberFormatException.class)
	public void shouldNotParseInvalidAsYearOnly() throws Exception {
		GatherDateTime.parseAsYearOnly(YEAR_ONLY_INVALID_STRING);
	}
	
	@Test (expected = NumberFormatException.class)
	public void shouldNotParseOutOfBoundsAsYearOnly() throws Exception {
		GatherDateTime.parseAsYearOnly(YEAR_ONLY_OUTOFBOUNDS_STRING);
	}

	@Test
	public void shouldParseAsYearMonthDay() throws Exception {
		Calendar parsedCalendar = GatherDateTime.parseAsYearMonthDay(YEAR_MONTH_DAY_VALID_STRING);
		assertTrue(parsedCalendar.isSet(Calendar.YEAR));
		assertTrue(parsedCalendar.isSet(Calendar.MONTH));
		assertTrue(parsedCalendar.isSet(Calendar.DAY_OF_MONTH));
		assertFalse(parsedCalendar.isSet(Calendar.HOUR_OF_DAY));
	}
	
	@Test (expected = ParseException.class)
	public void shouldNotParseInvalidAsYearMonthDay() throws Exception {
		GatherDateTime.parseAsYearMonthDay(YEAR_MONTH_DAY_INVALID_STRING);
	}

	@Test
	public void shouldParseAsMonthDayYear() throws Exception {
		Calendar parsedCalendar = GatherDateTime.parseAsMonthDayYear(MONTH_DAY_YEAR_VALID_STRING);
		assertTrue(parsedCalendar.isSet(Calendar.YEAR));
		assertTrue(parsedCalendar.isSet(Calendar.MONTH));
		assertTrue(parsedCalendar.isSet(Calendar.DAY_OF_MONTH));
		assertFalse(parsedCalendar.isSet(Calendar.HOUR_OF_DAY));
	}
	
	@Test (expected = ParseException.class)
	public void shouldNotParseInvalidAsMonthDayYear() throws Exception {
		GatherDateTime.parseAsMonthDayYear(MONTH_DAY_YEAR_INVALID_STRING);
	}

	@Test
	public void shouldParseAsYearMonthDayTime() throws Exception {
		Calendar parsedCalendar = GatherDateTime.parseAsYearMonthDayTime(YEAR_MONTH_DAY_TIME_VALID_STRING);
		assertTrue(parsedCalendar.isSet(Calendar.YEAR));
		assertTrue(parsedCalendar.isSet(Calendar.MONTH));
		assertTrue(parsedCalendar.isSet(Calendar.DAY_OF_MONTH));
		assertTrue(parsedCalendar.isSet(Calendar.HOUR_OF_DAY));
		assertTrue(parsedCalendar.isSet(Calendar.MINUTE));
	}
	
	@Test (expected = ParseException.class)
	public void shouldNotParseInvalidAsYearMonthDayTime() throws Exception {
		GatherDateTime.parseAsYearMonthDayTime(YEAR_MONTH_DAY_TIME_INVALID_STRING);
	}

	@Test
	public void shouldParseAnySupportedFormat() throws Exception {
		assertNotNull(GatherDateTime.parse(YEAR_ONLY_VALID_STRING));
		assertNotNull(GatherDateTime.parse(YEAR_MONTH_DAY_VALID_STRING));
		assertNotNull(GatherDateTime.parse(MONTH_DAY_YEAR_VALID_STRING));
		assertNotNull(GatherDateTime.parse(YEAR_MONTH_DAY_TIME_VALID_STRING));
	}

	@Test
	public void shouldFormatNullValueAsBlank() {
		assertEquals("", GatherDateTime.format(null));
	}

	private void testRoundTripStartingWith(String originalCalendarString) throws ParseException {
        Calendar fromStringToCalendar = GatherDateTime.parse(originalCalendarString);
        String backToString = GatherDateTime.format(fromStringToCalendar);
        Calendar backToCalendarAgain = GatherDateTime.parse(backToString);
        assertEquals(fromStringToCalendar, backToCalendarAgain);
	}
	    
	@Test
	public void shouldParseYearOnlyRoundtrip() throws ParseException {
	    testRoundTripStartingWith(YEAR_ONLY_VALID_STRING);
	}
	
	@Test
    public void shouldParseYearMonthDayRoundtrip() throws ParseException {
        testRoundTripStartingWith(YEAR_MONTH_DAY_VALID_STRING);
    }

    @Test
    public void shouldParseMonthDayYearRoundtrip() throws ParseException {
        testRoundTripStartingWith(YEAR_MONTH_DAY_VALID_STRING);
    }
    
    @Test
    public void shouldParseYearMonthDayTimeRoundtrip() throws ParseException {
        testRoundTripStartingWith(YEAR_MONTH_DAY_TIME_VALID_STRING);
    }
    
}
