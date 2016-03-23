
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mode {
	private Scanner in = new Scanner(System.in);
	
	public Mode() {
		super();
	}

	public void selectMode(){
		int temp=0;
		boolean tempBoolean = false;
		do{
			try{
				temp=in.nextInt();
				if(temp >0 && temp < 5)
					tempBoolean=true;
				else{
					System.out.println("The number you have inputted is invalid. Please Enter a valid Number.");
					in.nextLine();
					}
			}
			catch(InputMismatchException e){
				System.out.println("The input you entered is not an int, please re-enter a valid int.");
				in.nextLine();
			}
		}while(!tempBoolean);
		//tempBoolean=false;
		if(temp == 1)
			playerVplayer();
		else if(temp == 2)
			playerVWhiteComp();
		else if(temp == 3)
			playerVBlackComp();
		else
			compVcomp();
	}
	
	
	

	public void playerVplayer(){
		Board board = new Board(8,8);//Setting up board size.
		//Player player1 = new Player(true, board); //White player
		//	player2 = new Player(false, board); //Black player

		Player[] playerArray = new Player[2];
		playerArray[0] = new Player(true, board);//White player
		playerArray[1] = new Player(false, board);//Black player

		boolean gameValid = true, validInput=false;
		int playerTurn = 1, row1=0, row2=0, column1=0, column2=0;
		AI computer = new AI(false, board);
		board.display();

		while(gameValid){

			gameValid = board.gamestate(playerArray[playerTurn-1]);//check to see if there are any available moves.
			if(!gameValid){

				if(playerTurn==1){//Setting it to the previous player.
					playerTurn++;
				}
				else
					playerTurn--;
				System.out.println("Player " + playerTurn + " wins.");
				break;
			}

			System.out.println("Player " +  playerTurn + " turn to play.(" + playerArray[playerTurn-1].tokenColor() + ")\n");


			do{//keep looping till the user gives a valid input to the board. Also making sure the inputs are ints.
				System.out.println("Enter where you would like to place your two tokens: Row1, Column1, Row2, Column2. Hit enter after each input.");
				System.out.println("Enter a value for row1");
				row1 = playerArray[playerTurn-1].validEntry();
				System.out.println("Enter a value for column1");
				column1=playerArray[playerTurn-1].validEntry();
				System.out.println("Enter a value for row2");
				row2=playerArray[playerTurn-1].validEntry();
				System.out.println("Enter a value for column2");
				column2=playerArray[playerTurn-1].validEntry();
				validInput = playerArray[playerTurn-1].placeTokens(row1, column1, row2, column2);//if all the inputs are valid and the inputs are ints. Then place them.
			}while(!validInput);


			System.out.println();
			board.display();//Displaying the board.

			if(playerTurn==1)//Switching players.
				playerTurn++;
			else
				playerTurn--;

		}
	}
	public void playerVWhiteComp(){
		Board board = new Board(8,8);//Setting up board size.
		Player player = new Player(false, board); //White player
		boolean gameValid = true, validInput=false;
		int playerTurn = 1, row1=0, row2=0, column1=0, column2=0;
		AI computer = new AI(true, board);

		board.display();

		while(gameValid){

			if(playerTurn == 1){
				gameValid = board.gamestate(computer);
			}
			else{
				gameValid = board.gamestate(player);
			}
			if(!gameValid){
				if(playerTurn==1)
					playerTurn++;
				else
					playerTurn--;
				System.out.println("Player " + playerTurn + " wins.");
				break;
			}


			if(playerTurn == 2){
				System.out.println("Player " + playerTurn + " turn to play. (" + player.tokenColor() + ")\n");
				do{//keep looping till the user gives a valid input to the board. Also making sure the inputs are ints.
					System.out.println("Enter where you would like to place your two tokens: Row1, Column1, Row2, Column2. Hit enter after each input.");
					System.out.println("Enter a value for row1");
					row1 = player.validEntry();
					System.out.println("Enter a value for column1");
					column1=player.validEntry();
					System.out.println("Enter a value for row2");
					row2=player.validEntry();
					System.out.println("Enter a value for column2");
					column2=player.validEntry();
					validInput = player.placeTokens(row1, column1, row2, column2);//if all the inputs are valid and the inputs are ints. Then place them.
				}while(!validInput);
			}
			else{
				System.out.println("AI turn to play. (" + computer.getToken() + ")\n");
				computer.makeMove(board);
			}

			System.out.println();
			board.display();//Displaying the board.

			if(playerTurn==1)//Switching players.
				playerTurn++;
			else
				playerTurn--;
		}
	}
	public void playerVBlackComp(){
		Board board = new Board(8,8);//Setting up board size.
		Player player1 = new Player(true, board); //White player
		boolean gameValid = true, validInput=false;
		int playerTurn = 1, row1=0, row2=0, column1=0, column2=0;
		AI computer = new AI(false, board);

		board.display();

		while(gameValid){

			if(playerTurn == 1){
				gameValid = board.gamestate(player1);
			}
			else{
				gameValid = board.gamestate(computer);
			}
			if(!gameValid){
				if(playerTurn==1)
					playerTurn++;
				else
					playerTurn--;
				System.out.println("Player " + playerTurn + " wins.");
				break;
			}


			if(playerTurn == 1){
				System.out.println("Player " + playerTurn + " turn to play. (" + player1.tokenColor() + ")\n");
				do{//keep looping till the user gives a valid input to the board. Also making sure the inputs are ints.
					System.out.println("Enter where you would like to place your two tokens: Row1, Column1, Row2, Column2. Hit enter after each input.");
					System.out.println("Enter a value for row1");
					row1 = player1.validEntry();
					System.out.println("Enter a value for column1");
					column1=player1.validEntry();
					System.out.println("Enter a value for row2");
					row2=player1.validEntry();
					System.out.println("Enter a value for column2");
					column2=player1.validEntry();
					validInput = player1.placeTokens(row1, column1, row2, column2);//if all the inputs are valid and the inputs are ints. Then place them.
				}while(!validInput);
			}
			else{
				System.out.println("AI turn to play. (" + computer.getToken() + ")\n");
				computer.makeMove(board);
			}

			System.out.println();
			board.display();//Displaying the board.

			if(playerTurn==1)//Switching players.
				playerTurn++;
			else
				playerTurn--;
		}
	}
	public void compVcomp(){
		Board board = new Board(8,8);
		AI whiteComp = new AI(true, board);
		AI blackComp = new AI(false, board);
		int playerTurn = 1;
		boolean gameValid = true;
		
		board.display();
		
		while(gameValid){

			if(playerTurn == 1){
				gameValid = board.gamestate(whiteComp);
			}
			else{
				gameValid = board.gamestate(blackComp);
			}
			if(!gameValid){
				if(playerTurn==1)
					playerTurn++;
				else
					playerTurn--;
				System.out.println("Player " + playerTurn + " wins.");
				break;
			}


			if(playerTurn == 1){
				System.out.println("AI turn to play. (" + whiteComp.getToken() + ")\n");
				whiteComp.makeMove(board);
			}
			else{
				System.out.println("AI turn to play. (" + blackComp.getToken() + ")\n");
				blackComp.makeMove(board);
			}

			System.out.println();
			board.display();//Displaying the board.

			if(playerTurn==1)//Switching players.
				playerTurn++;
			else
				playerTurn--;
		}


	}

}
