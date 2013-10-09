package egovframework.com.guruguru.system.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static final String YEAR_PATTERN = "yyyy";
	
	public static final String MONTH_PATTERN = "yyyyMM";
	
	public static final String DATE_PATTERN = "yyyyMMdd";

	public static int getDayOfMonth(int year, int month) {
		DateTime dateTime = new DateTime(year, month, 1, 0, 0);
		
		return dateTime.dayOfMonth().getMaximumValue();
	}
	
	public static String getLastYear(int year) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(YEAR_PATTERN);
		
		DateTime dateTime = new DateTime(year, 1, 1, 0, 0);
		DateTime lastYear = dateTime.minusYears(1);
		
		return formatter.print(lastYear);
	}
	
	public static String getLastMonth(int year, int month) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(MONTH_PATTERN);
		
		DateTime dateTime = new DateTime(year, month, 1, 0, 0);
		DateTime lastMonth = dateTime.minusMonths(1);
		
		return formatter.print(lastMonth);
	}
	
	public static String getYesterDay(int year, int month, int day) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_PATTERN);
		
		DateTime dateTime = new DateTime(year, month, day, 0, 0);
		DateTime yesterday = dateTime.minusDays(1);
		
		return formatter.print(yesterday);
	}
	
}
