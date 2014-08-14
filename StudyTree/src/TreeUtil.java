import java.util.Random;

public class TreeUtil {

	public static Node createBST(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		Node root = Node.createNode(array[0]);
		for (int i = 1; i < array.length; i++) {
			int nowData = array[i];
			Node parent = null;
			Node tmpNode = root;
			boolean asRightChild = true;
			while (tmpNode != null) {
				parent = tmpNode;
				// to be inserted data is bigger
				if (nowData > tmpNode.data) {
					tmpNode = tmpNode.R;
					asRightChild = true;
				} else {
					tmpNode = tmpNode.L;
					asRightChild = false;
				}
			}
			// since here, n == null
			if (asRightChild) {
				parent.R = Node.createNode(nowData);
			} else {
				parent.L = Node.createNode(nowData);
			}
		}
		return root;
	}

	private static void test_createBST() {
		Random r = new Random(47);
		int maxInt = 51;
		int nodeCount = 10;
		int[] array = new int[nodeCount];
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(maxInt);
		}

		Node root = createBST(array);
		System.out.println(root);
	}

	public static Node createFullTree(int H) {
		Node root = Node.createNode(1);// root is level 1
		Node[] currentLevelLeaves = new Node[] { root };
		int currentLevel = 1; // root is level 1
		while (currentLevel <= H - 1) {
			Node[] nextLevelLeaves = new Node[pow2(currentLevel)];

			for (int i = 0; i < currentLevelLeaves.length; i++) {
				Node n = currentLevelLeaves[i];
				n.L = Node.createNode(n.data * 2);
				n.R = Node.createNode(n.data * 2 + 1);

				nextLevelLeaves[2 * i] = n.L;
				nextLevelLeaves[2 * i + 1] = n.R;
			}
			currentLevel++;
			currentLevelLeaves = nextLevelLeaves;
		}
		return root;
	}

	public static int H(VisibleNode root) {
		// root node is in level 1 (NOTE: 1, not 0)
		if (root == null) {
			return 0;
		}
		int subLeftH = H(root.L());
		int totalLeftH = subLeftH + 1;
		int subRightH = H(root.R());
		int totalRightH = subRightH + 1;

		return (totalLeftH > totalRightH) ? totalLeftH : totalRightH;
	}

	private static void test_H() {
		Random r = new Random();
		int maxInt = 51;
		int nodeCount = 10;
		int[] array = new int[nodeCount];
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(maxInt);
		}

		Node root = createBST(array);

		System.out.println(H(root));

	}

	public static void main(String[] args) {
		test_createBST();
		test_H();
	}

	public static int pow2(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		if (n == 0) {
			return 1;
		}
		return 2 << (n - 1);
	}

	public static int log2(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		return 31 - Integer.numberOfLeadingZeros(n);
	}
}
