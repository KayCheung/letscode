package com.trietree;

public class TrieBranch extends TrieNode {
	public static final int DOLLAR_END_FLAG_INDEX = 0;
	public TrieNode[] children = new TrieNode[1 + 10 + 1 + 26];

	private TrieBranch() {
		this.bLeaf = false;
	}

	public static TrieBranch createBranchNode() {
		return new TrieBranch();
	}

}
