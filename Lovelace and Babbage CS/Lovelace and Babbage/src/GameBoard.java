/**
 * The game board class holds a 2D array board. When a player selects a move, 
 * one of 20 methods is applied to the given number and the result is returned.
 * @author Roxy
 *
 */
public class GameBoard {
	String [][] gameBoard = {
							{"-1+","-2+","-5+"},
							{"><","-10+","-50+"},
							{"×2÷","-100+","x^2"},
							{"-x","√","×10÷"}
							};
	/**
	 * This method is accessed by players to see the result of a 
	 * move. Selecting "+1" would return you your number +1;
	 * @param num - takes in given number
	 * @param move - takes in the name of the move, there are 20 moves
	 * @return - returns the num, with the specified operation applied. 
	 */
	public static int selectMove(int num, String move ) {
		switch(move) {
		case ("+1"): return plus1(num);
		case ("-1"): return minus1(num);
		case ("+2"): return plus2(num);
		case ("-2"): return minus2(num);
		case ("+5"): return plus5(num);
		case ("-5"): return minus5(num);
		case ("><"): return switchNum(num);
		case ("+10"): return plus10(num);
		case ("-10"): return minus10(num);
		case ("+50"): return plus50(num);
		case ("-50"): return minus50(num);
		case ("*2"): return times2(num);
		case ("/2"): return divide2(num);
		case ("+100"): return plus100(num);
		case ("-100"): return minus100(num);
		case ("x^2"): return squared(num);
		case ("-x"): return negative(num);
		case ("sqrt"): return sqrt(num);
		case ("*10"): return times10(num);
		case ("/10"): return divide10(num);
		default: return num;
		}
		
		
	}
	/**
	 * Self Explanatory Methods
	 * @param num - takes in a number
	 * @return - applies the operation and returns the number
	 */
	private static int plus1(int num) {
		return num+1;
	}
	private static int minus1(int num) {
		return num-1;
	}
	private static int plus2(int num) {
		return num+2;
	}
	private static int minus2(int num) {
		return num-2;
	}
	private static int plus5(int num) {
		return num+5;
	}
	private static int minus5(int num) {
		return num-5;
	}
	private static int switchNum(int num) {
		int reversed = 0;
		while(num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
		return reversed;
	}
	private static int plus10(int num) {
		return num+10;
	}
	private static int minus10(int num) {
		return num-10;
	}
	private static int plus50(int num) {
		return num+50;
	}
	private static int minus50(int num) {
		return num-50;
	}
	private static int times2(int num) {
		return num*2;
	}
	private static int divide2(int num) {
		return num/2;
	}
	private static int plus100(int num) {
		return num+100;
	}
	private static int minus100(int num) {
		return num-100;
	}
	private static int squared(int num) {
		return num*num;
	}
	private static int negative(int num) {
		return num*-1;
	}
	private static int sqrt(int num) {
		return (int)(Math.sqrt(num));
	}
	private static int times10(int num) {
		return num*10;
	}
	private static int divide10(int num) {
		return num/10;
	}
	
	public String toString() {
	return gameBoard[0][0];
	}
}

