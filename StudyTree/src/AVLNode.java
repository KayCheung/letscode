public class AVLNode implements VisibleNode {
	public AVLNode L;
	public AVLNode R;
	public int data;
	public BalanceFactor bf;

	@Override
	public VisibleNode leftChild() {
		return L;
	}

	@Override
	public VisibleNode rightChild() {
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
}
