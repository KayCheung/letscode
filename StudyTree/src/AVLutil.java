public class AVLutil {

	/**
	 * <pre>
	 * 			O1							O2
	 * 		O2				-->			O3		O1
	 * 
	 * 	O3
	 * </pre>
	 * 
	 * naming convenience: n's level 1, son's level 2, grandson's level 3
	 * 
	 * @param n_1
	 */
	public static void R_Rotate(AVLNode n_1_Parent, AVLNode n_1) {
		System.out.println("R_Rotate: n_1.bf=" + n_1.bf);
		AVLNode l_2 = n_1.L;
		AVLNode lr_3 = l_2.R;

		l_2.R = n_1;
		n_1.L = lr_3;

		if (n_1_Parent != null) {
			// n_1 is leftChild
			if (n_1_Parent.L == n_1) {
				n_1_Parent.L = l_2;
			} else {
				n_1_Parent.R = l_2;
			}
		}
	}

	/**
	 * <pre>
	 * 	O1										O2
	 * 		O2				-->				O1		O3
	 * 
	 * 			O3
	 * </pre>
	 * 
	 * 
	 * @param n_1
	 */
	public static void L_Rotate(AVLNode n_1_Parent, AVLNode n_1) {
		System.out.println("L_Rotate, n_1.bf=" + n_1.bf);
		AVLNode r_2 = n_1.R;
		AVLNode rl_3 = r_2.L;

		r_2.L = n_1;
		n_1.R = rl_3;

		if (n_1_Parent != null) {
			if (n_1_Parent.L == n_1) {
				n_1_Parent.L = r_2;
			} else {
				n_1_Parent.R = r_2;
			}
			n_1_Parent.R = r_2;
		}
	}

	/**
	 * n_1.bf == LH
	 * 
	 * 
	 * @param n_1_Parent
	 * @param n_1
	 */
	public static void leftBalance(AVLNode n_1_Parent, AVLNode n_1) {
		AVLNode l_2 = n_1.L;

		switch (l_2.bf) {
		// never happen
		case EH:
			break;
		case LH: // new child is on l_2's left
			n_1.bf = AVLNode.BalanceFactor.EH;
			l_2.bf = AVLNode.BalanceFactor.EH;
			R_Rotate(n_1_Parent, n_1);
			break;
		case RH: // new child is on l_2's right
			AVLNode lr_3 = l_2.R;
			switch (lr_3.bf) {
			case LH:
				n_1.bf = AVLNode.BalanceFactor.RH;
				l_2.bf = AVLNode.BalanceFactor.EH;
				break;
			case EH:
				n_1.bf = AVLNode.BalanceFactor.EH;
				l_2.bf = AVLNode.BalanceFactor.EH;
				break;
			case RH:
				n_1.bf = AVLNode.BalanceFactor.EH;
				l_2.bf = AVLNode.BalanceFactor.LH;
				break;
			}
			lr_3.bf = AVLNode.BalanceFactor.EH;
			L_Rotate(n_1, l_2);
			R_Rotate(n_1_Parent, n_1);
			break;
		}
	}

	/**
	 * n_1.bf == RH
	 * 
	 * 
	 * @param n_1_Parent
	 * @param n_1
	 */
	public static void rightBalance(AVLNode n_1_Parent, AVLNode n_1) {
	}

	/**
	 * 1. Always insert successfully
	 * 
	 * 2. true: tree grows taller, false: NOT taller
	 * 
	 * @param node
	 * @param newValue
	 * @return
	 */
	public static boolean insert(AVLNode nodeParent, AVLNode node, int newValue) {
		// do insertion
		if (node == null) {
			AVLNode createdNewNode = new AVLNode();
			createdNewNode.data = newValue;
			createdNewNode.L = createdNewNode.R = null;
			createdNewNode.bf = AVLNode.BalanceFactor.EH;
			if (nodeParent != null) {
				// new value is less
				if (newValue < nodeParent.data) {
					nodeParent.L = createdNewNode;
				}
				// new value is greater/equal to
				else {
					nodeParent.R = createdNewNode;
				}
			}
			// Yes, tree grows taller
			return true;
		}
		// find node, and then do insertion
		else {
			// new value is less.
			// Should on node's left
			if (newValue < node.data) {
				// It's apparently possible that appending a leaf on a tree does
				// not increase this tree height
				// node's left subtree grows taller.
				boolean bGrowTaller = insert(node, node.L, newValue);
				if (bGrowTaller == true) {
					// Based on node's ORIGINAL bf (before appending the new
					// node)
					// do balance if necessary
					switch (node.bf) {
					case LH:
						leftBalance(nodeParent, node);
						// tree tree with <code>node</code> as root does grow
						// taller, but after we do balance, it's
						// height does not change
						return false;
					case EH:
						node.bf = AVLNode.BalanceFactor.LH;
						return true;
					case RH:
						node.bf = AVLNode.BalanceFactor.EH;
						return false;
					}
				}
			}
			// new value is greater or equal to
			else {
				return false;
			}
		}
		// never reach
		return false;
	}

	public static AVLNode createAVLTree(int[] array) {
		return null;
	}
}
