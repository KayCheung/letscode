package com.util;

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

	public static void swap(int i1, int i2, int[] array) {
		int tmp = array[i1];
		array[i1] = array[i2];
		array[i2] = tmp;
	}
}
