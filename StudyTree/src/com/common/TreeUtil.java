package com.common;

import java.util.Random;

import com.bst.BSTUtil;
import com.showtree.VisibleNode;

public class TreeUtil {
	public static int[] genereateRandomArray(int arrayLength, int maxIntExcluded) {
		Random r = new Random();
		int[] array = new int[arrayLength];
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(maxIntExcluded);
		}

		return array;
	}

	public static int H(VisibleNode root) {
		// root node is in level 1 (NOTE: 1, not 0)
		if (root == null) {
			return 0;
		}
		int subLeftH = H(root.left());
		int totalLeftH = subLeftH + 1;
		int subRightH = H(root.right());
		int totalRightH = subRightH + 1;

		return (totalLeftH > totalRightH) ? totalLeftH : totalRightH;
	}

	private static void test_H() {
		int nodeCount = 15;
		int maxInt = 51;
		Node root = BSTUtil.createBSTtree(genereateRandomArray(nodeCount,
				maxInt));
		System.out.println(H(root));

	}

	public static int pow2(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		if (n == 0) {
			return 1;
		}
		return 2 << (n - 1);
	}

	public static int log2(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		return 31 - Integer.numberOfLeadingZeros(n);
	}

	public static Node createFullTree(int H) {
		Node root = Node.createNode(1);// root is level 1
		Node[] currentLevelLeaves = new Node[] { root };
		int currentLevel = 1; // root is level 1
		while (currentLevel <= H - 1) {
			Node[] nextLevelLeaves = new Node[pow2(currentLevel)];

			for (int i = 0; i < currentLevelLeaves.length; i++) {
				Node n = currentLevelLeaves[i];
				n.L = Node.createNode(n.data * 2);
				n.R = Node.createNode(n.data * 2 + 1);

				nextLevelLeaves[2 * i] = n.L;
				nextLevelLeaves[2 * i + 1] = n.R;
			}
			currentLevel++;
			currentLevelLeaves = nextLevelLeaves;
		}
		return root;
	}

	public static void main(String[] args) {
		test_H();
	}
}
