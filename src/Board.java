
public class Board {

	private char[][] board;
	private int rows, columns;

	public Board(int rows, int columns) {
		this.rows=rows;
		this.columns=columns;
		this.board = new char[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	public char getToken(int r, int c){
		return board[r-1][c-1];
	}
	public char getTokenForAi(int r, int c){
		return board[r][c];
	}
	public void setToken(int r, int c, char color){
		board[r-1][c-1]=color;
	}
	public void setTokenForAi(int r, int c, char color){
		board[r][c]=color;
	}
	public Board copy(){
		Board copyOfBoard = new Board(rows, columns);
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				copyOfBoard.setTokenForAi(i, j, board[i][j]);
			}
		}
		return copyOfBoard;
	}

	public void display(){
		for(int i=0; i<rows;i++){
			for(int j=0; j<columns;j++){
				if(i==0 && j==0){
					System.out.print("[" +i + "]");
					for(int k=0; k<columns;k++){
						System.out.print("[" + (k+1) + "]");
					}
					System.out.println();
				}
				if(j==0){
					System.out.print("[" + (i+1) + "]");
				}
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//What this method does is:
	//It takes a player: checks whether the player is white or black. This is important because it determines how the board should be checked.
	public boolean gamestate(Player player){

		//if the player is white.
		if(player.getIsWhite()){
			for(int i=1;i<rows-1;i++){//I opted to start at second row and continue checking until the before last one. This way I wouldn't need special ifs
				for(int j=0;j<columns;j++){//to make sure the check doesn't go out of bounds. 
						if(board[i][j] == '\u0000' && (board[i+1][j] == '\u0000' || board[i-1][j] == '\u0000'))
							return true;
				}
			}
			return false;//return false if and only if there are no available slots to be played
		}
		//if the player is black.
		else{
			for(int i=0;i<rows;i++){//I opted to start at second column and continue checking until the before last one. This way I wouldn't need special ifs
				for(int j=1;j<columns-1;j++){//to make sure the check doesn't go out of bounds.
						if(board[i][j] == '\u0000' && (board[i][j-1] == '\u0000' || board[i][j+1] == '\u0000'))
							return true;
				}
			}
			return false;//return false if and only if there are no available slots to be played
		}

	}
	public boolean gamestate(AI player){

		//if the player is white.
		if(player.getIsWhite()){
			for(int i=1;i<rows-1;i++){//I opted to start at second row and continue checking until the before last one. This way I wouldn't need special ifs
				for(int j=0;j<columns;j++){//to make sure the check doesn't go out of bounds. 
						if(board[i][j] == '\u0000' && (board[i+1][j] == '\u0000' || board[i-1][j] == '\u0000'))
							return true;
				}
			}
			return false;//return false if and only if there are no available slots to be played
		}
		//if the player is black.
		else{
			for(int i=0;i<rows;i++){//I opted to start at second column and continue checking until the before last one. This way I wouldn't need special ifs
				for(int j=1;j<columns-1;j++){//to make sure the check doesn't go out of bounds.
						if(board[i][j] == '\u0000' && (board[i][j-1] == '\u0000' || board[i][j+1] == '\u0000'))
							return true;
				}
			}
			return false;//return false if and only if there are no available slots to be played
		}

	}

}
