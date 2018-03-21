import java.util.ArrayList;

public class Board {
	private int n;
	private int p;
	private float time;
	private int[][] board;
	private int empty;
	private ArrayList<Pair> flag;

	public Board(int n, int p, float time, int[][] board, int empty) {
		this.empty = empty;
		this.n = n;
		this.p = p;
		this.time = time;
		this.board = board;
		flag = new ArrayList<Pair>();
	}

	public Board copy() {
		int[][] temp = new int[n][];
		for (int i = 0; i < n; i++) {
			temp[i] = board[i].clone();
		}
		Board b = new Board(n, p, time, temp, empty);
		return b;
	}

	public void clearFlag() {
		flag = new ArrayList<Pair>();
	}

	public void setFlag(ArrayList<Pair> p) {
		flag = new ArrayList<Pair>(p);
	}

	public ArrayList<Pair> getFlag() {
		return this.flag;
	}

	public void setFlag(int row, int col) {
		Pair p = new Pair(row, col);
		flag.add(p);
	}

	public int getEmpty() {
		return empty;
	}

	public int getN() {
		return this.n;
	}

	public int getP() {
		return this.p;
	}

	public float getTime() {
		return this.time;
	}

	public int[][] getBoard() {
		return board;
	}

	public int getBoard(int r, int c) {
		if (r < 0)
			return -1;
		return board[r][c];
	}

	public void setTime(float t) {
		this.time = t;
	}

	public void minusTime(float t) {
		this.time -= t;
	}

	public void setBoard(int r, int c, int type) {
		board[r][c] = type;
	}

	// pick a cell (with fruit), check horizontally and vertically
	public int pickCell(int col, int row) {
		int type = getBoard(row, col);
		if (type == -1)
			return 0;
		setBoard(row, col, -1);
		setFlag(row, col);
		int count = claimUp(col, row, type) + claimDown(col, row, type)
				+ claimLeft(col, row, type) + claimRight(col, row, type) + 1;
		// print();
		gravity();
		// print();
		empty++;
		return count * count;
	}

	// fall from above
	public void gravity() {
		// Boolean first = true;
		// ArrayList<Integer> r = new ArrayList<Integer>();
		// ArrayList<Integer> number = new ArrayList<Integer>();
		// int r = -1;
		int number = 0;
		for (int i = 0; i < this.n; i++) {
			number = 0;
			// r.clear();
			// r = -1;
			// first = true;
			for (int j = n - 1; j >= 0; j--) {
				if (getBoard(j, i) == -1) {
					number++;
					// r.add(j);
					// if (first) {
					// r = j;
					// first = false;
					// }
				} else {
					if (number > 0) {
						setBoard(j + number, i, getBoard(j, i));
						setBoard(j, i, -1);
					}
					// if (!first){
					//
					// }
					// break;
				}
			}
			// for(int k=r.get(0);k>=0;k++){
			// if(getBoard(k+1,i)!=-1){
			// setBoard(k, i, getBoard(k+1,i));
			// k++;
			// }else{
			// k++;
			// }
			// }
			// if (!first && r - number >= 0) {
			// for (int k = r; k >= 0; k--) {
			// setBoard(k, i, getBoard(k - number, i));
			// }
			// }
		}
	}

	public String print() {
		String s = "";
		int type = -1;
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				type = getBoard(i, j);
				if (type == -1) {
					s += "*";
					System.out.print("*");
				} else {
					s += type;
					System.out.print(type);
				}
			}
			s += "\r\n";
			System.out.println();
		}
		return s;
	}

	private int claimUp(int col, int row, int type) {
		int count = 0;
		// up
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col] != type)
				break;
			else {
				empty++;
				setBoard(i, col, -1);
				setFlag(i, col);
				count++;
				count += claimLeft(col, i, type);
				count += claimRight(col, i, type);
			}
		}
		return count;
	}

	private int claimDown(int col, int row, int type) {
		int count = 0;
		// down
		for (int i = row + 1; i < n; i++) {
			if (board[i][col] != type)
				break;
			else {
				empty++;
				setBoard(i, col, -1);
				setFlag(i, col);
				count++;
				count += claimLeft(col, i, type);
				count += claimRight(col, i, type);
			}
		}
		return count;
	}

	private int claimLeft(int col, int row, int type) {
		int count = 0;
		// left
		for (int i = col - 1; i >= 0; i--) {
			if (board[row][i] != type)
				break;
			else {
				empty++;
				setBoard(row, i, -1);
				setFlag(row, i);
				count++;
				count += claimUp(i, row, type);
				count += claimDown(i, row, type);
			}
		}
		return count;
	}

	private int claimRight(int col, int row, int type) {
		int count = 0;
		// right
		for (int i = col + 1; i < n; i++) {
			if (board[row][i] != type)
				break;
			else {
				empty++;
				setBoard(row, i, -1);
				setFlag(row, i);
				count++;
				count += claimUp(i, row, type);
				count += claimDown(i, row, type);
			}
		}
		return count;
	}
}
