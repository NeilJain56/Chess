package pieces;

import chess.Board;

/**
 * Creates a Bishop piece and has rules that define the way it can move
 * @author nj243
 * @author rp930
 * @version 1.0
 */
public class Bishop extends Piece{
	
	/**
	 * Constructor that initializes a Bishop piece 
	 * @param team what team the piece is on
	 */
	public Bishop(boolean team) {
		super(team, "Bishop");
	}
	
	/**
	 * Returns what piece type
	 * @return String 
	 */
	public String what_piece() {
		return super.what_piece();
	}
	/**
	 * Returns what team the piece is on
	 * @return boolean
	 */
	public boolean what_team() {
		return super.what_team();
	}
	
	/**
	 * Returns if a piece is diagonal from another given two coordinate pairs 
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean isDiagonal(int x1, int y1, int x2, int y2) {
		if(Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns if the diagonal path has no pieces between two coordinate pairs 
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @param b game board
	 * @return boolean
	 */
	public boolean clearDiagonalPath(int x1, int y1, int x2, int y2, Board b) {
		int p = 0;
		if(x1 < x2 && y1 < y2) {
			x1++;
			y1++;
			
			p = 1;
		}
		else if(x1 < x2 && y1 > y2) {
			x1++;
			y1--;
		
			p = 2;
		}
		else if (x1 > x2 && y1 < y2) {
			
			x1--;
			y1++;
			p = 3;
		}
		else {
			x1--;
			y1--;
			
			p = 4;
		}
		while(x1 != x2 && y1 != y2) {
			if(b.isOccupied(x1, y1)) return false;
			
			if(p == 1) {
			x1++;
			y1++;
			}
			else if(p == 2) {
				x1++;
				y1--;
			}
			else if(p ==3) {
				x1--;
				y1++;
			}
			else {
				x1--;
				y1--;
			}
			
		}
		return true;
	}
	
	/**
	 * Returns if a move is valid or not
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @param team what team the piece is on
	 * @param b game board
	 * @return boolean
	 */
	public boolean isValidMove(int x1, int y1, int x2, int y2, boolean team, Board b) {
		
		if(x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) {
			return false;
		}
		if(x1 > 7 || y1 > 7 || x2 >7 || y2 > 7) {
			return false;
		}
		if(isDiagonal(x1,y1,x2,y2) && clearDiagonalPath(x1,y1,x2,y2,b)) {
			
			if(b.isOccupied(x2, y2) && b.returnColor(x2, y2) == team) {
				return false;
			}
			return true;
		}
		return false;
		
	}

}
