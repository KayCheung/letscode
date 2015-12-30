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
    }

    public static class MistakePair {
        public static class RecoverNode {
            TreeNode parent;
            boolean selfAtLeft;
            TreeNode self;

            public RecoverNode(TreeNode parent, TreeNode self) {
                this.parent = parent;
                this.self = self;
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


        exchangeParent(rn0, rn1);
        exchangeChildren(rn0.self, rn1.self);

    }

    private void exchangeParent(MistakePair.RecoverNode rn0, MistakePair.RecoverNode rn1) {

        if (rn0.selfAtLeft) {
            rn0.parent.left = rn1.self;
        } else {
            rn0.parent.right = rn1.self;
            if (rn0.parent == rn1.self) {
                rn0.self.right = rn1.self;
                rn1.self.right = rn0.self.right;
            }
        }

        if (rn1.selfAtLeft) {
            rn1.parent.left = rn0.self;
        } else {
            rn1.parent.right = rn0.self;
        }
    }

    private void exchangeChildren(TreeNode tn1, TreeNode tn2) {
        TreeNode tmp = null;
        tmp = tn1.left;
        tn1.left = tn2.left;
        tn2.left = tmp;

        tmp = tn1.right;
        tn1.right = tn2.right;
        tn2.right = tmp;
    }

}
