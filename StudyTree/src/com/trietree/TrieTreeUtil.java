package com.trietree;

public class TrieTreeUtil {
	public static TrieBranch createBranchNode() {
		return new TrieBranch();
	}

	public static void add2Tree(TrieBranch root, String word) {
		if (word == null || word.equals("")) {
			return;
		}
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);

		}
	}

	public static void addC(TrieBranch toWho, int curCIndex, String word) {
		if (curCIndex > word.length() - 1) {
			return;
		}
		int nodeSlotIndex = indexForC(word.charAt(curCIndex));

		/**
		 * good, slot is waiting to be filled. Create leaf
		 */
		if (toWho.children[nodeSlotIndex] == null) {
			TrieLeaf leaf = TrieLeaf.createLeafNode(toWho.level + 1);
			leaf.sb.append(word);
			toWho.children[nodeSlotIndex] = leaf;
		}
		/**
		 * nodeSlotIndex has been occupied
		 */
		else {
			TrieNode tn = toWho.children[nodeSlotIndex];
			// nodeSlotIndex stands a leaf
			if (tn.bLeaf == true) {
				TrieLeaf leaf = (TrieLeaf) tn;
				if (word.equals(leaf.sb.toString())) {
					leaf.occurrence++;
				}
				// dispose leaf
				else {
					char cBelong2LeafLevel;
					if (leaf.sb.length() > leaf.level) {
						cBelong2LeafLevel = leaf.sb.charAt(leaf.level - 1);
					} else if (leaf.sb.length() == leaf.level) {

					}
					// leaf.sb.length() == leaf.level -1
					else {

					}

					// the char to be added is the same with the
					// cBelong2LeafLevel
					if (word.charAt(curCIndex) == cBelong2LeafLevel) {

					}
					// different
					else {
						TrieBranch newBranch = TrieBranch
								.createBranchNode(leaf.level);
						toWho.children[nodeSlotIndex] = newBranch;

						addC(newBranch, curCIndex + 1, word);
						newBranch.children[indexForC(cBelong2LeafLevel)] = leaf;
						leaf.level = leaf.level + 1;
					}
				}
			}
			// nodeSlotIndex stands a branch node
			else {
				TrieBranch branch = (TrieBranch) tn;
				// char at curCIndex has been consumed
				addC(branch, curCIndex + 1, word);
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
