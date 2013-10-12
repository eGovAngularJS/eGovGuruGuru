package egovframework.com.guruguru.system.util;

import java.text.DecimalFormat;

public class NumberUtils {

	public static final String DECIMAL_DEFAULT_FORMAT = "#,##0.00";
	
	public static String convertToDecimal(double usage) {
		DecimalFormat format = new DecimalFormat(DECIMAL_DEFAULT_FORMAT);
		
		return format.format(usage);
	}
	
	public static int getRandomInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}
}
