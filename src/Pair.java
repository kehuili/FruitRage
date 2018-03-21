public class Pair {
	private int row;
	private int col;

	public Pair(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Boolean compare(Pair p) {
		if (this.row == p.row && this.col == p.col) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if(compare((Pair)o)){
			return true;
		}
		return false;
	}
}
