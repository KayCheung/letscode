import java.util.Arrays;

class Node {
	public Node(Node parent, int orgnIndex, Node cpNode) {
		this.orgnIndex = orgnIndex;
		this.parent = parent;
		this.cpNode = cpNode;
	}

	public int orgnIndex;
	public Node parent;
	public Node cpNode;
	public boolean bMax = false;
}

public class TreeSelectionSort_TournamentSort {

	public static void main(String[] args) {
		int[] array = { 3, 5, 2, 9, 8, 0, 3, 5, 8, 4, 7 };
		int[] resultIndex = treeSelectionSort(array, 0, array.length - 1);
		for (int i = 0; i < resultIndex.length; i++) {
			System.out.print(array[resultIndex[i]] + ", ");
		}
	}

	public static int[] treeSelectionSort(int[] arrayToBeSort, int startIndex,
			int endIndex) {
		Node[] nodeLeaves = new Node[endIndex - startIndex + 1];
		int nodeIndex = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			nodeLeaves[nodeIndex] = new Node(null, i, null);
			nodeIndex++;
		}

		Node root = buildTree(arrayToBeSort, nodeLeaves);
		int[] result = traveseLeaves(arrayToBeSort, nodeLeaves, root,
				startIndex, endIndex);
		return result;
	}

	/**
	 * <code>node1</code> will NEVER be null
	 * 
	 * @param arrayToBeSort
	 * @param parentN
	 * @param node1
	 * @param node2
	 */
	private static Node traveseUp2SetRootIndex(int[] arrayToBeSort,
			Node parentN, Node node1, Node node2) {
		if (parentN == null) {
			return node1;
		}
		// node2 NOT exist
		if (node2 == null) {
			if (node1.bMax) {
				parentN.bMax = true;
			} else {
				parentN.orgnIndex = node1.orgnIndex;
			}
		}
		// Both evenN and oddN are available
		else {
			int value1 = arrayToBeSort[node1.orgnIndex];
			int value2 = arrayToBeSort[node2.orgnIndex];
			// both are max
			if (node1.bMax && node2.bMax) {
				parentN.bMax = true;
			} else {
				// neither node1 nor node2 is max
				if (node1.bMax == false && node2.bMax == false) {
					parentN.orgnIndex = value1 < value2 ? node1.orgnIndex
							: node2.orgnIndex;
				} else {
					// either node1 or node2 is max
					parentN.orgnIndex = node1.bMax ? node2.orgnIndex
							: node1.orgnIndex;
				}
			}
		}

		node1 = parentN;
		node2 = node1.cpNode;
		parentN = parentN.parent;

		return traveseUp2SetRootIndex(arrayToBeSort, parentN, node1, node2);
	}

	private static Node buildTree(int[] arrayToBeSort, Node[] arrayChildren) {
		setCPRelation(arrayChildren);
		int totalCount = arrayChildren.length;
		if (totalCount == 1) {
			return arrayChildren[0];
		}

		Node[] arrayParent = new Node[ceilDivideBy2(totalCount)];
		int curParentIndex = 0;
		for (int i = 0; i < totalCount;) {
			Node evenN = arrayChildren[i];
			int oddIndex = i + 1;

			Node nodeParent = null;
			// still available
			if (oddIndex <= totalCount - 1) {
				Node oddN = arrayChildren[oddIndex];
				int evenValue = arrayToBeSort[evenN.orgnIndex];
				int oddValue = arrayToBeSort[oddIndex];
				int parentNodeOrgnIndex = evenValue < oddValue ? evenN.orgnIndex
						: oddN.orgnIndex;
				nodeParent = new Node(null, parentNodeOrgnIndex, null);
				oddN.parent = nodeParent;
			} else {
				nodeParent = new Node(null, evenN.orgnIndex, null);
			}

			evenN.parent = nodeParent;

			arrayParent[curParentIndex] = nodeParent;
			curParentIndex++;
			i = i + 2;
		}

		return buildTree(arrayToBeSort, arrayParent);
	}

	private static void setCPRelation(Node[] listChildren) {
		int size = listChildren.length;
		for (int i = 0; i < size;) {
			Node evenN = listChildren[i];
			int oddIndex = i + 1;
			// still available
			if (oddIndex <= size - 1) {
				Node oddN = listChildren[oddIndex];
				evenN.cpNode = oddN;
				oddN.cpNode = evenN;
			} else {
				evenN.cpNode = null;
			}
			i = i + 2;
		}
	}

	public static int[] traveseLeaves(int[] arrayToBeSort, Node[] nodeLeaves,
			Node root, int startIndex, int endIndex) {
		int[] resultIndex = new int[endIndex - startIndex + 1];
		// done
		if (root.bMax) {
			return resultIndex;
		}
		Node leaf = nodeLeaves[root.orgnIndex - startIndex];
		leaf.bMax = true;
		Node theRoot = traveseUp2SetRootIndex(arrayToBeSort, leaf.parent, leaf,
				leaf.cpNode);
		return traveseLeaves(arrayToBeSort, nodeLeaves, theRoot, startIndex,
				endIndex);
	}

	public static int ceilDivideBy2(int i) {
		if (i % 2 == 0) {
			return i >> 1;
		}
		return (i >> 1) + 1;
	}

	public static int floorDivideBy2(int i) {
		return i >> 1;
	}
}
