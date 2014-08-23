package com.losertree;

import com.common.TreeUtil;
import com.showtree.ShowStaticTreeFrame;

public class Test_LoserTree {
	public static void main(String[] args) {
		int ls[] = LoserTreeUtil.createLoserTree(TreeUtil.genereateRandomArray(
				25, 101));
		LSNode root = LoserTreeUtil.createVisibleLoserTree(ls);

		ShowStaticTreeFrame.showTreeFrm(root, "Loser Tree");
	}
}
