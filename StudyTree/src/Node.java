public class Node implements VisibleNode {
	public int data;
	public Node L;
	public Node R;

	public static Node createNode(int data) {
		Node n = new Node();
		n.data = data;
		return n;
	}

	public VisibleNode L() {
		return L;
	}

	public VisibleNode R() {
		return R;
	}

	public int data() {
		return data;
	}

	@Override
	public String toString() {
		return data + "";
	}
}
