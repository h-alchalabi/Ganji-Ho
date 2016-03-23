import java.util.Iterator;
import java.util.Vector;
import java.util.LinkedList;

public class AI {
	/*
	 * Methods: (Brief description of what each method does.
	 * AI() -> Constructs the AI object
	 * makeMove() -> generates all the things that are needed to make a move, and then makes the move.
	 * evaluate() -> evaluates the current state of the board, This is my heustric.
	 * row/column Full/Empty () -> this method is used by the heustric.
	 * invertColor() -> inverts the color.
	 * generateMoves() -> this method is one of the most crucial methods, it generates 2-ply game tree that which all the AI's decision depends on.
	 * evaluateTree() -> this method is also very crucial this generates the heustric values.
	 * 
	 */


	private boolean isWhite;
	private char tokenColor;
	private Board board;
	private int rows, columns;
	private TreeNode<Board> tree;

	//Constructor.
	public AI(boolean isWhite, Board board){
		this.isWhite = isWhite;
		this.board = board;
		this.columns = this.board.getColumns();
		this.rows = this.board.getRows();
		this.tree = new TreeNode<Board>(board);
		if(isWhite)
			tokenColor = '\u25CB';
		else
			tokenColor = '\u25CF';
	}
	//This calls all necessary methods to make a move.
	public void makeMove(Board board){
		tree = new TreeNode<Board>(board);
		generateMoves(board);
		int[] moveArray = evaluateTree();
		board.setTokenForAi(moveArray[0], moveArray[1], tokenColor);
		board.setTokenForAi(moveArray[2], moveArray[3], tokenColor);
		for(int i = 0; i<moveArray.length;i++){
			System.out.print((moveArray[i] + 1) + " ");
		}
	}

	//Setters/Getters
	public char getToken(){
		return this.tokenColor;
	}
	public void setTree(TreeNode<Board> tree){
		this.tree = tree; 
	}

	//This gets the total heustric value.
	public int evaluate(int[] moves){
		int total = 0;
		total += eval(moves);
		total += blockEval(moves);
		total += twoAwayEval(moves);
		return total;
	}

	//this heustric is favors moves that are not on edges.
	public int eval(int[] moves){
		int total = 0;
		int row1 = moves[0], col1 = moves[1], row2 = moves[2], col2 = moves[3];
		if(isWhite){
			if(col1 > 0 && col1 < board.getColumns()-1)
				total += 800;
			else
				total += 10;
		}
		else{
			if(row1 > 0 && row1 < board.getRows()-1)
				total += 800;
			
			else
				total += 10;
		}
		return total;
	}

