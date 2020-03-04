import java.io.IOException;
import java.util.*;
/**
 * The main class, responsible for running the program 
 * in the correct order and also having the runGames method
 * that runs N rounds.
 * @author Tanvir
 *
 */
public class Main {
	public static Player player1;
	public static Player player2;
	public static int gameCounter = 0;
	public static GameBoard gameBoard;
	public static ScoreBoard scoreBoard;
	/**
	 * The main method creates the player, score board and game board 
	 * objects and then just calls the run games method. Pretty minimal.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
	
	Host.greet();
	player1 = new Player("Regular");
	if (Host.smartOrGenius) {
		 player2 = new Player("Smart");
	}
	 scoreBoard = new ScoreBoard(Host.getGameRuns());
	
	gameBoard = new GameBoard();
	
	runGames();
	
	
	
	}
/**
 * This method calls methods from other classes in the order to run each round. 
 * It gets the number of rounds from the Host object, and iterates through every round
 * until it is finished. Once finished, the scoreBoard is called to display the results. 	
 * @throws IOException
 */
	public static void runGames() throws IOException {
		for (int i =0;i<Host.gameRuns;i++) {
			ArrayList<String> moves = null;
			ArrayList<String> smartMove = null;
			long durationR = 0;
			long durationS = 0;
			
			gameCounter++;
			System.out.printf("%25s %n","(Round: " +gameCounter+")");
			int given = Host.genGiven();
			int goal = Host.genGoal();
			System.out.println("\nGiven: " + given+ "                     Goal: "+ goal +"\n" );
			try {
			long startTime = System.nanoTime();
			
			 moves = Player.play("Regular", given, goal);
			
			long endTime = System.nanoTime();
			 durationR = (endTime - startTime);
			System.out.println("REGULAR PC --> "+moves.toString());
			System.out.println("Time: "+durationR+" nanoseconds\n");
			
			}
			catch (NullPointerException e) {
				System.out.println("REGULAR could not find a solution");
			} 
			//-------------------------------------------------------------------------
			try {
			
			
			smartMove = Player.play("Smart", given, goal);
			
			
			System.out.println("SMART PC --> "+smartMove.toString());
			durationS = Player.smartPC.duration;
			System.out.println("Time: "+durationS+" nanoseconds");
			}
			catch (NullPointerException e) {
				System.out.println("*** SMART could not find a solution ***");
			}
			if (gameCounter > (Host.getGameRuns()/2)-1)
			scoreBoard.gatherResults(moves, smartMove, durationR, durationS);
			System.out.println("___________________________________________");
		}
		scoreBoard.displayResults();
	}
}
