import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class calibrate {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
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
//			System.out.println(n + " " + p + " " + time);
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
			String move = Minimax.minimax(board,5, time);
			long end = System.currentTimeMillis();
			System.out.print(end-start);
			writeTxt(Long.toString(end-start));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeTxt(String time) {
		File writename = new File("calibrate.txt");
		try {
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write(time);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
