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

	public static void swap(int index1, int index2, int[] array) {
		int tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}
}
