class Leaf {
	private int orgnIndex;
	private boolean bMax;
	private boolean bMin;

	public Leaf(int orgnIndex) {
		setOrgnIndex(orgnIndex);
	}

	public static Leaf createMinLeaf() {
		Leaf lf = new Leaf(-1);
		lf.setMin();
		return lf;
	}

	public boolean bMax() {
		return bMax;
	}

	public boolean bMin() {
		return bMin;
	}

	public int orgnIndex() {
		return orgnIndex;
	}

	public void setOrgnIndex(int orgnIndex) {
		bMax = false;
		bMin = false;
		this.orgnIndex = orgnIndex;
	}

	public void setMax() {
		bMax = true;
		bMin = false;
		orgnIndex = -1;
	}

	public void setMin() {
		bMin = true;
		bMax = false;
		orgnIndex = -1;
	}
}

public class MultiWayMerge {

	public static void main(String[] args) {
		int[] b0 = { 10, 15, 16 };
		int[] b1 = { 9, 18, 20 };
		int[] b2 = { 20, 22, 40 };
		int[] b3 = { 6, 15, 25 };
		int[] b4 = { 12, 37, 48 };
	}

	private static Leaf[] constructLeaves(int k) {
		Leaf[] arrayLeaves = new Leaf[k];
		for (int i = 0; i < arrayLeaves.length; i++) {
			arrayLeaves[i] = Leaf.createMinLeaf();
		}
		return arrayLeaves;
	}

	private static int[] createLoserTree(Leaf[] arrayLeaves,
			int[] currentKElements) {
		int[] loserTree = new int[arrayLeaves.length];
		return loserTree;
	}

	private static void adjust(int[] loserTree, Leaf[] arrayLeaves,
			int leafIndex) {
		
	}

}
