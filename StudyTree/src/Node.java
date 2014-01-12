public class Node {
	public int data;
	public Node L;
	public Node R;

	public static Node createNode(int data) {
		Node n = new Node();
		n.data = data;
		return n;
	}

	@Override
	public String toString() {
		return data + "";
	}
}
