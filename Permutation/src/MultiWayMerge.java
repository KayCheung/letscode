class LSNode {
	private int orgnIndex;
	private boolean bMax;
	private boolean bMin;

	public LSNode(int orgnIndex) {
		setOrgnIndex(orgnIndex);
	}

	public static LSNode createMinLeaf() {
		LSNode lf = new LSNode(-1);
		lf.setMin();
		return lf;
	}

	public boolean bMax() {
		return bMax;
	}

	public boolean bMin() {
		return bMin;
	}

	public int orgnIndex() {
		return orgnIndex;
	}

	public void setOrgnIndex(int orgnIndex) {
		bMax = false;
		bMin = false;
		this.orgnIndex = orgnIndex;
	}

	public void setMax() {
		bMax = true;
		bMin = false;
		orgnIndex = -1;
	}

	public void setMin() {
		bMin = true;
		bMax = false;
		orgnIndex = -1;
	}
}

public class MultiWayMerge {

	public static void main(String[] args) {
		int[] b0 = { 10, 15, 16 };
		int[] b1 = { 9, 18, 20 };
		int[] b2 = { 20, 22, 40 };
		int[] b3 = { 6, 15, 25 };
		int[] b4 = { 12, 37, 48 };
		createLoserTree(new int[] { 10, 9, 20, 6, 12 });
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

	private static LSNode[] constructLeaves(int[] arrayToBeSearch) {
		LSNode[] arrayLeaves = new LSNode[arrayToBeSearch.length];
		for (int i = 0; i < arrayLeaves.length; i++) {
			arrayLeaves[i] = new LSNode(i);
		}
		return arrayLeaves;
	}

	private static int[] createLoserTree(int[] arrayToBeSearch) {
		int k = arrayToBeSearch.length;

		int[] loserTree = new int[k];
		LSNode[] arrayLeaves = constructLeaves(k);
		for (int i = k - 1; i >= 0; i--) {
			adjust(loserTree, arrayLeaves, i, arrayToBeSearch);
		}

		return loserTree;
	}

	private static boolean greatThan(LSNode A, LSNode B, int[] arrayToBeSearch) {
		if (A.bMax()) {
			return true;
		}
		if (A.bMin()) {
			return false;
		}
		// a is neither max nor min
		if (B.bMax()) {
			return false;
		}
		if (B.bMin()) {
			return true;
		}
		// b is neither max nor min
		return arrayToBeSearch[A.orgnIndex()] > arrayToBeSearch[B.orgnIndex()];
	}

	private static void adjust(int[] loserTree, LSNode[] arrayLeaves,
			int needAdjustLeafIndex, int[] arrayToBeSearch) {
		int k = arrayLeaves.length;
		// 1. LoserTree's index
		// 2. In this LoserTree's cell, stored a leafIndex (this leafIndex CAN
		// be changed)
		int lsParentIndex = (needAdjustLeafIndex + k) >> 1;

		while (lsParentIndex > 0) {
			LSNode lfN = arrayLeaves[needAdjustLeafIndex];
			LSNode lfParentN = arrayLeaves[loserTree[lsParentIndex]];
			// leaf bigger. Loser leaf, let it in parentCell
			if (greatThan(lfN, lfParentN, arrayToBeSearch)) {
				// exchange: leafIndex<-->loserTree[lsIndex]
				int tmp = loserTree[lsParentIndex];
				loserTree[lsParentIndex] = needAdjustLeafIndex;
				needAdjustLeafIndex = tmp;

				lsParentIndex = lsParentIndex >> 1;
			}
		}

		loserTree[0] = needAdjustLeafIndex;
	}

}
