import java.io.*;
import java.util.*;
/**
 * The player class holds code for both the player's 
 * strategies. 
 * @author Tanvir
 *
 */
public class Player {
	String playStyle;
	GameBoard gameBoard= new GameBoard();
	public static RegularPC regPC;
	public static SmartPC smartPC;
	public Player(String playStyle) {
		if (playStyle == "Regular") {
			regPC = new RegularPC();
		} else if (playStyle == "Smart") {
			smartPC = new SmartPC();
	
			
		}
	}
	/**
	 * Public method that is used by other methods. A problem set is inputed 
	 * along with what player should solve it. 
	 * @param playerType -There are only two playerTypes, Regular and Smart
	 * @param given - number to be modified into goal
	 * @param goal - number to be reached
	 * @return - returns ArrayLists of a string of moves, or null if there's no solution
	 * @throws IOException
	 */
	public static ArrayList<String> play(String playerType, int given, int goal) throws IOException {
		if (playerType.equals("Regular")) {
			return RegularPC.play(given, goal);
		}else if (playerType.equals("Smart")) {
			return SmartPC.play(given, goal);
		}
		else {
			return null;
		}
	}

}
/**
 * Class for the regular Player
 * @author Tanvir
 *
 */
class RegularPC {
	/**
	 * The regular player's strategy method. This is explained in the engineering report
	 * @param given - number to be modified into goal
	 * @param goal -number to be reached
	 * @return
	 */
	static ArrayList<String> play(int given, int goal) {
		
		
		 ArrayList<String> trialSheet = new ArrayList<String>();
		for (int i =0;i<5;i++) {
		scoreData[] desc = new scoreData[20];
		desc[0]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "+1")), "+1") ;
		desc[1]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "-1")), "-1") ;
		desc[2]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "+2")), "+2") ;
		desc[3]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "-2")), "-2") ;
		desc[4]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "+5")), "+5") ;
		desc[5]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "-5")), "-5") ;
		desc[6]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "><")), "><") ;
		desc[7]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "+10")), "+10") ;
		desc[8]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "-10")), "-10") ;
		desc[9]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "+50")), "+50") ;
		desc[10]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "-50")), "-50") ;
		desc[11]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "*2")), "*2") ;
		desc[12]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "/2")), "/2") ;
		desc[13]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "+100")), "+100") ;
		desc[14]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "-100")), "-100") ;
		desc[15]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "x^2")), "x^2") ;
		desc[16]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "-x")), "-x") ;
		desc[17]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "sqrt")), "sqrt") ;
		desc[18]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "*10")), "*10") ;
		desc[19]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, "/10")), "/10") ;
		
		int mIndex= desc.length;
		int min= Integer.MAX_VALUE;
				for (int j = 0;j<desc.length;j++) {
				int cur = desc[j].diff;
				if (cur<min) {
					min = cur;
					mIndex = j;
					}
				}
		trialSheet.add(desc[mIndex].move);
		//System.out.println(trialSheet.toString() + "\n");
		given = GameBoard.selectMove(given, desc[mIndex].move);		
				if (given == goal) {
					return trialSheet;
				}else if (trialSheet.size()==5&& given == goal ) {
					return trialSheet;
				}	
		}
		return null;
	}
	
}
/**
 * Class for the Smart Player, strategy explained in report.
 * @author Tanvir
 *
 */
