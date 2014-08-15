import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ShowTreeComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private int verticalGap;
	private int bottomLevelHorizontalGap;
	private int startX;
	private int startY;

	private FullTreeNode[] arrayFullTree;

	static class FullTreeNode {
		public static int radius = 13;
		public VisibleNode node;
		public int X;
		public int Y;
		public boolean V = false;
	}

	public ShowTreeComponent(int verticalGap, int bottomLevelHorizontalGap,
			VisibleNode root, int startX, int startY) {
		this.verticalGap = verticalGap;
		this.bottomLevelHorizontalGap = bottomLevelHorizontalGap;
		this.startX = startX;
		this.startY = startY;

		arrayFullTree = arbitrary2full(root);
	}

	public ShowTreeComponent(VisibleNode root) {
		this.verticalGap = 65;
		this.bottomLevelHorizontalGap = 15;
		this.startX = 20;
		this.startY = 30;

		arrayFullTree = arbitrary2full(root);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gCopy = g.create();

		int verticalGap = this.verticalGap;
		int bottomLevelHorizontalGap = this.bottomLevelHorizontalGap;
		int startX = this.startX;
		int startY = this.startY;

		calculateXY(verticalGap, bottomLevelHorizontalGap, arrayFullTree,
				startX, startY);
		drawTreeGraphic(gCopy, arrayFullTree);

		gCopy.dispose();
	}

	private void calculateXY(int verticalGap, int bottomLevelHorizontalGap,
			FullTreeNode[] listFullTree, int startX, int startY) {
		int totalLevel = TreeUtil.log2(listFullTree.length);// begins from 1
		// currentLevel begins from 1
		for (int currentLevel = totalLevel; currentLevel >= 1; currentLevel--) {
			int Y = startY + ((currentLevel - 1) * verticalGap);

			int leftestXInThisLevel = 0;
			int firstNodeIndexInThisLevel = TreeUtil.pow2(currentLevel - 1);
			// bottom level
			if (currentLevel == totalLevel) {
				leftestXInThisLevel = startX;
			}
			// NOT bottom level
			else {
				int leftChildIndex = firstNodeIndexInThisLevel * 2;
				int rightChildIndex = leftChildIndex + 1;
				FullTreeNode leftChild = listFullTree[leftChildIndex];
				FullTreeNode rightChild = listFullTree[rightChildIndex];
				leftestXInThisLevel = (leftChild.X + rightChild.X) >> 1;
			}

			int totalNodeCountInThisLevel = TreeUtil.pow2(currentLevel - 1);
			int currentLevelHorizonGap = bottomLevelHorizontalGap
					* TreeUtil.pow2(totalLevel - currentLevel);
			for (int i = 0; i < totalNodeCountInThisLevel; i++) {
				FullTreeNode nodeInThisLevel = listFullTree[firstNodeIndexInThisLevel
						+ i];
				nodeInThisLevel.Y = Y;
				nodeInThisLevel.X = leftestXInThisLevel
						+ currentLevelHorizonGap * i;
			}
		}
	}

	private void calculateXY_version2(int verticalGap,
			int bottomLevelHorizontalGap, FullTreeNode[] listFullTree,
			int startX, int startY) {
		int totalLevel = TreeUtil.log2(listFullTree.length);// begins from 1
		// currentLevel begins from 1
		for (int currentLevel = totalLevel; currentLevel >= 1; currentLevel--) {
			int Y = startY + ((currentLevel - 1) * verticalGap);

			int leftestXInThisLevel = 0;
			int firstNodeIndexInThisLevel = TreeUtil.pow2(currentLevel - 1);
			// bottom level
			if (currentLevel == totalLevel) {
				leftestXInThisLevel = startX;
			}
			// NOT bottom level
			else {
				int leftChildIndex = firstNodeIndexInThisLevel * 2;
				int rightChildIndex = leftChildIndex + 1;
				FullTreeNode leftChild = listFullTree[leftChildIndex];
				FullTreeNode rightChild = listFullTree[rightChildIndex];
				leftestXInThisLevel = (leftChild.X + rightChild.X) >> 1;
			}

			int totalNodeCountInThisLevel = TreeUtil.pow2(currentLevel - 1);
			int currentLevelHorizonGap = bottomLevelHorizontalGap
					* TreeUtil.pow2(totalLevel - currentLevel);
			for (int i = 0; i < totalNodeCountInThisLevel; i++) {
				FullTreeNode nodeInThisLevel = listFullTree[firstNodeIndexInThisLevel
						+ i];
				nodeInThisLevel.Y = Y;
				nodeInThisLevel.X = leftestXInThisLevel
						+ currentLevelHorizonGap * i;
			}
		}
	}

	protected void drawTreeGraphic(Graphics gCopy, FullTreeNode[] listFullTree) {
		drawAllTreeNode(gCopy, listFullTree);
		drawAllTreeLine(gCopy, listFullTree);
	}

	private void drawAllTreeNode(Graphics gCopy, FullTreeNode[] listFullTree) {
		Color outlineColor = null, fillColor = null, fontColor = null;
		outlineColor = Color.red;
		fillColor = Color.yellow;
		fontColor = Color.black;
		// draw each tree node
		for (int i = 1; i < listFullTree.length; i++) {
			FullTreeNode fullNode = listFullTree[i];
			if (fullNode.V == true) {
				drawEachTreeNode(gCopy, fullNode.X, fullNode.Y,
						FullTreeNode.radius, outlineColor, fillColor,
						fontColor, fullNode.node.data() + "");
			}
		}
	}

	private void drawEachTreeNode(Graphics gCopy, int x, int y, int radius,
			Color outlineColor, Color fillColor, Color fontColor, String str) {
		int width = radius * 2;
		int height = width;
		// draw outline
		gCopy.setColor(outlineColor);
		gCopy.drawOval(x, y, width, height);

		// fill Oval(outline is kept)
		gCopy.setColor(fillColor);
		gCopy.fillOval(x + 1, y + 1, width - 2, height - 2);

		// draw string
		int strWidth = SwingUtilities.computeStringWidth(
				getFontMetrics(getFont()), str);
		int strAscent = getFontMetrics(getFont()).getAscent();
		gCopy.setColor(fontColor);
		gCopy.drawString(str, (x + (width >> 1) - (strWidth >> 1)), (y
				+ (height >> 1) + (strAscent >> 1)));
	}

	protected void drawAllTreeLine(Graphics gCopy, FullTreeNode[] list) {
		Color lineColor = Color.black;
		// draw outline
		gCopy.setColor(lineColor);
		for (int nodeIndex = 1; nodeIndex < list.length / 2; nodeIndex++) {
			FullTreeNode cur = list[nodeIndex];
			if (cur.V == false) {
				continue;
			}
			int leftChildIndex = nodeIndex * 2;
			int rightChildIndex = leftChildIndex + 1;
			FullTreeNode leftChild = list[leftChildIndex];
			FullTreeNode rightChild = list[rightChildIndex];

			if (leftChild.V == true) {
				gCopy.drawLine(cur.X + FullTreeNode.radius, cur.Y
						+ FullTreeNode.radius, leftChild.X
						+ FullTreeNode.radius, leftChild.Y
						+ FullTreeNode.radius);
			}
			if (rightChild.V == true) {
				gCopy.drawLine(cur.X + FullTreeNode.radius, cur.Y
						+ FullTreeNode.radius, rightChild.X
						+ FullTreeNode.radius, rightChild.Y
						+ FullTreeNode.radius);
			}
		}
	}

	public static FullTreeNode[] arbitrary2full(VisibleNode root) {
		int height = TreeUtil.H(root);
		int arrayLength = TreeUtil.pow2(height);
		FullTreeNode[] arrayFullTree = new FullTreeNode[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			arrayFullTree[i] = new FullTreeNode();
		}
		int index = 1;
		correspondingFullTreeIndex(root, index, arrayFullTree);
		return arrayFullTree;
	}

	private static void correspondingFullTreeIndex(VisibleNode node, int index,
			FullTreeNode[] arrayFullTree) {
		if (node != null) {
			arrayFullTree[index].node = node;
			arrayFullTree[index].V = true;
			correspondingFullTreeIndex(node.L(), index << 1, arrayFullTree);
			correspondingFullTreeIndex(node.R(), (index << 1) + 1,
					arrayFullTree);
		}
	}

	public static void test_One_Component() {
		Random r = new Random();
		int maxInt = 51;
		int nodeCount = 14;
		int[] array = new int[nodeCount];
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(maxInt);
		}

		Node root = TreeUtil.createBST(array);

		int verticalGap = 65;
		int bottomLevelHorizontalGap = 20;
		int startX = 20;
		int startY = 30;

		JFrame frm = new JFrame();
		frm.getContentPane().add(
				new ShowTreeComponent(verticalGap, bottomLevelHorizontalGap,
						root, startX, startY));
		frm.setVisible(true);
		frm.setSize(700, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void test_FullTree() {
		Node root = createFullTree(5);

		int verticalGap = 65;
		int bottomLevelHorizontalGap = 30;
		int startX = 20;
		int startY = 30;

		JFrame frm = new JFrame();
		frm.getContentPane().add(
				new ShowTreeComponent(verticalGap, bottomLevelHorizontalGap,
						root, startX, startY));
		frm.setVisible(true);
		frm.setSize(700, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static Node createTree(int nodeCount) {
		Random r = new Random(47);
		int maxInt = 51;
		int[] array = new int[nodeCount];
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(maxInt);
		}

		Node root = TreeUtil.createBST(array);
		return root;
	}

	private static Node createFullTree(int H) {
		return TreeUtil.createFullTree(H);
	}

	public static void test_Multiple_Component() {
		Node root_9 = createTree(9);
		Node root_7 = createTree(7);
		Node root_8 = createTree(8);

		int verticalGap = 65;
		int bottomLevelHorizontalGap = 25;
		int startX = 30;
		int startY = 20;

		ShowTreeComponent stc_9 = new ShowTreeComponent(verticalGap,
				bottomLevelHorizontalGap, root_9, startX, startY);
		ShowTreeComponent stc_7 = new ShowTreeComponent(verticalGap,
				bottomLevelHorizontalGap, root_7, startX, startY);
		ShowTreeComponent stc_8 = new ShowTreeComponent(verticalGap,
				bottomLevelHorizontalGap, root_8, startX, startY);

		JFrame frm = new JFrame();
		Container container = frm.getContentPane();
		container.setLayout(new GridLayout(3, 1));

		container.add(stc_9);
		container.add(stc_8);
		container.add(stc_7);
		// container.add(new JLabel());

		frm.setVisible(true);
		frm.setSize(500, 1000);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// test_One_Component();
		// test_Multiple_Component();
		test_FullTree();
	}

}
