package chess;

import pieces.*;
import java.io.*;
import java.util.*;

/**
 * This is the main class which makes all needed method calls for chess. 
 * @author nj243
 * @author rp903
 * @version 1.0
 */
public class chessMain {

	/**
	 * This makes the a chess board and runs a game on it given input from the user.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String [] args) throws FileNotFoundException {
		Board board = new Board();
		
		board.printBoard();
		Scanner keyboard = new Scanner(System.in);
		boolean team = true;
		boolean draw = false;

		while(true) {

			String[] input;
			if(team) {
				System.out.print("White's move: ");
				
				input = keyboard.nextLine().split(" ");
				
			}
			else {
				System.out.print("Black's move: ");
				
				input = keyboard.nextLine().split(" ");
			
			}
			if(input.length == 1 && input[0].equals("resign")) {
				if(team) {
					System.out.println("Black Wins");
					
					break;
				}
				else {
					System.out.println("White Wins");
					
					break;
				}
			}
			if(input.length == 1 && input[0].equals("draw") && draw) {
				System.out.println("draw");
				break;
			}
			if(draw) {
				draw = false;
			}
			if(input.length == 2){
				int[] first = fileRankToInt(input[0]);
				int[] second = fileRankToInt(input[1]);
				if(board.isOccupied(first[0], first[1]) && board.returnColor(first[0], first[1]) == team) {
					if(board.makeMove(first[0],first[1], second[0], second[1])) {
						for(int i = 0; i < 8; i++) {
							for(int j = 0; j < 8; j++) {
								if(board.board[i][j] != null && board.board[i][j].what_team() == team && board.board[i][j].what_piece().equals("Pawn")) {
									board.board[i][j].set_empassant(false);
								}
							}
						}

						team = !team;
					} else {
						System.out.println("Illegal move, try again");
						
					}

				}
				else {
					System.out.println("Illegal move, try again");
					
				}
			}
			if(input.length == 3) {
				if(input[2].equals("draw?")) {
					int[] first = fileRankToInt(input[0]);
					int[] second = fileRankToInt(input[1]);
					if(board.isOccupied(first[0], first[1]) && board.returnColor(first[0], first[1]) == team) {
						if(board.makeMove(first[0],first[1], second[0], second[1])) {
							for(int i = 0; i < 8; i++) {
								for(int j = 0; j < 8; j++) {
									if(board.board[i][j] != null && board.board[i][j].what_team() == team && board.board[i][j].what_piece().equals("Pawn")) {
										board.board[i][j].set_empassant(false);
									}
								}
							}

							team = !team;
						} else {
							System.out.println("Illegal move, try again");
							
						}

					}
					else {
						System.out.println("Illegal move, try again");
						
					}
					draw = true;
				}
				else {
					String promotionPiece = input[2];
					
					int[] first = fileRankToInt(input[0]);
					int[] second = fileRankToInt(input[1]);
					if(board.isOccupied(first[0], first[1]) && board.returnColor(first[0], first[1]) == team) {
						if(board.promotion(first[0],first[1], second[0], second[1], promotionPiece)) {

							team = !team;
						}
						else {
							System.out.println("Illegal move, try again");
							
						}

					} else {
						System.out.println("Illegal move, try again");
						
					}
				}
			}

		}
	}
	
	/**
	 * This returns an int array with the coordinates corresponding to the user input of filerank
	 * @param filerank the string that states what part of the board is being referred to 
	 * @return int[] 
	 */
	public static int[] fileRankToInt(String filerank) {
		
		int x = 8 - Integer.parseInt(filerank.substring(1));
		int y;
		if(filerank.charAt(0) == 'a') y =0;
		else if(filerank.charAt(0) == 'b') y =1;
		else if(filerank.charAt(0) == 'c') y =2;
		else if(filerank.charAt(0) == 'd') y =3;
		else if(filerank.charAt(0) == 'e') y =4;
		else if(filerank.charAt(0) == 'f') y =5;
		else if(filerank.charAt(0) == 'g') y =6;
		else if(filerank.charAt(0) == 'h') y =7;
		else {
			y = -1;
		}
		int[] result = {x,y};		
		return result;
		
	}
	
	
	
	
}
