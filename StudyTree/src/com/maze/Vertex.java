package com.maze;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	public final MazeMap maze;
	public final int col;
	public final int row;
	/**
	 * (never forever change) initial status of this Vertex
	 */
	public final boolean blocked;

	private Set<Direction> setTried = new HashSet<Direction>(4);

	private boolean nonBlocked_but_has_been_discard = false;

	/**
	 * 1. has not been tried
	 * 
	 * 2. next time, go this direction
	 */
	private Direction nextGoToDirection = null;

	private Direction justActivelyConsumed = null;

	public Vertex(final MazeMap maze, final int row, final int col,
			final boolean blocked) {
		this.maze = maze;
		this.col = col;
		this.row = row;
		this.blocked = blocked;
	}

	public void setNextGoToDirection(Direction sourceGoDirec) {
		Direction implicitConsumed = sourceGoDirec.opposite();
		setTried.add(implicitConsumed);
		// To get here, source go from source's WEST side
		// So, equivalent to destination's EAST being tried already
		if (implicitConsumed == Direction.EAST) {
			nextGoToDirection = implicitConsumed.next();
		} else {
			nextGoToDirection = Direction.EAST;
		}
		justActivelyConsumed = null;
	}

	private int[] calculateGoTo(int curRow, int curCol,
			Direction wantToGoDirec, int Row_MAX_INDEX, int Col_MAX_INDEX) {
		int row = -1;
		int col = -1;
		switch (wantToGoDirec) {
		case SOUTH:
			col = curCol;
			row = (curRow >= Row_MAX_INDEX) ? -1 : curRow + 1;
			break;
		case WEST:
			row = curRow;
			col = (curCol == 0) ? -1 : curCol - 1;
			break;
		case NORTH:
			col = curCol;
			row = (curRow == 0) ? -1 : curRow - 1;
			break;
		case EAST:
			row = curRow;
			col = (curCol == Col_MAX_INDEX) ? -1 : curCol + 1;
			break;
		}
		return new int[] { row, col };
	}

	/**
	 * null-->tried all directions, finally failed
	 * <p>
	 * NOT null-->good, find the next vertex anyway
	 * 
	 * @return
	 */
	public Vertex nextVertex() {
		System.out.println("Go into nextVertex(): " + this);
		while (setTried.size() < 4) {
			// already been tried
			if (setTried.contains(nextGoToDirection)) {
				// nextGoToDirection already been consumed
				// generate the new nextGoToDirection
				nextGoToDirection = nextGoToDirection.next();
			}
			// good, never been tried
			else {
				int[] nextRowCol = calculateGoTo(row, col, nextGoToDirection,
						maze.Row_MAX_INDEX, maze.Col_MAX_INDEX);
				justActivelyConsumed = nextGoToDirection;

				// nextGoToDirection has been consumed
				setTried.add(nextGoToDirection);
				// generate the new nextGoToDirection
				nextGoToDirection = nextGoToDirection.next();
				// anyway, direction is ok
				// but note, we may still get null due to
				// border edge
				Vertex nextVertex = maze.getV(nextRowCol[0], nextRowCol[1]);
				if (nextVertex == null) {
					// continue next direction
				} else {
					System.out.println("Leave nextVertex(), good find one: "
							+ this);
					return nextVertex;
				}
			}
		}
		// For this vertex, we've tried all directions of it.
		// 4 directions have all been tried, but finally failed
		// Even direction is NOT available
		// discard this vertex
		nonBlocked_but_has_been_discard = true;
		System.out
				.println("Leave nextVertex(), but NO next vertex. Discard it: "
						+ this);
		return null;
	}

	public boolean nonBlocked_but_has_been_discard() {
		return nonBlocked_but_has_been_discard;
	}

	public Direction getNextGoToDirection() {
		return nextGoToDirection;
	}

	public Direction getJustActivelyConsumed() {
		return justActivelyConsumed;
	}

	@Override
	public String toString() {
		return "Vertex [row=" + row + ", col=" + col + ", blocked=" + blocked
				+ ", justActivelyConsumed=" + justActivelyConsumed
				+ ", nextGoToDirection=" + nextGoToDirection + "]";
	}
}
