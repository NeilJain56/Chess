package chess;
import pieces.*;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class creates a chess board and determines check's and checkmates on the board as well as making the moves if valid
 * @author nj243
 * @author rp930
 * @version 1.0
 */
public class Board {
	boolean teamInCheck = false;
	public Piece[][] board = new Piece[8][8];
	HashSet<Integer> attackingPieces = new HashSet<>();
	
	/**
	 * Constructor to create and initialize a chess board
	 */
	public Board() {
		King white_king = new King(true);
		King black_king = new King(false);
		
		Queen white_queen = new Queen(true);
		Queen black_queen = new Queen(false);
		
		Knight white_knight1 = new Knight(true);
		Knight white_knight2 = new Knight(true);
		Knight black_knight1 = new Knight(false);
		Knight black_knight2 = new Knight(false);
		
		Rook white_rook1 = new Rook(true);
		Rook white_rook2 = new Rook(true);
		Rook black_rook1 = new Rook(false);
		Rook black_rook2 = new Rook(false);
		
		Bishop white_bishop1 = new Bishop(true);
		Bishop white_bishop2 = new Bishop(true);
		Bishop black_bishop1 = new Bishop(false);
		Bishop black_bishop2 = new Bishop(false);
		
		Pawn white_pawn1 = new Pawn(true);
		Pawn white_pawn2 = new Pawn(true);
		Pawn white_pawn3 = new Pawn(true);
		Pawn white_pawn4 = new Pawn(true);
		Pawn white_pawn5 = new Pawn(true);
		Pawn white_pawn6 = new Pawn(true);
		Pawn white_pawn7 = new Pawn(true);
		Pawn white_pawn8 = new Pawn(true);
		
		Pawn black_pawn1 = new Pawn(false);
		Pawn black_pawn2 = new Pawn(false);
		Pawn black_pawn3 = new Pawn(false);
		Pawn black_pawn4 = new Pawn(false);
		Pawn black_pawn5 = new Pawn(false);
		Pawn black_pawn6 = new Pawn(false);
		Pawn black_pawn7 = new Pawn(false);
		Pawn black_pawn8 = new Pawn(false);
		
		
		
		board[0][0] =  black_rook1;
		board[0][1] =  black_knight1;
		board[0][2] =  black_bishop1;
		board[0][3] =  black_queen;
		board[0][4] =  black_king;
		board[0][5] =  black_bishop2;
		board[0][6] =  black_knight2;
		board[0][7] =  black_rook2;
		
		board[1][0] = black_pawn1;
		board[1][1] = black_pawn2;
		board[1][2] = black_pawn3;
		board[1][3] = black_pawn4;
		board[1][4] = black_pawn5;
		board[1][5] = black_pawn6;
		board[1][6] = black_pawn7;
		board[1][7] = black_pawn8;
		
		board[7][0] =  white_rook1;
		board[7][1] =  white_knight1;
		board[7][2] =  white_bishop1;
		board[7][3] =  white_queen;
		board[7][4] =  white_king;
		board[7][5] =  white_bishop2;
		board[7][6] =  white_knight2;
		board[7][7] =  white_rook2;
		
		board[6][0] = white_pawn1;
		board[6][1] = white_pawn2;
		board[6][2] = white_pawn3;
		board[6][3] = white_pawn4;
		board[6][4] = white_pawn5;
		board[6][5] = white_pawn6;
		board[6][6] = white_pawn7;
		board[6][7] = white_pawn8;
		
		
		
	}
	
	/**
	 * Constructor with parameter used to create a board given a board
	 * @param board instance of a board
	 */
	public Board(Piece[][] board){
		this.board = board;
	}
	
	/**
	 * Returns if a coordinate of the board is occupied by a piece object 
	 * @param x coordinate of piece
	 * @param y coordinate of piece
	 * @return boolean
	 */
	public boolean isOccupied(int x, int y) {
		return this.board[x][y] != null;
	}
	
	/**
	 * Returns the team of a piece given the coordinates
	 * @param x coordinate of piece
	 * @param y coordinate of piece
	 * @return boolean
	 */
	public boolean returnColor(int x, int y) {
		return this.board[x][y].what_team();

	}
	
