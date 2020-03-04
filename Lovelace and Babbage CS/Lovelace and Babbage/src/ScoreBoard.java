import java.util.ArrayList;
import java.util.Arrays;
/**
 * The score board class has a score board that
 * updates itself every round. At the end of the 
 * rounds it displays itself.
 * @author Tanvir
 *
 */
public class ScoreBoard {
	String[][] scoreboard;
	Host host = new Host();
	private int gameRuns;
	private int regCor=0;
	private int advCor=0;
	private int regAvgTime=0;
	private int advAvgTime=0;
	private int regWins =0;
	private int advWins =0;
	public ScoreBoard(int gameRuns) {
		this.gameRuns= gameRuns;
		scoreboard = new String[5][2];
		
		
	}
	/**
	 * After each round, this method is called to determine who is the winner of the round.
	 * A score is created factoring in the number of moves and execution speed, and whoever
	 * has the lowest, wins the round.
	 * @param regMoves - number of moves from the Regular Player
	 * @param advMoves - number of moves from the Smart Player
	 * @param regTime  - execution time for regular Player
	 * @param advTime  - execution time for Smart Player
	 */
	public void gatherResults(ArrayList<String> regMoves, ArrayList<String> advMoves, long regTime, long advTime) {
		int regScore =0;
		int advScore =0;
		regAvgTime += regTime;
		advAvgTime += advTime;
		if (regMoves != null) {
			regCor++;
			regScore += regMoves.size()*1000;
			regScore+= regTime;
		}
		if (advMoves != null) {
			advCor++;
			advScore+= advMoves.size()*1000;
			advScore += advTime;
		}
		if (regScore!=0 && advScore == 0) {
			regWins++;
		}
		else if (regScore==0 && advScore!= 0) {
			advWins++;
		
		}
		else if (regScore<advScore) {
			regWins++;
		}else if (regScore>advScore) {
			advWins++;
	//		System.out.println("SMART WON !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	//	System.out.println(regScore +" | " +advScore);
		
		
	}
	
	/**
	 * This method is called after the round to display the results. 
	 *
	 */
	public void displayResults() {
		regAvgTime = regAvgTime/(gameRuns/2);
		advAvgTime = advAvgTime/(gameRuns/2);
		
		scoreboard[0][0]="SCORE ";
		scoreboard[0][1]=" BOARD";
		scoreboard[1][0]="Regular Player";
		scoreboard[1][1]="Advanced Player";
		scoreboard[2][0]= "Correct: "+ regCor;
		scoreboard[2][1]="Correct: "+ advCor;
		scoreboard[3][0]= "Avg Time: "+ regAvgTime;
		scoreboard[3][1]= "Avg Time: "+ advAvgTime;
		scoreboard[4][0]= "Wins: "+ regWins;
		scoreboard[4][1] = "Wins: "+ advWins;
		System.out.println();
		System.out.println("*******************************************");
		System.out.printf("%27s %n",scoreboard[0][0]+scoreboard[0][1]);
		
		System.out.println("-------------------------------------------");
		System.out.printf("%-21s %21s %n",scoreboard[1][0],scoreboard[1][1]);
		System.out.printf("%-21s %21s %n",scoreboard[2][0],scoreboard[2][1]);
		System.out.printf("%-21s %21s %n",scoreboard[3][0],scoreboard[3][1]);
		System.out.printf("%-21s %21s %n",scoreboard[4][0],scoreboard[4][1]);
		System.out.println("-------------------------------------------");
		System.out.println("*******************************************");
	}
	

}
