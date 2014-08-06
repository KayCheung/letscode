import java.util.ArrayList;
import java.util.Arrays;

class Node {
	public Node(int value, Node parent, int index) {
		this.value = value;
		this.parent = parent;
		this.index = index;
		
	}

	public int value;
	public Node parent;
	public int index;
}

public class TreeSelectionSort_TournamentSort {
	public static ArrayList<Node> convertArray2Tree(int[] array,
			int startIndex, int endIndex) {
		ArrayList<Node> listLeaves = new ArrayList<Node>(endIndex - startIndex
				+ 1);
		for (int i = startIndex; i <= endIndex; i++) {
			listLeaves.add(new Node(array[i], null, i-startIndex));
		}
		buildTree(listLeaves);
		// Since here, tree build done
		return listLeaves;
	}

	private static Node buildTree(ArrayList<Node> listChildren) {
		int size = listChildren.size();
		if (size == 1) {
			return listChildren.get(0);
		}

		ArrayList<Node> listParent = new ArrayList<Node>((size / 2) + 1);

		for (int i = 0; i < size; i++) {
			Node evenN = listChildren.get(i);
			int oddIndex = i + 1;

			Node nodeParent = null;
			// still available
			if (oddIndex <= size - 1) {
				Node oddN = listChildren.get(oddIndex);
				nodeParent = new Node((evenN.value < oddN.value ? evenN.value
						: oddN.value), null, i/2);
				oddN.parent = nodeParent;
			} else {
				nodeParent = new Node(evenN.value, null, i/2);
			}

			evenN.parent = nodeParent;
			listParent.add(nodeParent);
		}

		return buildTree(listParent);
	}

	public static int[] treeSelectionSort(int[] array, int startIndex,
			int endIndex) {
		ArrayList<Node> listLeaves = new ArrayList<Node>(endIndex - startIndex
				+ 1);
		for (int i = startIndex; i <= endIndex; i++) {
			listLeaves.add(new Node(array[i], null, i-startIndex));
		}
		int[] result = new int[listLeaves.size()];

		Node root = buildTree(listLeaves);
		result[0] = root.value;
		Node lastNode = root;
		lastNode.value = Integer.MAX_VALUE;
		
		for (int i = 1; i < listLeaves.size(); i++) {
			if(lastNode.index/2==0){
				
			}else{
				
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] array = { 3, 5, 2, 9, 8, 0, 3, 5, 8, 4, 7 };
		int[] result = treeSelectionSort(array, 3, array.length - 1);
		System.out.println(Arrays.toString(result));
	}
}
