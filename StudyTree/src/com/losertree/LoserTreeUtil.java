package com.losertree;

import java.util.Arrays;

import com.common.TreeUtil;

public class LoserTreeUtil {
	public static LSNode createVisibleLoserTree(int[] ls) {
		if (ls == null || ls.length == 0) {
			return null;
		}
		LSNode[] arrayNode = new LSNode[ls.length];
		for (int i = 1; i < ls.length; i++) {
			arrayNode[i] = LSNode.createNode(i, ls[i]);
		}

		int leftHalf = arrayNode.length / 2;
		for (int i = 1; i < leftHalf; i++) {
			LSNode parent = arrayNode[i];

			int left = i * 2;
			parent.L = arrayNode[left];

			int right = left + 1;
			if (right <= arrayNode.length - 1) {
				parent.R = arrayNode[right];
			}
		}
		return arrayNode[1];
	}

	static class ValueContainer {
		private int valueIndex;
		private boolean max;

		public static ValueContainer createVC(int valueIndex) {
			ValueContainer vc = new ValueContainer();
			vc.setValueIndex(valueIndex);
			return vc;
		}

		public boolean max() {
			return max;
		}

		public int valueIndex() {
			return valueIndex;
		}

		public void setValueIndex(int orgnIndex) {
			max = false;
			this.valueIndex = orgnIndex;
		}

		public void setMax() {
			max = true;
			valueIndex = -1;
		}
	}

	public static void main(String[] args) {
		test_createLoserTree();
	}

	private static void fdf(int[][] twoDArray) {
		int k = twoDArray.length;

		int[] currentIndex = new int[k];

		int[] arrayToBeSearch = new int[k];
		for (int i = 0; i < arrayToBeSearch.length; i++) {
			arrayToBeSearch[i] = twoDArray[i][0];
			currentIndex[i] = 0;
		}

		int[] loserTree = createLoserTree(arrayToBeSearch);
		while (arrayToBeSearch[loserTree[0]] != Integer.MAX_VALUE) {
			int q = loserTree[0];
			System.out.println(q + ", ");

		}

		for (int i = 0; i < k - 1; i++) {
			int[] oneArray = twoDArray[i];

		}

	}

	public static void test_createLoserTree() {
		int[] array = TreeUtil.genereateRandomArray(60, 101);
		createLoserTree(array);
	}

	public static int[] createLoserTree(int[] values) {
		ValueContainer[] containers = new ValueContainer[values.length];
		for (int i = 0; i < containers.length; i++) {
			containers[i] = ValueContainer.createVC(i);
		}

		int[] ls = new int[values.length];
		for (int i = 0; i < ls.length; i++) {
			ls[i] = -1;
		}

		for (int i = values.length - 1; i >= 0; i--) {
			adjust(ls, containers, i, values);
		}
		System.out.println(Arrays.toString(ls));
		return ls;
	}

	/**
	 * 1. <b>a node is always compared with its parent</b>
	 * 
	 * 2. <code>ls</code>'s elements: index of <code>values</code>
	 * 
	 * @param ls
	 * @param containers
	 * @param valueIndex
	 * @param values
	 */
	private static void adjust(int[] ls, final ValueContainer[] containers,
			int valueIndex, int[] values) {
		// 1. lsIndex designated the cell index on ls
		// 2. lsIndex is parent of adjustMe
		int treeIndex = (valueIndex + containers.length) >> 1;
		while (treeIndex > 0) {
			int parentIndex = ls[treeIndex];
			// me bigger
			if (parentIndex < 0 // initial value
					|| valueIndex < 0 // initial value
					|| greatThan(containers[valueIndex],
							containers[parentIndex], values)) {
				// 1. move up childIndex
				ls[treeIndex] = valueIndex;
				// 2. smaller value should compare with it's parent
				valueIndex = parentIndex;
			}
			treeIndex = treeIndex / 2;
		}
		ls[0] = valueIndex;
	}

	private static boolean greatThan(ValueContainer A, ValueContainer B,
			int[] values) {
		if (A.max() && B.max()) {
			return false;
		}

		if (A.max()) {
			return true;
		}
		if (B.max()) {
			return false;
		}

		// b is neither max nor min
		return values[A.valueIndex()] > values[B.valueIndex()];
	}

}
