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

	public static void addC(TrieBranch toNode, int curCIndex, String word) {
		if (curCIndex > word.length() - 1) {
			return;
		}
		int nodeSlotIndex = indexForC(word.charAt(curCIndex));
		// good, slot is waiting to be filled. Create leaf
		if (toNode.children[nodeSlotIndex] == null) {
			TrieLeaf tl = TrieLeaf.createBranchNode(toNode.level + 1);
			tl.sb.append(word.substring(curCIndex));
			toNode.children[nodeSlotIndex] = tl;
		}
		// nodeSlotIndex has been occupied
		else {
			TrieNode tn = toNode.children[nodeSlotIndex];
			// used by a leaf
			if (tn.bLeaf == true) {
				int leafLevel = tn.level;
				char cBelong2LeafLevel = ((TrieLeaf) tn).sb
						.charAt(leafLevel - 1);
				//same
				if(word.charAt(curCIndex)==cBelong2LeafLevel){
					
				}
				// different
				else{
					
				
				}
			}
			// yes, a branch node
			else {
				// char at curCIndex has been consumed
				addC((TrieBranch) tn, curCIndex + 1, word);
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
