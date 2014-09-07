package com.maze;

public enum Direction {
	NONE {
		public Direction next() {
			throw new RuntimeException(
					"Tried all the 4 directions already. No next!!!");
		}

		public Direction opposite() {
			throw new RuntimeException(
					"Tried all the 4 directions already. No opposite!!!");
		}
	},
	EAST {
		public Direction next() {
			return SOUTH;
		}

		public Direction opposite() {
			return WEST;
		}
	},
	SOUTH {
		public Direction next() {
			return WEST;
		}

		public Direction opposite() {
			return NORTH;
		}
	},
	WEST {
		public Direction next() {
			return NORTH;
		}

		public Direction opposite() {
			return EAST;
		}
	},
	NORTH {
		public Direction next() {
			return EAST;
		}

		public Direction opposite() {
			return SOUTH;
		}
	};
	public abstract Direction next();

	public abstract Direction opposite();

	public static void main(String[] args) {

	}
}
