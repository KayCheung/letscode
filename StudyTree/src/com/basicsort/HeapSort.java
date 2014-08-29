package com.basicsort;

import java.util.Arrays;

public class HeapSort extends AbstractBasicSort {

	@Override
	public int[] doSort(int start, int end, int[] array) {
		Arrays.sort(array);
		return array;
	}

	public static void main(String[] args) {
		runTest(new HeapSort());
	}
}
