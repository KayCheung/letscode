package com.util;

import java.util.Arrays;

public class MultiDimensionArray {

	public static void twoDim() {
		int[][] x = new int[2][];
		x[0] = new int[] { 1 };
		x[1] = new int[] { 100, 200, 300 };
		System.out.println(Arrays.deepToString(x));// [[1], [100,200,300]]
		System.out.println(x.length);// 2
		System.out.println(Arrays.toString(x[0]));// [1]
	}

	public static void main(String[] args) {
		twoDim();
	}
}
