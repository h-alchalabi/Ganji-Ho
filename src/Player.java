import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

	private boolean isWhite, tempBoolean=false; //If this variable is true, then the player is white. Otherwise, the player is black.
	private Board board; //Tieing a player to a board.
	private char tokenColor;
	Scanner in = new Scanner(System.in);
	private int temp;

	public Player(boolean isWhite, Board board) {
		this.isWhite = isWhite;
		this.board = board;
		if(isWhite)
			tokenColor='\u25CB'; /*if this doesn't work then just use 'W' */
		else
			tokenColor='\u25CF'; // if this doesn't work then just use 'B'.
	}
	public boolean getIsWhite(){//checks if the player is white.
		return this.isWhite;
	}
	public int validEntry(){//Checks if the input is an int.
		do{
			try{
				temp=in.nextInt();
				tempBoolean=true;
			}
			catch(InputMismatchException e){
				System.out.println("The input you entered is not an int, please re-enter a valid int.");
				in.nextLine();
			}
		}while(!tempBoolean);
		tempBoolean=false;
		return temp;
	}

	public boolean placeTokens(int r1, int c1, int r2, int c2){

		if( r1 > board.getRows() || c1 > board.getColumns() || r2 > board.getRows() || c2 > board.getColumns() ||
				c1 <= 0 || c2 <= 0 || r1 <= 0 || r2 <= 0){ //this will guarantee that the input is within the range of the board.
			//i.e. It will make sure that the rows and columns given will not go out of bounds of the board array.
			System.out.println("At least one of the rows or columns is out of bounds.\n");
			return false;
		}

		if(isWhite){
			if(c1 == c2 && (r1 == (r2+1) || (r1 == (r2-1)))){//This statement makes sure that the columns are equal. Since white can only place tokens in the same
				//column. It will also make sure that it is directly above it or below it.
				if(board.getToken(r1, c1) == '\u0000' && board.getToken(r2, c2) == '\u0000'){//check to see that the input given can be placed. Meaning they have null
					board.setToken(r1, c1, tokenColor);										//Values in the array index.
					board.setToken(r2, c2, tokenColor);
					return true;
				}
				else{
					System.out.println("Atleast one of the values you entered is occupied.\n");//if error occurs notify user.
					return false;
				}

			}
			else{
				System.out.println("The values you have given are not next to each other.\n");//if error occurs notify user.
				return false;
			}
		}
		//if the player is black.
		else{
			if(r1 == r2 && (c1 == (c2+1) || (c1 == (c2-1)))){//This statement makes sure that the rows are equal. Since black can only place tokens in the same
				//row. It will also make sure that it is directly to the left of it or to the right of it it.
				if(board.getToken(r1, c1) == '\u0000' && board.getToken(r2, c2) == '\u0000'){
					board.setToken(r1, c1, tokenColor);
					board.setToken(r2, c2, tokenColor);
					return true;
				}
				else{
					System.out.println("Atleast one of the values you entered is occupied.\n");
					return false;
				}

			}
			else{
				System.out.println("The values you have given are not next to each other.\n");
				return false;
			}

		}

	}

	public char tokenColor() {//Returns the token color of the player.
		return  tokenColor;
	}


}
