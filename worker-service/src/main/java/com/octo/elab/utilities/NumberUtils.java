package com.octo.elab.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * This utility class will implement all out of the box Numeric methods
 * 
 * @author Sumit Dang
 *
 */
public class NumberUtils {

	/**
	 * This method to merge any number of integer arrays
	 *
	 */
	public static Integer[] merge(final Integer[]... arrays) {
		int size = 0;
		for (Integer[] a : arrays)
			size += a.length;

		Integer[] res = new Integer[size];

		int destPos = 0;
		for (int i = 0; i < arrays.length; i++) {
			if (i > 0)
				destPos += arrays[i - 1].length;
			int length = arrays[i].length;
			System.arraycopy(arrays[i], 0, res, destPos, length);
		}
		return res;
	}

	/**
	 * Remove duplicates from integer arrays
	 *
	 */
	public static Integer[] removeDuplicates(Integer[] array) {
		Set<Integer> set = new LinkedHashSet<Integer>();
		for (Integer token : array) {
			set.add(token);
		}
		Integer[] result = set.toArray(new Integer[0]);
		return result;
	}

	/**
	 * Get common tokens from 2 Integer arrays
	 *
	 */
	public static Integer[] commonTokens(Integer[] array1, Integer[] array2) {
		Set<Integer> hashSet = new HashSet<Integer>(Arrays.asList(array1));
		Set<Integer> commonTokens = new HashSet<Integer>();
		for (Integer token : array2) {
			if (hashSet.contains(token)) {
				commonTokens.add(token);
			}
		}
		Integer[] result = commonTokens.toArray(new Integer[0]);
		return result;
	}

	/**
	 * Converts a String to Integer[], using delimiter as split string
	 *
	 */
	public static Integer[] convertToIntegerArray(String str, String delimiter) {

		if (StringUtils.isNotBlank(str)) {
			String[] array = str.split(delimiter);
			List<Integer> result = new ArrayList<Integer>();
			for (int i = 0; i < array.length; i++) {
				try {
					result.add(Integer.parseInt(array[i]));
				} catch (NumberFormatException nfe) {
				}
			}
			return result.toArray(new Integer[0]);
		}
		return null;
	}

	/**
	 * Checks if Integer[] contains given Integer
	 * 
	 * @param Integer[]
	 * @param key
	 * 
	 * @return boolean result
	 *
	 */
	public static boolean contains(final Integer[] array, final Integer key) {
		if (array != null && array.length > 0) {
			return Arrays.asList(array).contains(key);
		}
		return false;
	}

}
