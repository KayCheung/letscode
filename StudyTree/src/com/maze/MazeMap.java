package com.maze;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MazeMap {
	public final int X_MAX_INDEX;
	public final int Y_MAX_INDEX;

	private Vertex[][] arrayV;

	public MazeMap() {
		this.X_MAX_INDEX = 12;
		this.Y_MAX_INDEX = 9;
		arrayV = new Vertex[X_MAX_INDEX + 1][Y_MAX_INDEX + 1];
		initializeMap(arrayV);
	}

	public void printMap() {
		for (int row = 0; row < arrayV.length; row++) {
			Vertex[] columns4OneRow = arrayV[row];
			System.out.println(Arrays.toString(columns4OneRow));
		}
	}

	private void initializeMap(Vertex[][] arrayV) {
		Random rdm = new Random();

		int rowCount = arrayV.length;
		for (int row = 0; row < rowCount; row++) {
			Vertex[] columns4OneRow = arrayV[row];
			int colCount = columns4OneRow.length;
			for (int col = 0; col < colCount; col++) {
				// entry
				if (row == 0 && col == 0) {
					Vertex v = new Vertex(this, row, col, false);
					v.lastDirec = Direction.NORTH;
					arrayV[row][col] = v;
				}
				// outlet
				else if (row == rowCount - 1 && col == colCount - 1) {
					Vertex v = new Vertex(this, row, col, false);
					v.lastDirec = null;
					arrayV[row][col] = v;
				} else {
					Vertex v = new Vertex(this, row, col, rdm.nextBoolean());
					arrayV[row][col] = v;
				}
			}
		}
	}

	private static boolean vertexExist(int x, int y) {
		if (x == -1 || y == -1) {
			return false;
		}
		return true;
	}

	public Vertex getV(int x, int y) {
		if (vertexExist(x, y)) {
			return null;
		}
		return arrayV[x][y];
	}

	boolean bOutVertex(Vertex v, int outX, int outY) {
		return v.x == outX && v.y == outY;
	}

	public Stack<Vertex> findPath(int entryX, int entryY, int outX, int outY) {
		Stack<Vertex> stack = new Stack<Vertex>();
		Vertex entry = getV(entryX, entryY);
		Vertex out = getV(outX, outY);
		// unavailable entry&&outlet
		if (entry.blocked || out.blocked) {
			return stack;
		}

		stack.push(entry);
		if (entryX == outX && entryY == outY) {
			// only one Vertex, already found the path
			return stack;
		}

		boolean found = false;
		while (stack.isEmpty()) {
			Vertex curV = stack.peek();
			Vertex nextV = curV.nextVertex();
			while (true) {
				// tried <b>curV's</b> all directions, finally failed
				if (nextV == null) {
					// curV not blocked, but after tried all direction, finally
					// failed on it
					// so curV is now been discard
					stack.pop();//
					break;
				}
				// nextV NOT null
				else {
					// 1. good, find outlet
					if (bOutVertex(nextV, outX, outY)) {
						stack.push(nextV);
						found = true;
						break;
					}
					// NOT outlet, come on, let's do hard work, marvin

					// 1. nextV has already been discarded before
					if (nextV.nonBlocked_but_has_been_discard()) {
						nextV = curV.nextVertex();
						break;
					}
					// 2. blocked
					if (nextV.blocked) {
						nextV = curV.nextVertex();
						break;
					}
					// 3. nextV is good
					if (nextV.blocked == true) {
						stack.push(nextV);
					}
				}
			}
			if (found) {
				break;
			}
		}
		return stack;
	}

	public void testMaze() {

	}

	public static void main(String[] args) {
		MazeMap maze = new MazeMap();
		int entryX = 0, entryY = 0, outX = 9, outY = 9;
		maze.printMap();

		int[][] x = new int[2][];
		x[0] = new int[] { 1 };
		x[1] = new int[] { 100, 200, 300 };

		System.out.println(Arrays.toString(x[0]));
	}
}
