public class Node implements VisibleNode {
	public int data;
	public Node L;
	public Node R;

	public static Node createNode(int data) {
		Node n = new Node();
		n.data = data;
		return n;
	}

	public VisibleNode leftChild() {
		return L;
	}

	public VisibleNode rightChild() {
		return R;
	}

	public String presentation() {
		return data + "";
	}

	@Override
	public String toString() {
		return data + "";
	}
}
