package gui2048;


import java.util.Arrays;
import java.util.Scanner;

public class Console2048 {

	public static int[][] board;
	
	public static void initBoard() {
		board = new int[4][4];
		oldBoard = new int[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				board[r][c] = 0;
			}
		}
	
		int pos2[] = getBlankPosition();
		board[pos2[0]][pos2[1]] = 2;
		int pos4[] = getBlankPosition();
		board[pos4[0]][pos4[1]] = 4;
	}
	
	public static int[][] getBoard() {
		if (board == null) {
			initBoard();
		}
		return board;
	}
	
	public static void printBoard() {
		for (int i = 0; i < 10; i++) {System.out.println();}
		for (int r = 0; r < 4; r++) {
			System.out.println(" ~~~~~~ ~~~~~~ ~~~~~~ ~~~~~~ ");
			System.out.println("|      |      |      |      |      ");
			for (int c = 0; c < 4; c++) {
				if(board[r][c] != 0) {
					System.out.format("|%5d ", board[r][c]);
				}else {
					//print blank version
					System.out.print("|      ");
				}
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.print(" ~~~~~~ ~~~~~~ ~~~~~~ ~~~~~~");
	}
	
	public static boolean isFilled() {
		int count = 0;
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (board[r][c] !=0) {
					count++;
				}
			}
		}
		if (count == 16) {
			return true;
		}
		return false;
	}
	
	public static int[] getBlankPosition() {
		int r = 0;
		int c = 0;
		int[] blankPosition = new int[2];
		
		if (isFilled()) {
			return null;
		}
		while (true)
		{
			r = (int)(Math.random() * 4);
			c = (int)(Math.random() * 4);
			if (board[r][c] == 0) {
				blankPosition[0] = r;
				blankPosition[1] = c;
				break;
			}
		}
		return blankPosition;
	}
	
	public static char getInputDirection() {
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		char direction = (' ');
		while (direction == ' ') {
			System.out.println("Enter a direction w-a-s-d: ");
			char s = (scanner.nextLine()).charAt(0);
			direction = s;
		}
		return direction;
	}
	
	public static void moveBoard(char direction) {
		if (direction == 'w') {
			for(int i = 0; i < 4; i++) {
				int[] col = getBoardCol(i, 'u');
				int[] movedCol = moveElements(col);
				int[] combinedCol = combineElements(movedCol, 'v');
				int[] movedCol2 = moveElements(combinedCol);
				int[] movedCol3 = moveBack(movedCol2);
				setBoardCol(i, movedCol3, 'u');
			}
		}
			
		if (direction == 'a') {
			for (int i = 0; i < 4; i++) {
				int[] row = getBoardRow(i, 'l');
				int[] movedRow = moveElements(row);
				int[] combinedRow = combineElements(movedRow, 'h');
				int[] movedRow2 = moveElements(combinedRow);
				setBoardRow(i, movedRow2, 'l');
			}
		}
		if (direction == 's') {
			for(int i = 0; i < 4; i++) {
				int[] col = getBoardCol(i, 'd');
				int[] movedCol = moveElements(col);
				int[] combinedCol = combineElements(movedCol, 'v');
				int[] movedCol2 = moveElements(combinedCol);
				int[] movedCol3 = moveBack(movedCol2);
				setBoardCol(i, movedCol3, 'd');
			}
		}
		if (direction == 'd') {
			for (int i = 0; i < 4; i++) {
				int[] row = getBoardRow(i, 'r');
				int[] movedRow = moveElements(row);
				int[] combinedRow = combineElements(movedRow, 'h');
				int[] movedRow2 = moveElements(combinedRow);
				setBoardRow(i, movedRow2, 'r');
			}
		}
	}
	
	public static int[] getBoardRow(int row, char direction) {
		int[] copy = new int[4];
		if (direction == 'r') {
			for (int c = 0; c < 4; c++) {
				copy[c] = board[row][c];
			}
		}
		int i = 0;
		if (direction == 'l') {
			for (int c = 3; c >= 0; c--) {
				copy[i] = board[row][c];
				i++;
			}
		}
		return copy;
	}
	
	public static int[] getBoardCol(int col, char direction) {
		int[] copy = new int[4];
		if (direction == 'u') {
			for (int r = 0; r < 4; r++) {
				copy[r] = board[r][col];
			}
		}
		int i = 0;
		if (direction == 'd') {
			for (int r = 3; r >= 0; r--) {
				copy[i] = board[r][col];
				i++;
			}
		}
		return copy;
	}

	public static void setBoardRow(int row, int[] array, char direction) {		
		if (direction == 'r') {
			for (int index = 0; index < 4; index++) {
				board[row][index] = array[index];
			}
		}
		int i = 0;
		if (direction == 'l') {
			for (int index = 3; index >= 0; index--) {
				board[row][index] = array[i];
				i++;
			}
		}
	}
	
	public static void setBoardCol(int col, int[] array, char direction) {

		
		if (direction == 'u') {
			for (int index = 0; index < 4; index++) {
				board[index][col] = array[index];
			}
		}
		int i = 0;
		if (direction == 'd') {
			for (int index = 3; index >= 0; index--) {
				board[index][col] = array[i];
				i++;
			}
		}
	}
	
	public static int[] moveElements(int[] array) {
		int[] moved = new int[4];
		int movedPos = 3;
		for (int i = 3; i >= 0; i--) {
			if (array[i] != 0) {
				moved[movedPos] = array[i];
				movedPos--;
			}
		}
		return moved;
	}
	
	public static int[] moveBack(int[] array) {
		int[] moved = new int[4];
		int movedPos = 0;
		for (int i = 0; i < 4; i++) {
			if (array[i] != 0) {
				moved[movedPos] = array[i];
				movedPos++;
			}
		}
		return moved;
	}
	
	private static int[] combineElements(int[] array, char direction) {
		int[] combined = new int[4];
		
		if (direction == 'h') {
			for (int i = 3; i >= 0; i--) {
				if (array[i] != 0) {
					if (i == 0) {
						combined[i] = array[i];
					}else if (array[i] == array[i-1]) {
						combined[i] = (array[i]*2);
						combined[i-1] = 0;
						i--;
					}
					else 
						combined[i] = array[i];
				}
			}
		}
		
		if (direction == 'v') {
			for (int i = 0; i < 4; i++) {
				if (array[i] != 0) {
					if (i == array.length-1) {
						combined[i] = array[i];
					}else if (array[i] == array[i+1]) {
						combined[i] = (array[i]*2);
						combined[i+1] = 0;
						i++;
					}
					else 
						combined[i] = array[i];
				}
			}
		}
		return combined;
	}
	
	
	public static boolean check2048() {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (board[r][c] == 2048) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkLose() {
			boolean noMoves = false;
			int count = 10;
			if(isFilled())
			{
				count = 0;
				for(int r = 0; r < board.length; r++)
				{
					for(int c = 0; c < board.length; c++)
					{
						if(c != board.length-1)
						{
							if(board[r][c] == board[r][c+1])
							{
								count++;
							}
						}
						if(r != board.length-1)
						{
							if(board[r][c] == board[r+1][c])
							{
								count++;
							}
						}
					}
				}
			}
			//System.out.println(count);
			if(count > 0)
				noMoves = false;
			else 
				noMoves = true;
			return noMoves;
		}

	public static int[][] oldBoard;
	
	
	public static boolean boardChanged() {
		boolean changed = false;
		int count = 0;
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (board[r][c] == oldBoard[r][c]) {
					count++;
				}
			}
		}
		if (count == 16) {
			changed = false;
		}else {
			changed = true;
		}
			
		for (int R = 0; R < 4; R++) {
				for (int C = 0; C < 4; C++) {
					oldBoard[R][C] = board[R][C];
				}
			}
		return changed;
		}

	
	public static void addNewNumber() {
		while (true) {
			//randomly determine a position
			// if position is 0, fill it with a 2 75% of the time, and 4 25% of the time
			int[] pos = getBlankPosition();
			if (pos == null)
				return;
			if (board[pos[0]][pos[1]] == 0) {
				if (Math.random() >= 0.25) {
					board[pos[0]][pos[1]] = 2;
					break;
				}else {
					board[pos[0]][pos[1]] = 4;
					break;
				}
			}
		}
}
		
		public static void main(String[] args) {
			boolean flag = true; // flag to continue the game
			initBoard();
			while(flag) {
				printBoard();
				char move = getInputDirection();
				moveBoard(move);
				flag = update();
			}
			
		}

		public static boolean update() {
			if (check2048()) {
				System.out.println("Congradulations you reached 2048");
				return false;
			}else if (checkLose()) {
				System.out.println("you lose");
				return false;
			}else {
				if (boardChanged()) {
					addNewNumber();
			}
				return true;
		}
		

	}	

}




