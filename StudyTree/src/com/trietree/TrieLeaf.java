package com.trietree;

public class TrieLeaf extends TrieNode {
	public final StringBuilder sb = new StringBuilder();
	public int occurrence;
	
	private TrieLeaf(int myLevel) {
		this.level = myLevel;
		this.occurrence = 0;
	}

	public static TrieLeaf createLeafNode(int myLevel) {
		return new TrieLeaf(myLevel);
	}

}
