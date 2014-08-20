public interface VisibleNode {
	public VisibleNode left();

	public VisibleNode right();

	public String presentation();

	/**
	 * Only copy the node itself, not including leftChild and rightChild
	 * 
	 * @return
	 */
	public VisibleNode copyNode();

	public void setLeft(VisibleNode left);

	public void setRight(VisibleNode right);
}
