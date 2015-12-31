package m111;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by marvin on 15/12/27.
 */
public class MinimumDepthofBinaryTree {

    public static void main(String[] args) {
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);

        tn1.left = tn2;
        tn1.right = tn3;
        MinimumDepthofBinaryTree mt = new MinimumDepthofBinaryTree();
        System.out.println(mt.lowestCommonAncestor(tn1, tn2, tn3));

    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> curLevelNodes = new ArrayList<>(1);
        curLevelNodes.add(root);
        int depth = 0;

        while (!curLevelNodes.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            depth++;
            for (TreeNode tn : curLevelNodes) {
                if (tn.left == null && tn.right == null) {
                    return depth;
                } else {
                    if (tn.left != null) {
                        nextLevel.add(tn.left);
                    }
                    if (tn.right != null) {
                        nextLevel.add(tn.right);
                    }
                }
            }
            curLevelNodes = nextLevel;
        }

        return depth;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return leftRightDiff(root) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int leftRightDiff(TreeNode tn) {
        if (tn == null) {
            return 0;
        }
        return Math.abs(maxDepth(tn.left) - maxDepth(tn.right));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    private List<TreeNode> root2NodePath(TreeNode root, TreeNode target) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return stack;
        }

        TreeNode cur = root;
        stack.push(cur);
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listP = new ArrayList<>();
        root2NodePath(listP, root, p);

        List<TreeNode> listQ = new ArrayList<>();
        root2NodePath(listQ, root, q);

        return firstDifferent(listP, listQ);
    }

    private boolean root2NodePath(List<TreeNode> rst, TreeNode cur, TreeNode target) {
        if (cur == null) {
            return false;
        }
        rst.add(cur);
        if (cur == target) {
            return true;
        } else {
            boolean found = root2NodePath(rst, cur.left, target) || root2NodePath(rst, cur.right, target);
            if (!found) {
                rst.remove(cur);
            }
            return found;
        }
    }

    private TreeNode firstDifferent(List<TreeNode> listP, List<TreeNode> listQ) {
        int length = Math.min(listP.size(), listQ.size());
        TreeNode lastEqual = null;
        for (int i = 0; i < length; i++) {
            if (listP.get(i) == listQ.get(i)) {
                lastEqual = listP.get(i);
            } else {
                break;
            }
        }
        return lastEqual;
    }

    static class TreeNode {
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
                    '}';
        }
    }
}
