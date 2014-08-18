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
	public static AVLNode R_Rotate(AVLNode n_1) {
		System.out.println("R_Rotate: n_1.bf=" + n_1.bf);
		AVLNode l_2 = n_1.L;
		AVLNode lr_3 = l_2.R;

		l_2.R = n_1;
		n_1.L = lr_3;
		return l_2;
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
	public static AVLNode L_Rotate(AVLNode n_1) {
		System.out.println("L_Rotate, n_1.bf=" + n_1.bf);
		AVLNode r_2 = n_1.R;
		AVLNode rl_3 = r_2.L;

		r_2.L = n_1;
		n_1.R = rl_3;
		return r_2;
	}

	/**
	 * Precondition: n_1 is already LH
	 * 
	 * add a new leaf to n_1's left/right subtree
	 * 
	 * 
	 * @param n_1_Parent
	 * @param n_1
	 */
	public static AVLNode leftBalance(AVLNode n_1) {
		// n_1 is LH
		// add leaf to n_1.L 's lef
		AVLNode l_2 = n_1.L;
		switch (l_2.bf) {
		// never happen
		case EH:
			break;
		case LH: // new child is on l_2's left
			n_1.bf = AVLNode.BalanceFactor.EH;
			l_2.bf = AVLNode.BalanceFactor.EH;
			return R_Rotate(n_1);
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
			AVLNode newRoot = L_Rotate(l_2);
			R_Rotate(newRoot);
		}
		return null;
	}

	/**
	 * n_1.bf == RH
	 * 
	 * 
	 * @param n_1_Parent
	 * @param n_1
	 */
	public static AVLNode rightBalance(AVLNode n_1) {
		// n_1 is LH
		// add leaf to n_1.L 's lef
		AVLNode r_2 = n_1.R;
		switch (r_2.bf) {
		// never happen
		case EH:
			break;
		case RH: // new child is on l_2's left
			n_1.bf = AVLNode.BalanceFactor.EH;
			r_2.bf = AVLNode.BalanceFactor.EH;
			return R_Rotate(n_1);
		case LH: // new child is on l_2's right
			AVLNode rl_3 = r_2.L;
			switch (rl_3.bf) {
			case LH:
				n_1.bf = AVLNode.BalanceFactor.LH;
				r_2.bf = AVLNode.BalanceFactor.EH;
				break;
			case EH:
				n_1.bf = AVLNode.BalanceFactor.EH;
				r_2.bf = AVLNode.BalanceFactor.EH;
				break;
			case RH:
				n_1.bf = AVLNode.BalanceFactor.EH;
				r_2.bf = AVLNode.BalanceFactor.RH;
				break;
			}
			rl_3.bf = AVLNode.BalanceFactor.EH;
			AVLNode newRoot = R_Rotate(r_2);
			L_Rotate(newRoot);
		}
		return null;

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
	public static boolean insert(AVLNode node, int newValue) {
		// do insertion
		if (node == null) {
			AVLNode createdNewNode = new AVLNode();
			createdNewNode.data = newValue;
			createdNewNode.L = createdNewNode.R = null;
			createdNewNode.bf = AVLNode.BalanceFactor.EH;
			// Yes, tree grows taller
			return true;
		}
		// find node, and do insertion
		else {
			// new value is less.
			// Should on node's left
			if (newValue < node.data) {
				// It's apparently possible that appending a leaf on a tree does
				// not increase this tree height
				// node's left subtree grows taller.
				boolean bGrowTaller = insert(node.L, newValue);
				if (bGrowTaller == true) {
					// if grows taller, we need to balance node.L's parent
					// do balance if necessary
					switch (node.bf) {
					case LH:
						AVLNode newRoot = leftBalance(node);
						node.L = newRoot;
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
				// It's apparently possible that appending a leaf on a tree does
				// not increase this tree height
				// node's left subtree grows taller.
				boolean bGrowTaller = insert(node.R, newValue);
				if (bGrowTaller == true) {
					// if grows taller, we need to balance node.L's parent
					// do balance if necessary
					switch (node.bf) {
					case RH:
						AVLNode newRoot = rightBalance(node);
						node.R = newRoot;
						// tree tree with <code>node</code> as root does grow
						// taller, but after we do balance, it's
						// height does not change
						return false;
					case EH:
						node.bf = AVLNode.BalanceFactor.RH;
						return true;
					case LH:
						node.bf = AVLNode.BalanceFactor.EH;
						return false;
					}
				}
			}
		}
		// never reach
		return false;
	}

	public static AVLNode createAVLTree(int[] array) {
		array = new int[]{10, 20, 30, 40};
		for (int i = 0; i < array.length; i++) {
			insert(null, array[i]);
			
		}
		return null;
	}
}
