import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by nuc on 2015/12/30.
 */
public class RecoverBST {
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
                    '}';
        }
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Boolean> stackAtLeft = new ArrayDeque<>();
        TreeNode lastNode = root;
        boolean selfAtLeft = false;
        TreeNode cur = root;
        while (!(cur == null && stack.isEmpty())) {
            if (cur != null) {
                stack.push(cur);
                stackAtLeft.push(selfAtLeft ? Boolean.TRUE : Boolean.FALSE);

                cur = cur.left;
                selfAtLeft = true;
            } else {
                cur = stack.pop();

                rst.add(cur.val);


                cur = cur.right;
                selfAtLeft = false;
            }
        }
        return rst;
    }

    public static class RecoverNode {
        TreeNode parent;
        boolean selfAtLeft;
        TreeNode self;

        public RecoverNode(TreeNode parent, TreeNode self, boolean selfAtLeft) {
            this.parent = parent;
            this.self = self;
            this.selfAtLeft = selfAtLeft;
        }
    }

    private void doExchange(RecoverNode[] rns) {
        if (rns.length != 2) {
            return;
        }
        RecoverNode rn0 = rns[0];
        RecoverNode rn1 = rns[1];

        if (rn0.parent == rn1.self || rn1.parent == rn0.self) {
            exchangeAdjacent(rn0, rn1);
        } else {
            exchangeFarAway(rn0, rn1);
        }
    }

    private void exchangeFarAway(RecoverNode rn0, RecoverNode rn1) {
        TreeNode tn0 = rn0.self;
        TreeNode tn1 = rn1.self;
        TreeNode tmp = null;

        tmp = tn0.left;
        tn0.left = tn1.left;
        tn1.left = tmp;

        tmp = tn0.right;
        tn0.right = tn1.right;
        tn1.right = tmp;


        if (rn0.selfAtLeft) {
            rn0.parent.left = rn1.self;
        } else {
            rn0.parent.right = rn1.self;
        }

        if (rn1.selfAtLeft) {
            rn1.parent.left = rn0.self;
        } else {
            rn1.parent.right = rn0.self;
        }
    }


    private void exchangeAdjacent(RecoverNode rn0, RecoverNode rn1) {

        TreeNode rn0_R = rn0.self.right;
        TreeNode rn0_L = rn0.self.left;

        TreeNode rn1_R = rn1.self.right;
        TreeNode rn1_L = rn1.self.left;

        if (rn0.parent == rn1.self) {
            RecoverNode rn_high = rn1;
            RecoverNode rn_low = rn0;
            TreeNode rn_low_R = rn0_R;
            TreeNode rn_low_L = rn0_L;
            doExchangeAdjacent(rn_high, rn_low, rn_low_R, rn_low_L);
        } else if (rn1.parent == rn0.self) {
            RecoverNode rn_high = rn0;
            RecoverNode rn_low = rn1;
            TreeNode rn_low_R = rn1_R;
            TreeNode rn_low_L = rn1_L;
            doExchangeAdjacent(rn_high, rn_low, rn_low_R, rn_low_L);
        }
    }

    private void doExchangeAdjacent(RecoverNode rn_high, RecoverNode rn_low,
                                    TreeNode rn_low_R, TreeNode rn_low_L) {
        //change rn0.self parent
        if (rn_high.selfAtLeft) {
            rn_high.parent.left = rn_low.self;
        } else {
            rn_high.parent.right = rn_low.self;
        }
        // change rn0.self children
        if (rn_low.selfAtLeft) {
            rn_low.self.left = rn_high.self;
            rn_low.self.right = rn_high.self.right;
        } else {
            rn_low.self.right = rn_high.self;
            rn_low.self.left = rn_high.self.left;
        }


        // change rn1.self parent
        if (rn_low.selfAtLeft) {
            rn_low.self.left = rn_high.self;
        } else {
            rn_low.self.right = rn_high.self;
        }
        // change rn1.self children
        rn_high.self.right = rn_low_R;
        rn_high.self.left = rn_low_L;
    }

    public static void main(String[] args) {
        RecoverBST rbst = new RecoverBST();
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);

        tn1.left = tn2;
        tn2.right = tn3;

        RecoverNode rn0 = new RecoverNode(tn1, tn2, true);
        RecoverNode rn1 = new RecoverNode(tn1, tn3, false);

        rbst.doExchange(new RecoverNode[]{rn0, rn1});
        System.out.println(tn1);

    }
}