	//This heustric is used to see how many moves the move will block. White checks left and right while black checks up and down.
	public int blockEval(int[] moves){
		int total = 0, counter = 0;
		int row1 = moves[0], col1 = moves[1], row2 = moves[2], col2 = moves[3];
		if(isWhite){
			//since in white the col is constant, we only need to check for one of them.
			if(col1 == 0){
				if(board.getTokenForAi(row1, col1+1) == '\u25CF')
					counter++;
				if(board.getTokenForAi(row2, col1+1) == '\u25CF')
					counter++;
				if(counter > 0)
					return total = counter * 100;
				else
					return total += 10;
			}
			else if(col1 == board.getColumns() - 1){
				if(board.getTokenForAi(row1, col1-1) == '\u25CF')
					counter++;
				if(board.getTokenForAi(row2, col1-1) == '\u25CF')
					counter++;
				if(counter > 0)
					return total = counter * 100;
				else
					return total = 10;
			}
			else{
				if(board.getTokenForAi(row1, col1-1) == '\u25CF')
					counter++;
				if(board.getTokenForAi(row1, col1+1) == '\u25CF')
					counter++;
				if(board.getTokenForAi(row2, col1-1) == '\u25CF')
					counter++;
				if(board.getTokenForAi(row2, col1+1) == '\u25CF')
					counter++;
				if(counter > 0)
					return total = counter * 100;
				else
					return total = 10;
			}
		}
		else{
			if(row1 == 0){
				if(board.getTokenForAi(row1+1, col1) == '\u25CB')
					counter++;
				if(board.getTokenForAi(row1+1, col2) == '\u25CB')
					counter++;
				if(counter > 0)
					return total = counter * 100;
				else
					return total = 10;
			}
			else if(row1 == board.getRows() - 1){
				if(board.getTokenForAi(row1-1, col1) == '\u25CB')
					counter++;
				if(board.getTokenForAi(row1-1, col2) == '\u25CB')
					counter++;
				if(counter > 0)
					return total = counter * 100;
				else
					return total = 10;
			}
			else{
				if(board.getTokenForAi(row1-1, col1) == '\u25CB')
					counter++;
				if(board.getTokenForAi(row1+1, col1) == '\u25CB')
					counter++;
				if(board.getTokenForAi(row2-1, col1) == '\u25CB')
					counter++;
				if(board.getTokenForAi(row2+1, col1) == '\u25CB')
					counter++;
				if(counter > 0)
					return total = counter * 100;
				else
					return total = 10;
			}
		}
	}
	//This evaluation is to guarantee you another move.
	//White checks two away from itself, if the position is a white pieces then it will rate it as higher.
	//black checks two away from itself, in terms of rows, if it is two blacks pieces it will be rated higher.
	public int twoAwayEval(int[] moves){
		int total = 0;
		int row1 = moves[0], col1 = moves[1], row2 = moves[2], col2 = moves[3];
		if(isWhite){
			if(board.getColumns() - 1 == col1 || board.getColumns()-1 == col1+1){
				if(board.getTokenForAi(row1, col1-2) == '\u25CF' && board.getTokenForAi(row1, col2-2) == '\u25CF')
					return 200;
				else 
					return 0;
			}
			else if(0 == col1-1 || 0 == col1){
				if(board.getTokenForAi(row1, col1+2) == '\u25CF' && board.getTokenForAi(row1, col2+2) == '\u25CF')
					return 200;
				else
					return 0;
			}
			else{
				int counter = 0;
				if(board.getTokenForAi(row1, col1 + 2) == '\u25CF' && board.getTokenForAi(row1, col2+2) == '\u25CF')
					counter++;
				if(board.getTokenForAi(row1, col1-2) == '\u25CF' && board.getTokenForAi(row1, col2 -2) == '\u25CF')
					counter++;
				return counter * 200;
				
			}
		}
		else{
			if(board.getRows() - 1 == row1 || board.getRows()-1 == row1+1){
				if(board.getTokenForAi(row1 - 2, col1) == '\u25CF' && board.getTokenForAi(row1 - 2, col2) == '\u25CF')
					return 200;
				else
					return 0;
			}
			else if(0 == row1-1 || 0 == row1){
				if(board.getTokenForAi(row1 + 2, col1) == '\u25CF' && board.getTokenForAi(row1 + 2, col2) == '\u25CF')
					return 200;
				else return 0;
			}
			else{
				int counter = 0;
				if(board.getTokenForAi(row1 + 2, col1) == '\u25CF' && board.getTokenForAi(row1+2, col2) == '\u25CF')
					counter++;
				if(board.getTokenForAi(row1 - 2, col1) == '\u25CF' && board.getTokenForAi(row1 - 2, col1) == '\u25CF')
					counter++;
				return counter * 200;
				
			}
		}
	}

	//inverts the colors of the AI, this is used for the generate.
	public void invertColor(){
		if(isWhite){
			isWhite=false;
			tokenColor = '\u25CF';
		}

		else{
			isWhite=true;
			tokenColor = '\u25CB';
		}
	}

	public boolean getIsWhite(){
		return isWhite;
	}

	//Traversing the tree...
	//min max algorithm
	public int[] evaluateTree(){

		Iterator<TreeNode<Board>> iter = tree.getChildren().iterator(); //gets the children of the root.
		while(iter.hasNext()){ //while the there is still nodes
			TreeNode<Board> nextNode = iter.next();//it takes that node
			Iterator<TreeNode<Board>> childIter = nextNode.getChildren().iterator();//gets its children
			while(childIter.hasNext()){//while there are more children
				TreeNode<Board> childNode = childIter.next();//it takes that child
				int temp = evaluate(childNode.getParent().getValues());//it gets evaluated
				childNode.setHeursticVal(temp);//it sets the heustric value.
				if(temp > nextNode.getHeursticVal()){//it tests against its parent
					nextNode.setHeursticVal(temp);//if its greater than its parent than it will set its parent's heustric to that.
				}
			}
		}

		int maxNodeVal=-1; 
		TreeNode<Board> finalNode = null;
		iter = tree.getChildren().iterator();//resetting the iterator.
		while(iter.hasNext()){//while there is still nodes.
			TreeNode<Board> childNode = iter.next();//checks to find the greatests val.
			if(childNode.getHeursticVal() > maxNodeVal){//if it is the greatest, then return the move that was made.
				maxNodeVal = childNode.getHeursticVal();
				finalNode = childNode;
			}
		}
		return finalNode.getValues();

	}





