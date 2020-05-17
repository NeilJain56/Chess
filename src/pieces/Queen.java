package pieces;

import chess.Board;

/**
 * Creates a Queen piece and has rules that define the way it can move
 * @author nj243
 * @author rp930
 * @version 1.0
 */
public class Queen extends Piece{
	
	
	/**
	 * Constructor that initializes a Queen piece 
	 * @param team what team the piece is on
	 */
	public Queen(boolean team) {
		super(team, "Queen");
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
	 * Check if the coordinates are vertical to one another
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean verticalCheck(int x1, int y1, int x2, int y2) {
		if(y1 == y2 && x1 != x2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Check if the coordinates are horizontal to one another
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean horizontalCheck(int x1, int y1, int x2, int y2) {
		if(x1 == x2 && y1 != y2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Return if the horizontal path between two points has no pieces in between
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean horizontalClearPath(int x1, int y1, int x2, int y2, Board b) {
		boolean way = false;
		if(y1<y2) {
			way = true;
			y1++;
		}
		else {
			y1--;
		}
		while(y1 != y2) {
			if(b.isOccupied(x1,y1)) {
				return false;
			}
			if(way) {
				y1++;
			}
			else {
				y1--;
			}
		}
		return true;
		
		
	}
	/**
	 * Return if the vertical path between two points has no pieces in between
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean verticalClearPath(int x1, int y1, int x2, int y2, Board b) {
		boolean way = false;
		if(x1<x2) {
			way = true;
			x1++;
		}
		else {
			x1--;
		}
		while(x1 != x2) {
			if(b.isOccupied(x1,y2)) {
				return false;
			}
			if(way) {
				x1++;
			}
			else {
				x1--;
			}
		}
		return true;
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
if((verticalCheck(x1, y1, x2, y2) || horizontalCheck(x1,y1,x2, y2))  ) {
			
			if(verticalCheck(x1, y1, x2, y2)) {
				if(verticalClearPath(x1,y1,x2,y2,b)) {
					if(b.isOccupied(x2, y2)) {
						if(!b.returnColor(x2, y2) == team) {
					
				
					return true;
						}
						else {
							return false;
						}
					}
					else {
						
						return true;
					}
				}
			}
			if(horizontalCheck(x1,y1,x2,y2)) {
				if(horizontalClearPath(x1,y1,x2,y2,b)){
					
					if(b.isOccupied(x2, y2)) {
						if(!b.returnColor(x2, y2) == team) {
					
					
					return true;
						}
						else {
							return false;
						}
					}
					else {
						
						return true;
					}
				
				}
				}
			}
		
		return false;
	}

}
