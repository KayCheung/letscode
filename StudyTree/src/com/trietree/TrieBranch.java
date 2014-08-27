package com.trietree;

public class TrieBranch extends TrieNode {
	public TrieNode[] children = new TrieNode[1 + 10 + 1 + 26];

	private TrieBranch(int myLevel) {
		this.level = myLevel;
	}

	public static TrieBranch createBranchNode(int myLevel) {
		return new TrieBranch(myLevel);
	}

}
