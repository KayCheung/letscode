package com.trietree;

import java.util.ArrayList;
import java.util.Iterator;

public class TrieTreeUtil {
	private static final ArrayList<TrieLeaf> list = new ArrayList<TrieLeaf>();

	public static void addEachWord(TrieBranch root, String word) {

		if (word == null || word.equals("")) {
			return;
		}
		addChar(root, 0, word);
	}

	public static void addWords(String[] arrayWords) {
		TrieBranch tb = TrieBranch.createBranchNode();

		for (int i = 0; i < arrayWords.length; i++) {
			addEachWord(tb, arrayWords[i]);
		}

		for (Iterator<TrieLeaf> it = list.iterator(); it.hasNext();) {
			TrieLeaf tl = (TrieLeaf) it.next();
			System.out.println(tl);
		}
	}

	public static void main(String[] args) {
		String[] arrayWords = new String[] { "abc", "ba", "bc", "abc", "abcd" };
		addWords(arrayWords);
	}

	public static void addChar(TrieBranch toWho, int curCIndexOfWord,
			String word) {
		// hitch word to parent.$position
		if (curCIndexOfWord == word.length()) {
			TrieLeaf leaf = TrieLeaf.createLeafNode();
			toWho.children[TrieBranch.DOLLAR_END_FLAG_INDEX] = leaf;
			leaf.word = word;
			leaf.occurrence++;
			leaf.consumedIndex = curCIndexOfWord;
			list.add(leaf);
			return;
		}

		int nodeSlotIndex = indexForC(word.charAt(curCIndexOfWord));

		/**
		 * good, slot is waiting to be filled. Create leaf
		 */
		if (toWho.children[nodeSlotIndex] == null) {
			TrieLeaf leaf = TrieLeaf.createLeafNode();
			toWho.children[nodeSlotIndex] = leaf;
			leaf.word = word;
			leaf.occurrence++;
			leaf.consumedIndex = curCIndexOfWord + 1;
			list.add(leaf);
		}
		/**
		 * nodeSlotIndex has been occupied
		 */
		else {
			TrieNode tn = toWho.children[nodeSlotIndex];
			// nodeSlotIndex stands a leaf
			if (tn.bLeaf == true) {
				TrieLeaf leaf = (TrieLeaf) tn;
				if (word.equals(leaf.word)) {
					leaf.occurrence++;
				}
				// dispose leaf
				else {
					// never happen
					// leaf do not provide any char.
					// leaf's position is: nodeSlotIndex
					// if the following is correct, it should be
					// DOLLAR_END_FLAG_INDEX
					if (leaf.consumedIndex == leaf.word.length()) {
						// never happen
					}
					// leaf itself does provided char
					char cBelong2Leaf = leaf.word.charAt(leaf.consumedIndex);

					// last char of word
					if (curCIndexOfWord == word.length() - 1) {
						TrieBranch newBranch = TrieBranch.createBranchNode();
						// pad a new branch between original leaf and toWho
						toWho.children[nodeSlotIndex] = newBranch;

						// hitch leaf to newBranch
						leaf.consumedIndex++;
						if (leaf.consumedIndex == leaf.word.length()) {
							newBranch.children[TrieBranch.DOLLAR_END_FLAG_INDEX] = leaf;
						} else {
							newBranch.children[indexForC(cBelong2Leaf)] = leaf;
						}
						// hitch word
						addChar(newBranch, curCIndexOfWord + 1, word);
						// hitch word
						// TrieLeaf leaf4Word = TrieLeaf.createLeafNode();
						// newBranch.children[TrieBranch.DOLLAR_END_FLAG_INDEX]
						// = leaf4Word;
						// leaf4Word.word = word;
						// leaf4Word.occurrence++;
						// leaf4Word.consumedIndex = word.length();
						// list.add(leaf4Word);

					}
					// curCIndexOfWord NOT last char
					else {
						// the char to be added is the same with the
						// cBelong2LeafLevel
						if (word.charAt(curCIndexOfWord + 1) == cBelong2Leaf) {
							TrieBranch newBranch = TrieBranch
									.createBranchNode();
							// pad a new branch between original leaf and toWho
							toWho.children[nodeSlotIndex] = newBranch;

							// hitch leaf to newBranch
							leaf.consumedIndex++;
							if (leaf.consumedIndex == leaf.word.length()) {
								newBranch.children[TrieBranch.DOLLAR_END_FLAG_INDEX] = leaf;
							} else {
								newBranch.children[indexForC(cBelong2Leaf)] = leaf;
							}

							// hitch word
							addChar(newBranch, curCIndexOfWord + 1, word);

						}
						// different
						else {
							TrieBranch newBranch4Word = TrieBranch
									.createBranchNode();
							TrieBranch newBranch4Leaf = TrieBranch
									.createBranchNode();
							// pad a new branch between original leaf and toWho
							// indexForC(cBelong2Leaf) maybe contains values already
							toWho.children[indexForC(cBelong2Leaf)] = newBranch4Leaf;

							toWho.children[indexForC(word
									.charAt(curCIndexOfWord + 1))] = newBranch4Word;

							// hitch leaf to newBranch4Leaf
							leaf.consumedIndex++;
							if (leaf.consumedIndex == leaf.word.length()) {
								newBranch4Leaf.children[TrieBranch.DOLLAR_END_FLAG_INDEX] = leaf;
							} else {
								newBranch4Leaf.children[indexForC(cBelong2Leaf)] = leaf;
							}

							// hitch word
							addChar(newBranch4Word, curCIndexOfWord + 1, word);

						}
					}
				}
			}
			// nodeSlotIndex stands a branch node
			else {
				TrieBranch branch = (TrieBranch) tn;
				// char at curCIndex has been consumed
				addChar(branch, curCIndexOfWord + 1, word);
			}

		}

	}

	public static int indexForC(char c) {
		// 0----48
		// 9----57
		// A----65
		// Z----90
		// _----95
		// a----97
		// z----122
		// 0--9_a--z, totally 37, [0]--end
		//

		int c2i = (int) c;
		// 0--9
		if (c2i >= 48 && c2i <= 57) {
			return c2i - 48 + 1;// '0'--index1, .., '9'--index10
		}
		// '_'
		if (c2i == 95) {
			return 11;// '_'--index11
		}
		// 'a'--'z'
		if (c2i >= 97 && c2i <= 122) {
			return c2i - 97 + 12;
		}
		return -1;
	}
}
