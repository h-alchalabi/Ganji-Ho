import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public class TreeNode<Board> implements Iterable<TreeNode<Board>>{
	
	/*
	 * Tree Structure.
	 */
	private TreeNode<Board> parent;//Parent node
	private Board data;//the board state for that node
	private LinkedList<TreeNode<Board>> children; //list of child nodes
	private int[] value; //the move it made to get there.
	private int heursticVal = 0;

	public TreeNode(Board data){//Create a new node, set parent to null. Board to board, etc
		this.data = data;
		this.children = new LinkedList<TreeNode<Board>>();
		this.parent = null;
		this.value = new int[4];
	}
	public TreeNode<Board> addChild(Board board){ //adding a child to a parent, returning it is sometimes useful.
		TreeNode<Board> childNode = new TreeNode<Board>(board);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}
	//Setter and getter for the move positions on the board.
	public void setValue(int row, int col, int row1, int col1){
		this.value[0] = row;
		this.value[1] = col;
		this.value[2] = row1;
		this.value[3] = col1;
	}
	public int[] getValues(){
		int[] temp = new int[value.length];
		for(int i=0;i< value.length;i++){
			temp[i] = value[i];
		}
		return temp;
	}
	public int getHeursticVal(){
		return heursticVal;
	}
	public void setHeursticVal(int val){
		this.heursticVal = val;
	}
	//standard Tree methods
	public boolean isRoot(){
		return parent == null;
	}
	public TreeNode<Board> getParent(){
		return this.parent;
	}
	public void setParent(TreeNode<Board> parent){
		this.parent = parent;
	}
	public Board getData(){
		return data;
	}
	public boolean hasChildren(){
		return !children.isEmpty();
	}
	public LinkedList<TreeNode<Board>> getChildren(){
		return this.children;
	}
	@Override
	public Iterator<TreeNode<Board>> iterator() {
		// TODO Auto-generated method stub
		return this.children.iterator();
	}
}
