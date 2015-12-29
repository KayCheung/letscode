import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by libing2 on 2015/12/29.
 */
public class PostTraveral {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Boolean> accessed = new ArrayDeque<>();

        TreeNode cur = root;
        while (!(cur == null && stack.isEmpty())) {
            if (cur == null) {
                cur = stack.peek();
                Boolean already = accessed.peek();

                if (Boolean.TRUE.equals(already)) {
                    cur = stack.pop();
                    accessed.pop();

                    rst.add(cur.val);
                    cur = null;
                } else {
                    accessed.pop();
                    accessed.push(Boolean.TRUE);
                    cur = cur.right;
                }
            } else {
                if (cur.left != null) {
                    stack.push(cur);
                    accessed.push(Boolean.FALSE);
                    cur = cur.left;
                } else if (cur.right != null) {
                    stack.push(cur);
                    accessed.push(Boolean.TRUE);
                    cur = cur.right;
                }
                // both left&&right are null
                else {
                    rst.add(cur.val);
                    cur = null;
                }
            }
        }
        return rst;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root;
        while (!(cur == null && stack.isEmpty())) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                rst.add(cur.val);
                cur = cur.right;
            }
        }
        return rst;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root;
        while (!(cur == null && stack.isEmpty())) {
            if (cur != null) {
                rst.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return rst;
    }

    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        recursivePreOrderTraversal(rst, root);
        return rst;
    }

    public void recursivePreOrderTraversal(List<Integer> rst, TreeNode root) {
        if (root == null) {
            return;
        }
        rst.add(root.val);
        recursivePreOrderTraversal(rst, root.left);
        recursivePreOrderTraversal(rst, root.right);
    }


    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        recursiveInOrderTraversal(rst, root);
        return rst;
    }

    public void recursiveInOrderTraversal(List<Integer> rst, TreeNode root) {
        if (root == null) {
            return;
        }
        recursiveInOrderTraversal(rst, root.left);
        rst.add(root.val);
        recursiveInOrderTraversal(rst, root.right);
    }

    public List<Integer> postorderTraversal_2(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        recursivePostOrderTraversal(rst, root);
        return rst;
    }

    public void recursivePostOrderTraversal(List<Integer> rst, TreeNode root) {
        if (root == null) {
            return;
        }
        recursivePostOrderTraversal(rst, root.left);
        recursivePostOrderTraversal(rst, root.right);
        rst.add(root.val);
    }
}
