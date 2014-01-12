public class AVLNode extends Node {
	public int bf;

	public static AVLNode createAVLNode(int data) {
		AVLNode n = new AVLNode();
		n.data = data;
		n.bf = 0;
		return n;
	}

	@Override
	public String toString() {
		return data + "";
	}
}