	/**
	 * if a pawn reaches the end of the board on either side (for its respective team) it changes the pawn into
	 * a promotion piece (queen if no input is given, otherwise the specific piece asked for)
	 * returns false, if that move cannot be made, true if it can be
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @param piece which piece
	 * @return boolean
	 */
	public boolean promotion(int x1, int y1, int x2, int y2, String piece) {
		try{
			if(this.board[x1][y1].isValidMove(x1,y1,x2,y2, this.board[x1][y1].what_team(), this) && this.board[x1][y1] instanceof Pawn) { // if valid move, then check if it gets you in check
				if (checkForCheck(x1,y1,x2,y2)) {
					
					return false;
				}

				if (this.board[x1][y1].what_team()){
					if (piece.equals("N")) this.board[x2][y2] = new Knight(true);
					else if (piece.equals("Q") || piece.equals("")) this.board[x2][y2] = new Queen(true);
					else if (piece.equals("B")) this.board[x2][y2] = new Bishop(true);
					else if (piece.equals("R")) this.board[x2][y2] = new Rook(true);
				} else {
					if (piece.equals("N")) this.board[x2][y2] = new Knight(false);
					else if (piece.equals("Q") || piece.equals("")) this.board[x2][y2] = new Queen(false);
					else if (piece.equals("B")) this.board[x2][y2] = new Bishop(false);
					else if (piece.equals("R")) this.board[x2][y2] = new Rook(false);
				}

				this.board[x1][y1] = null;

				if (otherTeamInCheck(x2,y2)){
					printBoard();
					System.out.println("Check");
					teamInCheck = true;
				} else {
					printBoard();
					teamInCheck = false;
				}
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException e){
			return false;
		}

	}
	/**
	 * makes sure it can do a castling move if needed and checks if other team is in check(mate)
	 * returns true if the move can be made, false if it cannot
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean makeMove(int x1, int y1, int x2, int y2) {
		//want to check for 'check' here for other team and own team

		try{
			if (this.board[x1][y1].what_team() && this.board[x1][y1] instanceof Pawn){
				//if white team pawn reaches other side then if reaches end, send to promotion
				if (x2 == 0){
					return promotion(x1, y1, x2, y2, "");
				}

			} else if (!this.board[x1][y1].what_team() && this.board[x1][y1] instanceof Pawn){
				//if black team pawn reaches other side then if reaches end, send to promotion
				if (x2 == 7){
					return promotion(x1, y1, x2, y2, "");
				}
			}

			if(this.board[x1][y1].isValidMove(x1,y1,x2,y2, this.board[x1][y1].what_team(), this)) { // if valid move, then check if it gets you in check
				if (checkForCheck(x1,y1,x2,y2)) {
					return false;
				}

				//if instance of king, and its a castle, make sure pieces haven't moved before, king can't be in check
				//check to see if you're in check rn, make sure rook hasn't moved
				if (this.board[x1][y1] instanceof King){
					if (x1 == 7 && x2 == 7 && y1 == 4 && y2 == 2){
						if (!teamInCheck){
							if (checkForCheck(x1,y1,7,3)) {
								return false;
							}

							if (this.board[7][0].isHasMoved()) return false;
							//make sure rook hasn't moved
							this.board[x2][y2] = this.board[x1][y1];
							this.board[x2][y2+1] = this.board[7][0];
							this.board[x1][y1] = null;
							this.board[7][0] = null;
							printBoard();
							return true;
						} else {
							return false;
						}
					} else if(x1 == 7 && x2 == 7 && y1 == 4 && y2 == 6){
						if (!teamInCheck){
							if (checkForCheck(x1,y1,7,5)) {
								return false;
							}
							if (this.board[7][7].isHasMoved()) return false;
							this.board[x2][y2] = this.board[x1][y1];
							this.board[x2][y2-1] = this.board[7][7];
							this.board[x1][y1] = null;
							this.board[7][7] = null;
							printBoard();
							return true;
						} else {
							return false;
						}
					} else if(x1 == 0 && x2 == 0 && y1 == 4 && y2 == 6){
						if (!teamInCheck){
							if (checkForCheck(x1,y1,0,5)) {
								return false;
							}
							if (this.board[0][7].isHasMoved()) return false;
							this.board[x2][y2] = this.board[x1][y1];
							this.board[x2][y2-1] = this.board[0][7];
							this.board[x1][y1] = null;
							this.board[0][7] = null;
							printBoard();
							return true;
						} else {
							return false;
						}
					} else if (x1 == 0 && x2 == 0 && y1 == 4 && y2 == 2){
						if (!teamInCheck){
							//make sure rook hasn't moved
							if (checkForCheck(x1,y1,0,3)) {
								return false;
							}
							this.board[x2][y2] = this.board[x1][y1];
							this.board[x2][y2+1] = this.board[0][0];
							this.board[x1][y1] = null;
							this.board[0][0] = null;
							printBoard();
							return true;
						} else {
							return false;
						}
					}
				}

				this.board[x2][y2] = this.board[x1][y1];
				this.board[x1][y1] = null;

				if (otherTeamInCheck(x2,y2)){
					if (checkmate(this.board[x2][y2].what_team())) {
						this.printBoard();
						System.out.println("Checkmate");
						
						if (this.board[x2][y2].what_team()){
							
							System.out.println("White Wins");
							
						} else {
							
							System.out.println("Black Wins");
							
						}
						System.exit(0);
						return true;
					} else {
						for (Integer piece: attackingPieces) attackingPieces.remove(piece);
						printBoard();
						System.out.println("Check");
						teamInCheck = true;
					}
				} else {
					printBoard();
					teamInCheck = false;
				}
				return true;
			}
			else {
				return false;
			}
		} catch (NullPointerException e){
			return false;
		}

	}
	/**
	 * checks to see if your team becomes in check by doing a move
	 * returns true if the move puts your own team in check(so cannot do the move) false if it does not put you
	 * in check, so you can do the move
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean checkForCheck(int x1, int y1, int x2, int y2) { //check method helper for making a move
		//check if your team is in check first, because it's an invalid move if you do that
		Piece[][] temp = new Piece[8][8];
		//initialize temp (can't do temp = board because of pass by reference)
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				temp[i][j] = board[i][j];
			}
		}
		boolean team = board[x1][y1].what_team();
		temp[x2][y2] = temp[x1][y1];
		temp[x1][y1] = null;


		int KingRow = 0;
		int KingCol = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (temp[i][j] == null) continue;
				if (temp[i][j].what_team() == team && temp[i][j].what_piece().equals("King")) {
					KingRow = i;
					KingCol = j;
				}
			}
		}


		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (temp[i][j] == null) continue;
				if (temp[i][j].what_team() == !team) {
					Piece curr = temp[i][j];
					if (curr.isValidMove(i, j, KingRow, KingCol, !team, new Board(temp))) {
						return true;
					}
				}

			}
		}

		return false;
	}


	/**
	 * checks to see if the king can move into a spot where its not in check or a piece of the team can take the
	 * attacking piece or a piece could move in the way of the attacking piece
	 * @param team what team
	 * @return boolean
	 */
	public boolean checkmate(boolean team){ //team is the team that put the other team in check (need to check if !team can move anywhere)
		//first test if king can move anywhere
		int KingRow = 0;
		int KingCol = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) continue;
				if (board[i][j].what_team() == !team && board[i][j].what_piece().equals("King")) {
					KingRow = i;
					KingCol = j;
				}
			}
		}
		if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow+1,KingCol,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow+1, KingCol)) return false;
		} else if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow+1,KingCol+1,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow+1, KingCol+1)) return false;
		} else if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow+1,KingCol-1,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow+1, KingCol-1)) return false;
		} else if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow-1,KingCol,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow-1, KingCol)) return false;
		} else if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow-1,KingCol+1,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow-1, KingCol+1)) return false;
		} else if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow-1,KingCol-1,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow-1, KingCol-1)) return false;
		} else if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow,KingCol+1,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow, KingCol+1)) return false;
		} else if (this.board[KingRow][KingCol].isValidMove(KingRow,KingCol,KingRow,KingCol-1,!team,this)){
			if (!checkForCheck(KingRow, KingCol, KingRow, KingCol-1)) return false;
		}

		//next test if your pieces can take attacking pieces
		for (Integer r: attackingPieces){
			int row = r/8;
			int col = r%8;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j] == null) continue;
					if (board[i][j].what_team() == !team) {
						Piece curr = board[i][j];
						if (curr.isValidMove(i, j, row, col, !team, this)) {
							if (!checkForCheck(i,j,row,col)){
								return false;
							}
						}
					}
				}
			}
		}

		//lastly test if your pieces can move in spots in the line to the king
		for (Integer r: attackingPieces){
			int row = r/8;
			int col = r%8;

			ArrayList<Integer> path = new ArrayList<>();
			if (row == KingRow){
				//vertical path
				if (col < KingCol){
					Integer temp = col+1;
					while (temp != KingCol){
						path.add((row*8)+temp);
						temp++;
					}
				} else {
					Integer temp = col-1;
					while (temp != KingCol){
						path.add((row*8)+temp);
						temp--;
					}
				}
			} else if (col == KingCol){
				//horizontal path
				if (row < KingRow){
					Integer temp = row+1;
					while (temp != KingRow){
						path.add((temp*8)+col);
						temp++;
					}
				} else {
					Integer temp = row-1;
					while (temp != KingRow){
						path.add((temp*8)+col);
						temp--;
					}
				}
			} else{
				//diagonal
				if (row < KingRow){
					if (col < KingCol){
						Integer tempRow = row+1;
						Integer tempCol = col+1;
						while (tempRow!=KingRow && tempCol!=KingCol){
							path.add((tempRow*8)+tempCol);
							tempRow++;tempCol++;
						}
					} else { //col > KingCol
						Integer tempRow = row+1;
						Integer tempCol = col-1;
						while (tempRow!=KingRow && tempCol!=KingCol){
							path.add((tempRow*8)+tempCol);
							tempRow++;tempCol--;
						}
					}
				} else { // row>KingRow
					if (col < KingCol){
						Integer tempRow = row-1;
						Integer tempCol = col+1;
						while (tempRow!=KingRow && tempCol!=KingCol){
							path.add((tempRow*8)+tempCol);
							tempRow--;tempCol++;
						}
					} else {//col > KingCol
						Integer tempRow = row-1;
						Integer tempCol = col-1;
						while (tempRow!=KingRow && tempCol!=KingCol){
							path.add((tempRow*8)+tempCol);
							tempRow--;tempCol--;
						}
					}
				}
			}

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j] == null) continue;
					if (board[i][j].what_team() == !team) {
						Piece curr = board[i][j];
						for (Integer square: path){
							int squareR = square/8;
							int squareCol = square % 8;
							if (curr.isValidMove(i, j, squareR, squareCol, !team, this)) {
								if (!checkForCheck(i,j,squareR,squareCol)){
									return false;
								}
							}
						}
						if (curr.isValidMove(i, j, row, col, !team, this)) {
							if (!checkForCheck(i,j,row,col)){
								return false;
							}
						}
					}
				}
			}
		}


		return true;
	}
	
	/**
	 * check if the move is made, if the move puts the other team in check
	 * returns true if it does put the other team in check, false if it does not
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean otherTeamInCheck(int x2, int y2){
		//check if other person is in check
		boolean team = board[x2][y2].what_team();
		int KingRow = 0;
		int KingCol = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) continue;
				if (board[i][j].what_team() == !team && board[i][j].what_piece().equals("King")){
					KingRow = i;
					KingCol = j;
				}
			}
		}

		boolean check = false;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) continue;
				if (board[i][j].what_team() == team) {
					Piece curr = board[i][j];
					if (curr.isValidMove(i, j, KingRow, KingCol, team, new Board(board))) {
						attackingPieces.add((i*8)+j);
						check = true;
					}
				}

			}
		}
		return check;
	}
	
	
	/**
	 * Prints board
	 */
	public void printBoard() {
		System.out.println();
		boolean blackSpot = false;
		for(int i =0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				String team;
				
				if(this.board[i][j] == null) {
					if(blackSpot) {
						System.out.print("## ");
					}
					else {
						System.out.print("   ");
					}
					blackSpot = !blackSpot;
				}
				else {
					
					if (this.board[i][j].what_team()) {
						team = "w";
					}
					else {
						team = "b";
					}	
					
					
				if(this.board[i][j].what_piece().equals("King")) {
					System.out.print(team + "K" + " ");
				}
				else if(this.board[i][j].what_piece().equals("Queen")) {
					System.out.print(team + "Q" + " ");
				}
				else if(this.board[i][j].what_piece().equals("Rook")) {
					System.out.print(team + "R" + " ");
				}
				else if(this.board[i][j].what_piece().equals("Knight")) {
					System.out.print(team + "N" + " ");
				}
				else if(this.board[i][j].what_piece().equals("Bishop")) {
					System.out.print(team + "B" + " ");
				}
				else if(this.board[i][j].what_piece().equals("Pawn")) {
					System.out.print(team + "p" + " ");
				}
				
				blackSpot = !blackSpot;
				}
			}
				
			System.out.println(8-i);
			blackSpot = !blackSpot;
			
		}
		System.out.println(" a  b  c  d  e  f  g  h ");
		System.out.println();
	}
}
