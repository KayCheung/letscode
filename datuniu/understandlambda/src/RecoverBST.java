import apple.laf.JRSUIUtils;

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
                stackAtLeft.push(selfAtLeft?Boolean.TRUE:Boolean.FALSE);

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

    public static class MistakePair {
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

        RecoverNode left;
        RecoverNode right;

        public MistakePair(RecoverNode left, RecoverNode right) {
            this.left = left;
            this.right = right;
        }
    }


    private MistakePair.RecoverNode[] calcExchangeRecoverNode(MistakePair[] mistakePairs) {
        if (mistakePairs.length == 0) {
            return new MistakePair.RecoverNode[0];
        }
        if (mistakePairs.length == 1) {
            return new MistakePair.RecoverNode[]{mistakePairs[0].left, mistakePairs[0].right};
        }
        // being here mistakePairs.length==2
        MistakePair f = mistakePairs[0];
        MistakePair s = mistakePairs[1];
        //f0,f1,s0,s1
        //s0,f1,f0,s1
        if (s.left.self.val >= f.right.self.val &&
                f.right.self.val >= f.left.self.val &&
                f.left.self.val >= s.right.self.val) {
            return new MistakePair.RecoverNode[]{f.left, s.left};
        }
        //s1,f1,s0,f0
        if (s.right.self.val >= f.right.self.val &&
                f.right.self.val >= s.left.self.val &&
                s.left.self.val >= f.left.self.val) {
            return new MistakePair.RecoverNode[]{f.left, s.right};
        }
        //f0,s0,f1,s1
        if (f.left.self.val >= s.left.self.val &&
                s.left.self.val >= f.right.self.val &&
                f.right.self.val >= s.right.self.val) {
            return new MistakePair.RecoverNode[]{f.right, s.left};
        }
        //f0,s1,s0,f1
        if (f.left.self.val >= s.right.self.val &&
                s.right.self.val >= s.left.self.val &&
                s.left.self.val >= f.right.self.val) {
            return new MistakePair.RecoverNode[]{f.right, s.right};
        }
        return new MistakePair.RecoverNode[0];
    }

    private void doExchange(MistakePair.RecoverNode[] recoverNodes) {
        if (recoverNodes.length != 2) {
            return;
        }
        MistakePair.RecoverNode rn0 = recoverNodes[0];
        MistakePair.RecoverNode rn1 = recoverNodes[1];

        if (rn0.parent == rn1.self || rn1.parent == rn0.self) {
            exchangeAdjacent(rn0, rn1);
        } else {
            exchangeFarAway(rn0, rn1);
        }
    }

    private void exchangeFarAway(MistakePair.RecoverNode rn0, MistakePair.RecoverNode rn1) {
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


    private void exchangeAdjacent(MistakePair.RecoverNode rn0, MistakePair.RecoverNode rn1) {

        TreeNode rn0_R = rn0.self.right;
        TreeNode rn0_L = rn0.self.left;

        TreeNode rn1_R = rn1.self.right;
        TreeNode rn1_L = rn1.self.left;

        if (rn0.parent == rn1.self) {
            MistakePair.RecoverNode rn_high = rn1;
            MistakePair.RecoverNode rn_low = rn0;
            TreeNode rn_low_R = rn0_R;
            TreeNode rn_low_L = rn0_L;
            doExchangeAdjacent(rn_high, rn_low, rn_low_R, rn_low_L);
        } else if (rn1.parent == rn0.self) {
            MistakePair.RecoverNode rn_high = rn0;
            MistakePair.RecoverNode rn_low = rn1;
            TreeNode rn_low_R = rn1_R;
            TreeNode rn_low_L = rn1_L;
            doExchangeAdjacent(rn_high, rn_low, rn_low_R, rn_low_L);
        }
    }

    private void doExchangeAdjacent(MistakePair.RecoverNode rn_high, MistakePair.RecoverNode rn_low,
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

        MistakePair.RecoverNode rn0 = new MistakePair.RecoverNode(tn1, tn2, true);
        MistakePair.RecoverNode rn1 = new MistakePair.RecoverNode(tn1, tn3, false);

        rbst.doExchange(new MistakePair.RecoverNode[]{rn0, rn1});
        System.out.println(tn1);

    }
}
