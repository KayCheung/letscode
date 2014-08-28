package com.trietree;

public class TrieLeaf extends TrieNode {
	public String word;
	public int occurrence;
	public int consumedIndex;

	private TrieLeaf() {
		this.bLeaf = true;
		this.occurrence = 0;
		this.consumedIndex = 0;
	}

	public static TrieLeaf createLeafNode() {
		return new TrieLeaf();
	}

	@Override
	public String toString() {
		return "TrieLeaf [word=" + word + ", occurrence=" + occurrence
				+ ", consumedIndex=" + consumedIndex + "]";
	}

}
