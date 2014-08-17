public class AVLNode implements VisibleNode {
	public AVLNode L;
	public AVLNode R;
	public int data;
	public BalanceFactor bf;

	@Override
	public VisibleNode L() {
		return L;
	}

	@Override
	public VisibleNode R() {
		return R;
	}

	@Override
	public int data() {
		return data;
	}

	static enum BalanceFactor {
		LH, // left higher
		RH, // right higher
		EH; // equal higher
	}
}
