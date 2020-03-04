import java.util.*;
/**
 * The Host object is a object that organizes the rounds. 
 * It is used to greet the player and create problem sets
 * each round
 * @author Tanvir
 *
 */
public class Host {
public static int gameRuns = 0;
public static boolean smartOrGenius;
	/**
	 * The greet method is used to introduce the player to the game.
	 * It contains instructions for how the game plays out and asks
	 * the user about how many games they want to run. 
	 */
	public static void greet() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello Spectator to a PC vs PC version of the game \"Lovelace and Babbage\"!");
		formatPrint();
		System.out.printf("%50s %n", "*** How the game works: *** ");
		formatPrint();
		System.out.println( "1. You enter the number of rounds (N) you would like in a game\n\n"
				+ "2. Then for each round:\n "
				+ "	-The Host (myself) generates a 'given' number and a 'goal' number\n"
				+ "	-Both players use their strategies to select moves from the board,\n"
				+ "	-While the players are making their decisions they are timed.\n"
				+ "	-Whichever player gets the right solution in the least time or the least moves, wins the round\n\n"
				+ "3. At the end of N rounds, the scoreboard displays the statistics of the game"
				+ "");
		formatPrint();
		
		System.out.printf("%55s %n","How many games would you like to run? ");
		setGameRuns(sc.nextInt()*2);	
		smartOrGenius = true;
	
		sc.close();
	}
	/**
	 * Getter method for the gameRuns integer. 
	 * @return int gameRuns
	 */
	static int getGameRuns() {
		return gameRuns;
	}
	/**
	 * Setter method for the gameRuns integer.
	 * @param gameRuns
	 */
	private static void setGameRuns(int gameRuns) {
		Host.gameRuns = gameRuns;
	}
	
	/**
	 * This method is called when a round needs a given number. 
	 * This number is a randomly generated number between 1-1000.
	 * Players must modify the given number with moves to reach the 
	 * goal number.
	 * @return A given number
	 */
	static int genGiven() {
		
		return (int)(Math.random()*1000);
	}
	/**
	 * This is called when a round needs a goal number
	 * This number is a randomly generated number between 1-1000.
	 * @return A goal number
	 */
	static int genGoal() {
	
		return (int)(Math.random()*1000);
	}
	/**
	 * Simple method that prints a line seperator when called. 
	 */
	static void formatPrint() {
		System.out.println("----------------------------------------------------------------------------");
	}
	
}
