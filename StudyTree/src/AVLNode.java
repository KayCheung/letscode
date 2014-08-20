public class AVLNode implements VisibleNode {
	public AVLNode L;
	public AVLNode R;
	public int data;
	public BalanceFactor bf;

	@Override
	public VisibleNode left() {
		return L;
	}

	@Override
	public VisibleNode right() {
		return R;
	}

	static enum BalanceFactor {
		LH, // left higher
		RH, // right higher
		EH; // equal higher
	}

	@Override
	public String presentation() {
		return data + "";
	}

	@Override
	public VisibleNode copyNode() {
		AVLNode n = new AVLNode();
		n.data = this.data;
		return n;
	}

	@Override
	public void setLeft(VisibleNode left) {
		L = (AVLNode) left;
	}

	@Override
	public void setRight(VisibleNode right) {
		R = (AVLNode) right;
	}
}
