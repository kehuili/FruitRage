import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class homework {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// timer
		// new Timer().schedule(new TimerTask() {
		//
		// @Override
		// public void run() {
		// System.out.println("bombing!");
		//
		// }
		// }, 1000);

		// TODO Auto-generated method stub
		try {
			// read from file
			String path = "input.txt";
			File file = new File(path);
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			// first line, board size n
			int n = Integer.parseInt(br.readLine());
			// second line, # of fruit type
			int p = Integer.parseInt(br.readLine());
			// third line, remaining time
			float time = Float.parseFloat(br.readLine());
			// System.out.println(n + " " + p + " " + time);
			int[][] arr = new int[n][n];
			int i = 0;
			line = br.readLine();
			int empty = 0;
			while (line != null) {
				for (int j = 0; j < n; j++) {
					if (line.charAt(j) == '*') {
						arr[i][j] = -1;
						empty++;
					} else {
						int t = Character.getNumericValue(line.charAt(j));
						arr[i][j] = t;
					}
				}
				i++;
				line = br.readLine();
			}
			Board board = new Board(n, p, time, arr, empty);
			board.print();
			// board.pickCell(2,1);
			int depth = 3;
			int left = n * n - empty;
			if (left >= 80 && left < 100) {
				depth = 4;
			}else if (left < 80) {
				depth = 5;
			}
			if (time >= 10 && time < 20) {
				if (left < 50) {
					depth = 5;
				} else {
					depth = 3;
				}
			} else if (time >= 5 && time < 10) {
				if (left < 50) {
					depth = 3;
				} else {
					depth = 2;
				}
			} else if (time >= 1 && time < 5) {
				depth = 1;
			} else if (time < 1) {
				depth = 0;
			}
			String move = Minimax.minimax(board, 0, time);
			// while(board.getEmpty()<board.getN()*board.getN()){
			// String move = Minimax.minimax(board, 0, time);
			// System.out.println("max "+ move);
			// board.print();
			// System.out.println(board.getEmpty());
			// System.out.println();
			// if(board.getEmpty()==board.getN()*board.getN()){
			// System.out.print("i lose");
			// break;
			// }
			//
			// move = Minimax.minimax(board, 2, time);
			// System.out.println("min "+ move);
			// board.print();
			// System.out.println(board.getEmpty());
			// System.out.println();
			// if(board.getEmpty()==board.getN()*board.getN()){
			// System.out.print("i win");
			// }
			// }
			writeTxt(move, board);
			// System.out.println(board.getEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeTxt(String move, Board board) {
		File writename = new File("output.txt");
		try {
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write(move + "\r\n" + board.print());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
