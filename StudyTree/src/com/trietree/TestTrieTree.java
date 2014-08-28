package com.trietree;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.IOUtil;

public class TestTrieTree {
	static Pattern ptn = Pattern.compile("\\w*");

	public void fdfd() {
	}

	public static void main(String[] args) throws Exception {
		TrieBranch root = TrieBranch.createBranchNode();
		BufferedReader br = IOUtil.createBufferedReader(
				"E:/Eden/gitworkspace/letscode/StudyTree/testfile.txt", null);
		ArrayList<TrieLeaf> resultList = new ArrayList<TrieLeaf>();
		String line = null;
		while ((line = br.readLine()) != null) {
			line = line.toLowerCase();
			Matcher m = ptn.matcher(line);
			while (m.find()) {
				String word = m.group();
				TrieTreeUtil.addEachWord(root, word, resultList);
			}
		}
		Collections.sort(resultList);
		for (Iterator<TrieLeaf> it = resultList.iterator(); it.hasNext();) {
			TrieLeaf tl = (TrieLeaf) it.next();
			System.out.println(tl);
		}
	}
}
