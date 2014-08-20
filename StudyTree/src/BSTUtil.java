public class BSTUtil {
	public static Node insertIntoBST(Node root, int data) {
		if (root == null) {
			return Node.createNode(data);
		}

		boolean asRightChild = false;

		Node parent = null;
		Node tmp = root;
		while (tmp != null) {
			parent = tmp;
			if (data < tmp.data) {
				tmp = tmp.L;
				asRightChild = false;
			} else {
				tmp = tmp.R;
				asRightChild = true;
			}
		}

		if (asRightChild) {
			parent.R = Node.createNode(data);
		} else {
			parent.L = Node.createNode(data);
		}
		return root;
	}

	/**
	 * We suppose we've found the node on the tree. Now, delete this found node
	 * <code>nodeToBeDeleted</code>
	 * 
	 * @param root
	 * @param nodeToBeDeleted
	 * @return
	 */
	public static Node deleteFromBST(Node root, Node nodeToBeDeleted) {
		return null;
	}
}
