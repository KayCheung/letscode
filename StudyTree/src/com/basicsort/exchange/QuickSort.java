package com.basicsort.exchange;

import com.basicsort.AbstractBasicSort;
import com.util.CommUtil;

public class QuickSort extends AbstractBasicSort {

	@Override
	public int[] doSort(int start, int end, int[] array) {
		quickSort(start, end, array);
		return array;
	}

	public void quickSort(int start, int end, int[] array) {
		if (start >= end) {
			return;
		}
		int pivot = firstElement_Should_Fall_Into(start, end, array);
		quickSort(start, pivot - 1, array);
		quickSort(pivot + 1, end, array);
	}

	private int firstElement_Should_Fall_Into(int low, int high, int[] array) {
		int firstValue = array[low];
		while (low < high) {
			while (low < high && array[high] >= firstValue) {
				high--;
			}
			array[low] = array[high];

			while (low < high && array[low] <= firstValue) {
				low++;
			}
			array[high] = array[low];
		}
		array[low] = firstValue;
		System.out.println("low=" + low + ", high=" + high
				+ ". First element falls into: " + low);
		return low;
	}

	public static void main(String[] args) {
		runTest(new QuickSort());
	}
}
