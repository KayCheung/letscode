package com.trietree;

public class TrieLeaf extends TrieNode {
	public final StringBuilder sb = new StringBuilder();
	
	private TrieLeaf(int myLevel) {
		this.level = myLevel;
	}

	public static TrieLeaf createBranchNode(int myLevel) {
		return new TrieLeaf(myLevel);
	}

}
