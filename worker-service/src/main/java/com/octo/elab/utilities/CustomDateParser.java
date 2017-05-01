package com.octo.elab.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomDateParser {
	private static final List<String> formats = new ArrayList<String>();

	static {
		formats.add("yyyy-MM-dd HH:mm:ss");
		formats.add("yyyy-MM-dd");
		formats.add("yyyy-MM-dd HH:mm a");
		formats.add("EEE MMM d HH:mm:ss z yyyy");
		formats.add("yyyy-MM-dd'T'HH:mm:ss.SSS z");
	}

	public static Date parse(String dateValue) {
		for (String candidateFormat : formats) {
			Date date = parseDate(dateValue, candidateFormat);
			if (date != null) {
				return date;
			}
		}
		return null;
	}

	private static Date parseDate(String dateCandidate, String dateFormat) {
		try {
			return new SimpleDateFormat(dateFormat).parse(dateCandidate);
		} catch (Exception e) {
			return null;
		}
	}

	public static String parseDate(long dateCandidate, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = new Date(dateCandidate);
			return (format.format(date));
		} catch (Exception e) {
			return null;
		}
	}

	public static String parseDate(long dateCandidate) {
		return parseDate(dateCandidate, "MM/dd/yyyy");
	}
}