class SmartPC{
	public static long duration = 0;
	private static int[][] under500over500 = new int[5][20];
	private static int[][] over500under500 = new int[5][20];
	private static int[][] underORover = new int[5][20];
	private static int[][] over500under100 = new int[5][20];
	private static int[][] under100over500 = new int[5][20];
	static File file = new File("SmartPC\\log.txt");
	static int[][][] fiveArrays = {under500over500, over500under500, underORover
								, over500under100, under100over500};
	/**
	 * Checks if there is log data, if there is, copy 
	 * all data to 3D Array
	 * @throws IOException
	 */
	private static void fileCheck() throws IOException {
		
	if (file.length()!=0) {
		Scanner fileScanner = new Scanner(file);
				
		for (int i =0;i<5;i++) {
			for (int j = 0;j<20;j++) {
				under500over500[i][j]= fileScanner.nextInt();
			}
		}
		for (int i =0;i<5;i++) {
			for (int j = 0;j<20;j++) {
				over500under500[i][j]= fileScanner.nextInt();
			}
		}
		for (int i =0;i<5;i++) {
			for (int j = 0;j<20;j++) {
				underORover[i][j]= fileScanner.nextInt();
			}
		}
		for (int i =0;i<5;i++) {
			for (int j = 0;j<20;j++) {
				over500under100[i][j]= fileScanner.nextInt();
			}
		}
		for (int i =0;i<5;i++) {
			for (int j = 0;j<20;j++) {
				under100over500[i][j]= fileScanner.nextInt();
			}
		}
		fileScanner.close();	
	}
			
		
	}

