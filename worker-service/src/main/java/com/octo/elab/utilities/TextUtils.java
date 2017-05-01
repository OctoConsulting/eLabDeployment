package com.octo.elab.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This utility class will implement all out of the box String methods
 * 
 * @author Sumit Dang
 *
 */
public class TextUtils extends StringUtils {

	private static final Logger log = LoggerFactory.getLogger(TextUtils.class);

	/**
	 * Sample calls String s = convertInstanceOfObject("string", String.class);
	 * Integer i = convertInstanceOfObject(4, Integer.class);
	 * 
	 * @param Object
	 *            - List of generic objects
	 * @param Generic
	 *            Class
	 * 
	 * @return Given object type-casted in specified generic class
	 */
	public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
		try {
			return clazz.cast(o);
		} catch (ClassCastException e) {
			return null;
		}
	}

	/**
	 * @param List<?>
	 *            List of generic objects
	 * 
	 * @return Comma delimited string for input list
	 */
	public static String stringify(List<?> list) {
		return stringify(list, Constants.DEFAULT_DELIMITER);
	}

	/**
	 * @param List<?>
	 *            List of generic objects
	 * @param delimiter
	 * 
	 * @return String for input list with specified delimiter
	 */
	public static String stringify(List<?> list, String delimiter) {
		boolean needDelimiter = false;
		StringBuilder sb = new StringBuilder();
		for (Object obj : list) {
			if (needDelimiter) {
				sb.append(delimiter);
			} else {
				needDelimiter = true;
			}
			sb.append(obj);
		}
		return sb.toString();
	}

	/**
	 * @param String
	 *            keyword = CFDA
	 * 
	 * @return String %CFDA,%
	 */
	public static String convertToSQLLikeToken(String keyword) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.SQL_WILDCARD).append(keyword).append(Constants.DEFAULT_DELIMITER)
				.append(Constants.SQL_WILDCARD);
		return sb.toString();
	}

	/**
	 * This method splits input strings with comma as delimiter and then returns
	 * commons tokens from input strings.
	 * 
	 * @param String1
	 * @param String2
	 * 
	 * @return String with common tokens from input strings.
	 */
	public static String commonTokens(String str1, String str2) {
		return commonTokens(str1, str2, Constants.DEFAULT_DELIMITER);
	}

	/**
	 * This method splits input strings with given delimiter and then returns
	 * commons tokens from input strings.
	 * 
	 * @param String1
	 * @param String2
	 * @param delimiter
	 * 
	 * @return String with common tokens from input strings.
	 */
	public static String commonTokens(String str1, String str2, String delimiter) {

		if (StringUtils.isBlank(str1)) {
			return str2;
		} else if (StringUtils.isBlank(str2)) {
			return str1;
		} else {
			String[] splitString1 = str1.split(delimiter);
			String[] splitString2 = str2.split(delimiter);

			String[] commonStrings = commonTokens(splitString1, splitString2);

			return stringify(Arrays.asList(commonStrings), delimiter);
		}
	}

	/**
	 * This method returns commons tokens from input string arrays.
	 * 
	 * @param String[]
	 * @param String[]
	 * 
	 * @return List<String> with common tokens from input strings.
	 */
	public static String[] commonTokens(String[] str1, String[] str2) {

		if (ArrayUtils.isEmpty(str1)) {
			return str2;
		} else if (ArrayUtils.isEmpty(str2)) {
			return str1;
		} else {
			List<String> keepList = new ArrayList<String>();
			for (String token : str1) {
				if (Arrays.asList(str2).contains(token)) {
					keepList.add(token);
				}
			}

			return keepList.toArray(new String[0]);
		}
	}

	// Method to print full trace of Claim process
	public static void printTrace(Map<String, String> traceMap) {
		for (Map.Entry<String, String> entry : traceMap.entrySet()) {
			log.info(entry.getKey() + " : " + entry.getValue());
		}
	}

}
