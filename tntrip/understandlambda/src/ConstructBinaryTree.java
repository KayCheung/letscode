/**
 * Created by nuc on 2016/01/01452056
 */
public class ConstructBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public TreeNode buildTree_pre(int[] preorder, int[] inorder) {
        return null;
    }

    public TreeNode buildTree_post(int[] inorder, int[] postorder) {
        return null;
    }

    public static void main(String[] args) {
        ConstructBinaryTree rbst = new ConstructBinaryTree();
        TreeNode tn0 = new TreeNode(0);
        TreeNode tn1 = new TreeNode(1);

        tn0.left = tn1;

        TreeNode root = tn0;
        System.out.println(root);

        //TreeNode newRoot = rbst.recoverTree(root);

        //System.out.println(newRoot);

    }
}