	/**
	 * If there is not enough log data, play the exact same way as the Regular player
	 * while logging the moves
	 * @param given
	 * @param goal
	 * @return - Returns a arrayList with the moves
	 * @throws FileNotFoundException
	 */
	private static ArrayList<String> earlyPlay(int given, int goal) throws FileNotFoundException {
	
		ArrayList<String> solution = RegularPC.play(given, goal);
		if (given<500&&goal>500) {
			for (int i =0;i<solution.size();i++) {
				ArrayUpdate(1,i,solution.get(i));
			}
		}
		if (given>500&&goal<500) {
			for (int i =0;i<solution.size();i++) {
				ArrayUpdate(2,i,solution.get(i));
			}
		}
		if (given>500&&goal>500||given<500&&goal<500) {
			for (int i =0;i<solution.size();i++) {
				ArrayUpdate(3,i,solution.get(i));
			}
		}
		if (given>500&&goal<100) {
			for (int i =0;i<solution.size();i++) {
				ArrayUpdate(4,i,solution.get(i));
			}
		}
		if (given<100&&goal>500) {
			for (int i =0;i<solution.size();i++) {
				ArrayUpdate(5,i,solution.get(i));
			}
		}
		writetoFile();
		return solution;
		
	}
	/**
	 * Organizes the problem into 1 of 5 cases and executes based on that.
	 * Narrows down to 3 moves and chooses the best one
	 * @param given
	 * @param goal
	 * @return
	 * @throws IOException
	 */
	static ArrayList<String> play(int given, int goal) throws IOException{
		 
		if (Main.gameCounter>Host.getGameRuns()/2) {
		ArrayList<String> trialSheet = new ArrayList<String>();
		fileCheck();
		String[][] choices = new String[5][3];
		long startTime = System.nanoTime();
		if (given>500&&goal<100) {
			for (int i =0;i<5;i++) {
				choices[i]= identifyChoices(4,i);
				scoreData[] desc = new scoreData[3];
				for(int j =0;j< desc.length;j++) {
					desc[j]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, choices[i][j])), choices[i][j]) ;
				}
				
				int mIndex = playHelper(desc);
				
				trialSheet.add(desc[mIndex].move);
			//	System.out.println(trialSheet.toString());
				given = GameBoard.selectMove(given, desc[mIndex].move);		
				if (given == goal) {
					return trialSheet;
				}else if (trialSheet.size()==5&& given == goal ) {
					return trialSheet;
				}
			}
		}
		
		else if (given<100&&goal>500) {
			for (int i =0;i<5;i++) {
				choices[i]= identifyChoices(5,i);
				scoreData[] desc = new scoreData[3];
				for(int j =0;j< desc.length;j++) {
					desc[j]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, choices[i][j])), choices[i][j]) ;
				}
				
				int mIndex = playHelper(desc);
				
				trialSheet.add(desc[mIndex].move);
			//	System.out.println(trialSheet.toString());
				given = GameBoard.selectMove(given, desc[mIndex].move);		
				if (given == goal) {
					return trialSheet;
				}else if (trialSheet.size()==5&& given == goal ) {
					return trialSheet;
				}
			}
		}
		else if (given<500&&goal>500) {
			for (int i =0;i<5;i++) {
				
				choices[i]= identifyChoices(1,i);
				scoreData[] desc = new scoreData[3];
				for(int j =0;j< desc.length;j++) {
					desc[j]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, choices[i][j])), choices[i][j]) ;
				}
				
				int mIndex = playHelper(desc);
				
				trialSheet.add(desc[mIndex].move);
				
				given = GameBoard.selectMove(given, desc[mIndex].move);		
			//	System.out.println(trialSheet.toString());
				if (given == goal) {
					return trialSheet;
				}else if (trialSheet.size()==5&& given == goal ) {
					return trialSheet;
				}
			}
		}
		
		else if (given>500&&goal<500) {
			for (int i =0;i<5;i++) {
				choices[i]= identifyChoices(2,i);
				scoreData[] desc = new scoreData[3];
				for(int j =0;j< desc.length;j++) {
					desc[j]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, choices[i][j])), choices[i][j]) ;
				}
				
				int mIndex = playHelper(desc);
				
				trialSheet.add(desc[mIndex].move);
			//	System.out.println(trialSheet.toString());
				given = GameBoard.selectMove(given, desc[mIndex].move);		
				if (given == goal) {
					return trialSheet;
				}else if (trialSheet.size()==5&& given == goal ) {
					return trialSheet;
				}
			}
		}
		
		else if (given>500&&goal>500||given<500&&goal<500) {
			for (int i =0;i<5;i++) {
				choices[i]= identifyChoices(3,i);
				scoreData[] desc = new scoreData[3];
				for(int j =0;j< desc.length;j++) {
					desc[j]= new scoreData(Math.abs(goal- GameBoard.selectMove(given, choices[i][j])), choices[i][j]) ;
				}
				
				int mIndex = playHelper(desc);
				
				trialSheet.add(desc[mIndex].move);
			//	System.out.println(trialSheet.toString());
				given = GameBoard.selectMove(given, desc[mIndex].move);		
				if (given == goal) {
					return trialSheet;
				}else if (trialSheet.size()==5&& given == goal ) {
					return trialSheet;
				}
			}
		}
		long endTime = System.nanoTime();
		 duration = (endTime - startTime);
		
		
		}else {
			return earlyPlay(given, goal);
		}
		return null;

	}

	/**
	 * Loops through the scoreData array to find the best move
	 * @param desc- ScoreData Array
	 * @return
	 */
	private static int playHelper(scoreData[] desc) {
		int mIndex= desc.length;
		int min= Integer.MAX_VALUE;
		for (int j=0;j<desc.length;j++) {
			int cur = desc[j].diff;
			if (cur<min) {
				min = cur;
				mIndex = j;
				}
		}
		
		return mIndex;
	}
	/**
	 * Calls Identify choices helper based on case
	 * @param caseNum - number of the case
	 * @param trialNum - what index of the move the player is on
	 * @return
	 */
	private static String[] identifyChoices (int caseNum, int trialNum) {
	switch (caseNum) {
	case 1: return identifyChoicesHelper(under500over500[trialNum]);
	case 2: return identifyChoicesHelper(over500under500[trialNum]);
	case 3: return identifyChoicesHelper(underORover[trialNum]);
	case 4: return identifyChoicesHelper(over500under100[trialNum]);
	case 5: return identifyChoicesHelper(under100over500[trialNum]);
	}
		
		return null;
	}
	/**
	 * Runs through 1D array to find top 3 choices
	 * Then return the move that should be executed
	 * @param arr
	 * @return
	 */
	private static String[] identifyChoicesHelper (int[] arr) {
		String [] choices = new String[3];
		int index1=0;
		int index2= 0;
		int index3 =0;
		
		int max1 = 0;
		int max2 =0;
		int max3 = 0;
		for (int i =0;i<arr.length;i++) {
			int cur = arr[i];
			if (cur>max1) {
				max1 = cur;
				index1 = i;
			}
		}
		for (int i =0;i<arr.length;i++) {
			int cur = arr[i];
			if (cur>max2) {
				if (cur == max1) {
					continue;
				}else {
				max2 = cur;
				index2 = i;}
			}
		}
		for (int i =0;i<arr.length;i++) {
			int cur = arr[i];
			if (cur>max3) {
				if (cur == max2|| cur == max1) {
					continue;
				}else {
				max3 = cur;
				index3 = i;}
			}
		}
		int[] indexes = {index1,index2,index3};
			for (int i =0;i<indexes.length;i++) {
				switch(indexes[i]) {
				case 0: choices[i] = ("+1"); break;
				case 1: choices[i] = ("-1"); break;
				case 2: choices[i] = ("+2"); break;
				case 3: choices[i] = ("-2"); break;
				case 4: choices[i] = ("+5"); break;
				case 5: choices[i] = ("-5"); break;
				case 6: choices[i] = ("><"); break;
				case 7: choices[i] = ("+10"); break;
				case 8: choices[i] = ("-10"); break;
				case 9: choices[i] = ("+50"); break;
				case 10: choices[i] = ("-50"); break;
				case 11: choices[i] = ("*2"); break;
				case 12: choices[i] = ("/2"); break;
				case 13: choices[i] = ("+100"); break;
				case 14: choices[i] = ("-100"); break;
				case 15: choices[i] = ("x^2"); break;
				case 16: choices[i] = ("-x"); break;
				case 17: choices[i] = ("sqrt"); break;
				case 18: choices[i] = ("*10"); break;
				case 19: choices[i] = ("/10"); break;
				}
			}
		return choices;
	}
	/**
	 * Updates the 3D array based on what the player chooses
	 * This builds the log data
	 * @param caseNum
	 * @param trialNum
	 * @param moveType
	 */
    static void ArrayUpdate (int caseNum, int trialNum, String moveType ) {
    	int moveIndex= 0;
    	switch (moveType) {
    	case "+1": moveIndex = 0; break;
		case "-1": moveIndex = 1; break;
		case "+2": moveIndex = 2; break;
		case "-2": moveIndex = 3; break;
		case "+5": moveIndex = 4; break;
		case "-5": moveIndex = 5; break;
		case "><": moveIndex = 6; break;
		case "+10": moveIndex = 7; break;
		case "-10": moveIndex = 8; break; 
		case "+50": moveIndex = 9; break; 
		case "-50": moveIndex = 10; break;
		case "*2": moveIndex = 11; break;
		case "/2": moveIndex = 12; break;
		case "+100": moveIndex = 13; break;
		case "-100": moveIndex = 14; break;
		case "x^2": moveIndex = 15; break;
		case "-x": moveIndex = 16; break;
		case "sqrt": moveIndex = 17; break;
		case "*10": moveIndex = 18; break;
		case "/10": moveIndex = 19; break;
    	}
    	switch (caseNum) {
    	case 1: under500over500[trialNum][moveIndex]++;
    	case 2: over500under500[trialNum][moveIndex]++;
    	case 3: underORover[trialNum][moveIndex]++;
    	case 4: over500under100[trialNum][moveIndex]++;
    	case 5: under100over500[trialNum][moveIndex]++;
    	}
    }
    /**
     * Writes 3D array to file
     * @throws FileNotFoundException
     */
   private static void writetoFile() throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(file);
   
    for (int i =0;i<5;i++) {
		for (int j = 0;j<5;j++) {
			for (int k =0;k<20;k++) {
				writer.print(fiveArrays[i][j][k]+ " ");	
			}
			writer.println();
		}
		writer.println();
	}  
	 writer.close();
    	
    }
}
/**
 * Custom Class called scoreData that holds a int and a String
 * @author Tanvir
 *
 */
class scoreData{
	int diff;
	String move;
	scoreData(int diff, String move){
		this.diff = diff;
		this.move = move;
	}
}