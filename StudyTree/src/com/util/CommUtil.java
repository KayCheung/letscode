package com.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommUtil {
	public static int[] genereateRandomArray(int arrayLength, int maxIntExcluded) {
		Random r = new Random();
		int[] array = new int[arrayLength];
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(maxIntExcluded);
		}

		return array;
	}

	public static void swap(int index1, int index2, int[] array) {
		int tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}

	public static List<String> split(String original, String delimiter) {
		List<String> list = new ArrayList<String>();
		if (original == null || original.trim().length() == 0) {
			return list;
		}
		int delimiterL = delimiter.length();
		int orgnL = original.length();
		int lastEnd = 0;// delimiter's (endIndex+1)
		while (lastEnd <= (orgnL - 1)) {
			int currentStart = original.indexOf(delimiter, lastEnd);
			if (currentStart == -1) {
				list.add(original.substring(lastEnd));
				break;
			}
			list.add(original.substring(lastEnd, currentStart));
			lastEnd = currentStart + delimiterL;
		}
		if (original.endsWith(delimiter)) {
			list.add("");
		}
		return list;
	}

	public static String[] combineNewArray(String[] array1, String[] array2) {

		if (array1 == null || array1.length == 0) {
			if (array2 == null || array2.length == 0) {
				return new String[0];
			} else {
				String[] rst = new String[0];
				System.arraycopy(array2, 0, rst, 0, array2.length);
				return rst;
			}
		}

		if (array2 == null || array2.length == 0) {
			if (array1 == null || array1.length == 0) {
				return new String[0];
			} else {
				String[] rst = new String[0];
				System.arraycopy(array1, 0, rst, 0, array1.length);
				return rst;
			}
		}
		// Since here, both array1 and array2 are not empty
		String[] rst = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, rst, 0, array1.length);
		System.arraycopy(array2, 0, rst, array1.length, array2.length);
		return rst;
	}

	public static String int2str(BigInteger bi, int finalLength) {
		StringBuilder sb = new StringBuilder(bi.toString());

		while (sb.length() < finalLength) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}

	public static String int2str(int i, int finalLength) {
		StringBuilder sb = new StringBuilder(i + "");

		while (sb.length() < finalLength) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}
}
