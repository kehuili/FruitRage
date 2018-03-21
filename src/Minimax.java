public class Minimax {
	public static String minimax(Board board, int depth, float time) {
		
//		Timer timer = new Timer();
		int score = -1;
		int value = -1;
		int col = 0;
		int row = 0;
		for (int i = 0; i < board.getN(); i++) {
			for (int j = 0; j < board.getN(); j++) {
				if(board.getBoard(i, j)==-1) continue;
				Pair p = new Pair(i, j);
				if(board.getFlag().contains(p)){
					continue;
				}
				Board b = board.copy();
				score = b.pickCell(j, i);
				board.setFlag(b.getFlag());
				b.clearFlag();
				int tempValue = minimaxValue(b, score, false, depth-1, -10000, 10000);
				if(value < tempValue){
					col = j;
					row = i;
					value = tempValue;
				}
				//*?
			}
		}
		board.pickCell(col, row);
		board.clearFlag();
		System.out.println();
		board.print();
		char c = (char) ('A'+col);
		String move = c+Integer.toString(row+1);
//		System.out.println(value);
		return move;
	}

	private static int minimaxValue(Board board, int score, Boolean max, int depth, int alpha, int beta) {
//		System.out.println(board.getEmpty()+max.toString()+depth);
		if(depth<=0)
			return score;
		if(board.getEmpty()==board.getN()*board.getN()){
			return score;
		}else if(max){
			int value = alpha;
			int sc = score;
			for (int i = 0; i < board.getN(); i++) {
				for (int j = 0; j < board.getN(); j++) {
					sc = score;
					if(board.getBoard(i, j)==-1) continue;
					Pair p = new Pair(i, j);
					if(board.getFlag().contains(p)){
						continue;
					}
					Board b = board.copy();
//					System.out.println("max");
					sc += b.pickCell(j, i);
					if(board.getEmpty()==board.getN()*board.getN()){
						return sc;
					}
					board.setFlag(b.getFlag());
					b.clearFlag();
//					System.out.println(value);
					value = Math.max(value, minimaxValue(b, sc, false,depth-1, alpha, beta));
					if(value>=beta){
						return value;
					}
					alpha = Math.max(alpha, value);
					//*?
				}
			}
			return value;
		}else if(!max) {
			int value = beta;
			int sc = score;
			for (int i = 0; i < board.getN(); i++) {
				for (int j = 0; j < board.getN(); j++) {
					sc = score;
					if(board.getBoard(i, j)==-1) continue;
					Pair p = new Pair(i, j);
					if(board.getFlag().contains(p)){
						continue;
					}
					Board b = board.copy();
//					System.out.println("min");
					sc -= b.pickCell(j, i);
					if(board.getEmpty()==board.getN()*board.getN()){
						return sc;
					}
					board.setFlag(b.getFlag());
					b.clearFlag();
//					System.out.println(value);
					value = Math.min(value,minimaxValue(b, sc, true,depth-1,alpha,beta));
					if(value<= alpha){
						return value;
					}
					beta = Math.min(beta, value);
					//*?
				}
			}
			return value;
		}
		
		return 0;
	}
}
