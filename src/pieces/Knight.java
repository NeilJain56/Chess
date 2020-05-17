package pieces;
import chess.Board;

/**
 * Creates a Knight piece and has rules that define the way it can move
 * @author nj243
 * @author rp930
 * @version 1.0
 */
public class Knight extends Piece{
	
	
	/**
	 * Constructor that initializes a Knight piece 
	 * @param team what team the piece is on
	 */
	public Knight(boolean team) {
		super(team, "Knight");
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
	 * Return if a certain move is possible just based on position, no other factors
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean moveCheck(int x1, int y1, int x2, int y2) {
		
		if(x1 == x2+2 || x1 == x2-2) {
			if(y1 == y2+1 || y1 == y2-1) {
				return true;
			}
		}
		if(y1 == y2+2 || y1 == y2-2) {
			if(x1 == x2+1 || x1 == x2-1) {
				return true;
			}
		}
		
		
		return false;
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
		if(moveCheck(x1,y1,x2,y2)) {
			if(b.isOccupied(x2, y2)) {
				if(!b.returnColor(x2, y2) == team) {
					return true;
				}
				else {
					return false;
				}
			}
			return true;
	}
		return false;
	}
}
