package com.basicsort;

import java.util.Arrays;

public class HeapSort extends AbstractBasicSort {

	@Override
	public int[] doSort(int start, int end, int[] array) {
		Arrays.sort(array);
		return array;
	}

	public void ascending_By_BigRoot(int start, int end, int[] array) {

	}

	private void buildBigRootHeap(int start, int end, int[] array) {

	}

	public void descending_By_SmallRoot(int start, int end, int[] array) {

	}

	private void buildSmallRootHeap(int start, int end, int[] array) {

	}

	public static void main(String[] args) {
		runTest(new HeapSort());
	}
}
