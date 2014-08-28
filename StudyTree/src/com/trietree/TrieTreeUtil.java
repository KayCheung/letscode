package com.trietree;

import java.util.ArrayList;
import java.util.Iterator;

public class TrieTreeUtil {
	public static final void main(String[] args) {
		String[] arrayWords = new String[] {"ab","adc","abc"};
		ArrayList<TrieLeaf> resultList = new ArrayList<TrieLeaf>();
		addWords(arrayWords, resultList);

		verifyResult(resultList, arrayWords);
	}

	public static void addWords(String[] arrayWords,
			ArrayList<TrieLeaf> resultList) {

		TrieBranch root = TrieBranch.createBranchNode();

		for (int i = 0; i < arrayWords.length; i++) {
			addEachWord(root, arrayWords[i], resultList);
		}

	}

	public static void verifyResult(ArrayList<TrieLeaf> resultList,
			String[] arrayWords) {
		System.out.println("Total words count: " + arrayWords.length);
		int countInResult = 0;
		for (Iterator<TrieLeaf> it = resultList.iterator(); it.hasNext();) {
			TrieLeaf tl = (TrieLeaf) it.next();
			countInResult += tl.occurrence;
			System.out.println(tl);
		}
		System.out.println("Calculated words count: " + countInResult);
	}

	public static void addEachWord(TrieBranch root, String word,
			ArrayList<TrieLeaf> resultList) {
		if (word == null || word.equals("")) {
			return;
		}
		addChar(root, 0, word, resultList);
	}

	private static void addChar(TrieBranch toWho, int curCIndexOfWord,
			String word, ArrayList<TrieLeaf> resultList) {
		System.out.println(word);
		// hang to parent.$position
		if (curCIndexOfWord == word.length()) {
			TrieLeaf leaf = (TrieLeaf) toWho.children[TrieBranch.DOLLAR_END_FLAG_INDEX];
			// already exist
			if (leaf != null) {
				leaf.occurrence++;
			} else {
				leaf = TrieLeaf.createLeafNode();
				toWho.children[TrieBranch.DOLLAR_END_FLAG_INDEX] = leaf;
				leaf.word = word;
				leaf.occurrence++;
				leaf.consumedIndex = curCIndexOfWord;
				resultList.add(leaf);
			}

			return;
		}

		int slot4CurC = indexForC(word.charAt(curCIndexOfWord));
		/*
		 * Please note: since we've found slot4CurC
		 * 
		 * currentChar will never be hanged on position
		 * TrieBranch.DOLLAR_END_FLAG_INDEX
		 */
		/**
		 * good, slot is waiting to be filled
		 * 
		 * Create leaf
		 */
		if (toWho.children[slot4CurC] == null) {
			TrieLeaf leaf = TrieLeaf.createLeafNode();
			toWho.children[slot4CurC] = leaf;
			leaf.word = word;
			leaf.occurrence++;
			leaf.consumedIndex = curCIndexOfWord + 1;
			resultList.add(leaf);
			return;
		}
		/**
		 * 
		 * slot4CurC is not TrieBranch.DOLLAR_END_FLAG_INDEX
		 * 
		 * means: LEAF ALWAYS CONSUME AT LEAST A CHAR
		 */
		else {
			TrieNode occupiedNode = toWho.children[slot4CurC];
			/** 1. nodeSlotIndex stands a leaf */
			if (occupiedNode.bLeaf == true) {
				TrieLeaf leaf = (TrieLeaf) occupiedNode;
				if (word.equals(leaf.word)) {
					leaf.occurrence++;
					return;
				}

				// The leaf is NOT equals leaf.word

				// leaf does not provide char at all
				// Move it to index DOLLAR_END_FLAG_INDEX
				if (leaf.consumedIndex == leaf.word.length()) {
					TrieBranch commonBranch = TrieBranch.createBranchNode();
					toWho.children[slot4CurC] = commonBranch;
					// hitch leaf to newBranch
					commonBranch.children[TrieBranch.DOLLAR_END_FLAG_INDEX] = leaf;

					// hitch word
					addChar(commonBranch, curCIndexOfWord + 1, word, resultList);
				}
				// leaf itself DOES provided char
				else {
					char cBelong2Leaf = leaf.word.charAt(leaf.consumedIndex);

					TrieBranch commonBranch = TrieBranch.createBranchNode();
					toWho.children[slot4CurC] = commonBranch;

					// hitch leaf to newBranch4Leaf
					commonBranch.children[indexForC(cBelong2Leaf)] = leaf;
					leaf.consumedIndex++;
					// hitch word
					addChar(commonBranch, curCIndexOfWord + 1, word, resultList);
				}

			}
			/** 2. nodeSlotIndex stands a branch */
			else {
				TrieBranch branch = (TrieBranch) occupiedNode;
				// char at curCIndex has been consumed
				addChar(branch, curCIndexOfWord + 1, word, resultList);
			}

		}

	}

	private static int indexForC(char c) {
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
