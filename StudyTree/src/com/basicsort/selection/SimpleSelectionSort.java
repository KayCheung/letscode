package com.basicsort.selection;

import com.basicsort.AbstractBasicSort;
import com.util.CommUtil;

public class SimpleSelectionSort extends AbstractBasicSort {

	@Override
	public int[] doSort(int start, int end, int[] array) {
		simpleSelectionSort(start, end, array);
		return array;
	}

	/**
	 * 1. auxiliary space-------- Only a tmp int, used for swap
	 * 
	 */
	public void simpleSelectionSort(int start, int end, int[] array) {
		for (int i = start; i <= end - 1; i++) {
			int minIndex = i;
			for (int k = i + 1; k <= end; k++) {
				if (array[k] < array[minIndex]) {
					minIndex = k;
				}
			}
			// Yes, find smaller element
			if (minIndex != i) {
				CommUtil.swap(i, minIndex, array);
			}
		}
	}

	public static void main(String[] args) {
		runTest(new SimpleSelectionSort());
	}
}
