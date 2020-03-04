import java.io.*;
import java.io.IOException;
import java.util.*;
/**
 * This class is not a part of the program, instead it was used to test potential features.
 * This contains a implementation of finding all possible combinations of a solution using
 * N-ary trees. 
 * @author Tanvir
 *
 */
public class Test {
	public static Node root= new Node (8,"" ,null,null,null);
	public static ArrayList<String> trialSheet = new ArrayList<String>();
	/**
	 * Driver Method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		genius(root, 18,false, trialSheet);
		System.out.println(trialSheet.toString());
		

	}
	
	
	/**
	 * Attempt to send in a node, and recursively find which one of its 
	 * child nodes would lead to a solution. The goal is to find the shortest
	 * possible solution, something the other players can't do. 
	 * @param node - A parent node
	 * @param goal - number to be reached
	 * @param add - useless boolean
	 * @param trial - ArrayLists that adds to itself with moves
	 */
	public static void genius (Node node, int goal,boolean add, ArrayList<String> trial){
		System.out.println(trial.size());
		System.out.println(trial.toString());
		
		
		if (node.data == goal) {
	System.out.println("GOAL");
			return ;
		}
		
		if (trial.size()>=5) {
			System.out.println("REACHED");
			return;
		}
		if(node.data != goal) {
			System.out.println("NODE");
			node.left10 = new Node(node.data+1, "+1", node.left10, node.left9, node.left8);
			node.left9 = new Node(node.data+5, "+5",  node.left10, node.left9, node.left8);
			node.left8 = new Node(node.data+10, "+10",  node.left10, node.left9, node.left8);
			 genius(node.left10, goal, trial.add("+1"), trial );
				genius(node.left9, goal, trial.add("+5"), trial );
				genius(node.left8, goal, trial.add("+10"), trial );
		}
		
	   
		
		
	}

}
/**
 * A node class holds a data value, and is also a 
 * parent to 20 other child nodes.
 * @author Tanvir
 *
 */
class Node{
	int data; 
	String move;
	Node left10;
	Node left9;
	Node left8;
	
	Node (int data, String move, Node left10, Node left9, Node left8){
		this.data  =data;
		this.move = move;
		this.left10 = left10;
		this.left9 = left9;
		this.left8 = left8;
	}
}