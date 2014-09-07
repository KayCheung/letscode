package com.maze;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	public final MazeMap maze;
	public final int x;
	public final int y;
	/**
	 * (never forever change) initial status of this Vertex
	 */
	public final boolean blocked;

	private Set<Direction> setTried = new HashSet<Direction>(4);

	private boolean nonBlocked_but_has_been_discard = false;

	public Direction lastDirec = null;

	public Vertex(final MazeMap maze, final int x, final int y,
			final boolean blocked) {
		this.maze = maze;
		this.x = x;
		this.y = y;
		this.blocked = blocked;
	}

	private int[] shouldGoTo(int curX, int curY, Direction curDirec,
			int X_MAX_INDEX, int Y_MAX_INDEX) {
		int x = -1;
		int y = -1;
		switch (curDirec) {
		// current EAST, want to SOUTH
		case EAST:
			x = curX;
			y = (curY >= Y_MAX_INDEX) ? -1 : curY + 1;
			break;
		// current SOUTH, want to WEST
		case SOUTH:
			y = curY;
			x = (curX == 0) ? -1 : curX - 1;
			break;
		// current WEST, want to NORTH
		case WEST:
			x = curX;
			y = (curY == 0) ? -1 : curY - 1;
			break;
		// current NORTH, want to EAST
		case NORTH:
			y = curY;
			x = (curX == X_MAX_INDEX) ? -1 : curX + 1;
			break;
		case NONE:
			break;
		}
		return new int[] { x, y };
	}

	public Vertex nextVertex() {
		// already tried all the directions.
		// But, you go into nextVertex again
		if (setTried.size() == 4) {
			nonBlocked_but_has_been_discard = true;
			return null;
		}
		int[] nextXY = shouldGoTo(x, y, lastDirec, maze.X_MAX_INDEX,
				maze.Y_MAX_INDEX);
		lastDirec = lastDirec.next();
		setTried.add(lastDirec);
		return maze.getV(nextXY[0], nextXY[1]);
	}

	public boolean nonBlocked_but_has_been_discard() {
		return nonBlocked_but_has_been_discard;
	}

	@Override
	public String toString() {
		return "Vertex [x=" + x + ", y=" + y + ", blocked=" + blocked + "]";
	}
}
