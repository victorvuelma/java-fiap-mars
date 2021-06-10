package br.com.fiap.apmd.mars.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

	public static Date fromString(String date) throws ParseException {
		return fmt.parse(date);
	}

	public static String toString(Date date) {
		return fmt.format(date);
	}

}