	//A method to display the tree, it displays it in Depth-First Fashion.
	public void displayTreeData(){
		Iterator<TreeNode<Board>> iter = tree.iterator();
		while(iter.hasNext()){
			TreeNode<Board> sample = iter.next();
			Iterator<TreeNode<Board>> itera = sample.iterator();
			((TreeNode<Board>) sample).getData().display();
			while(itera.hasNext()){
				TreeNode<Board> sample2 = itera.next();
				sample2.getData().display();
			}
		}
	}

	/*
	 * For the next 3 algorithms, the logic is the same.
	 * generateMoves() -> this method is used as a generic way to call the method.
	 * I'll only comment one.
	 */



	public void generateMoves(Board board){
		if(isWhite)
			this.generateMovesWhite(board, tree);
		else
			this.generateMovesBlack(board, tree);
	}

	private void generateMovesWhite(Board board, TreeNode<Board> boardNode){
		if(isWhite){																						//Tests all possible moves
			for(int i=0;i<rows-1;i++){																		//for every possible move, it will create a node
				for(int j=0;j<columns;j++){																	//it will store the moves values.
					if(board.getTokenForAi(i, j) == '\u0000' && board.getTokenForAi(i+1, j) == '\u0000'){	//creates a node and makes it a child of the parent
						Board copy = board.copy();															//
						copy.setTokenForAi(i, j, tokenColor);												//
						copy.setTokenForAi(i+1, j, tokenColor);												//
						TreeNode<Board> childNode = boardNode.addChild(copy);								//
						childNode.setValue(i, j, i+1, j);													//
						//copy.display();																	//
						invertColor();																		//<-This is done so that it will test the next player's move
						generateMovesWhite(copy, childNode);												//it tests all the moves the next player can make
						invertColor();																		//and ensures that it set back to the proper color.

					}
				}
			}
		}
		else{																								//Checks all possible moves
			for(int i=0;i<rows;i++){																		//and makes childs to link to parent from above.
				for(int j=0;j<columns-1;j++){
					if(board.getTokenForAi(i, j) == '\u0000' && board.getTokenForAi(i, j+1) == '\u0000'){
						Board copy = board.copy();
						copy.setTokenForAi(i, j, tokenColor);
						copy.setTokenForAi(i, j+1, tokenColor);
						boardNode.addChild(copy);
						//copy.display();
					}
				}
			}
		}
	}

	private void generateMovesBlack(Board b, TreeNode<Board> boardNode){

		if(isWhite){
			for(int i=0;i<rows-1;i++){
				for(int j=0;j<columns;j++){
					if(b.getTokenForAi(i, j) == '\u0000' && board.getTokenForAi(i+1, j) == '\u0000'){
						Board copy = b.copy();
						copy.setTokenForAi(i, j, tokenColor);
						copy.setTokenForAi(i+1, j, tokenColor);
						boardNode.addChild(copy);
						//copy.display();
					}
				}
			}
		}
		else{
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns-1;j++){
					if(board.getTokenForAi(i, j) == '\u0000' && board.getTokenForAi(i, j+1) == '\u0000'){
						Board copy = b.copy();
						copy.setTokenForAi(i, j, tokenColor);
						copy.setTokenForAi(i, j+1, tokenColor);
						TreeNode<Board> childNode = tree.addChild(copy);
						childNode.setValue(i, j, i, j+1);
						//copy.display();
						invertColor();
						generateMovesBlack(copy, childNode);
						invertColor();
					}
				}
			}
		}
	}


	//Used to evaluate.
	//important to remember that rows and columns start at 0 for Ai.
	//These methods are not used.
	public boolean rowEmpty(Board b, int row){
		for(int i=0;i<columns;i++){
			if(b.getTokenForAi(row, i) != '\u0000')
				return false;
		}
		return true;
	}

	public boolean columnEmpty(Board b,int column){
		for(int i=0; i<rows;i++){
			if(b.getTokenForAi(i, column) != '\u0000')
				return false;
		}
		return true;
	}

	public boolean rowFull(Board b,int row){

		for(int i=0;i<columns;i++){
			if(b.getTokenForAi(row, i) == '\u0000')
				return false;
		}
		return true;
	}

	public boolean columnFull(Board b, int column){
		for(int i=0; i<rows;i++){
			if(b.getTokenForAi(i, column) == '\u0000')
				return false;
		}
		return true;
	}



}
