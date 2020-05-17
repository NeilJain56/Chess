package pieces;
import chess.*;

/**
 * Creates a Pawn piece and has rules that define the way it can move
 * @author nj243
 * @author rp930
 * @version 1.0
 */
public class Pawn extends Piece {

	boolean enpassant = false;
	
	/**
	 * Constructor that initializes a Pawn piece 
	 * @param team what team the piece is on
	 */
	public Pawn(boolean team) {
		super(team, "Pawn");
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
	 * Set the state of empassant
	 * @param truth if empassant is possible
	 */
	public void set_empassant(boolean truth) {
		this.enpassant = truth;
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
		if(b.isOccupied(x2, y2) && y1 == y2) {
			return false;
		}
		if(!team) {
			if(x1+1 == x2 && y1 == y2 && !b.isOccupied(x2, y2)) {
				return true;
			}
			else if(x1+2 == x2 && y1 == y2 && !b.isOccupied(x2, y2) && x1 == 1) {
				if(y2-1 > -1) {
				if(b.isOccupied(x2, y2-1) && b.board[x2][y2-1].what_piece().equals("Pawn") && !b.returnColor(x2, y2-1) == team ) {
					b.board[x2][y2-1].set_empassant(true);
				}
				}
				if(y2+1 < 8) {
				if(b.isOccupied(x2, y2+1) && b.board[x2][y2+1].what_piece().equals("Pawn") && !b.returnColor(x2, y2+1) == team ) {
					b.board[x2][y2+1].set_empassant(true);
				}
				}
				return true;
			}
			//en passant conditions
			else if(enpassant && x1+1 == x2 && (y1-1 == y2 || y1+1 == y2) ) {
				b.board[x2-1][y2] = null;
				return true;
			}
			
			else if(b.isOccupied(x2, y2) && x1+1 == x2 && (y1-1 == y2 || y1+1 == y2) && !b.returnColor(x2, y2) == team ) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(x1-1 == x2 && y1 == y2 && !b.isOccupied(x2, y2) ) {
				return true;
			}
			else if(x1-2 == x2 && y1 == y2 && !b.isOccupied(x2, y2) && x1 == 6) {
				if(y2-1 >-1) {
				if(b.isOccupied(x2, y2-1) && b.board[x2][y2-1].what_piece().equals("Pawn") && !b.returnColor(x2, y2-1) == team ) {
					b.board[x2][y2-1].set_empassant(true);
				}
				}
				if(y2+1 < 8) {
				if(b.isOccupied(x2, y2+1) && b.board[x2][y2+1].what_piece().equals("Pawn") && !b.returnColor(x2, y2+1) == team ) {
					b.board[x2][y2+1].set_empassant(true);
				}
				}
				return true;
			}
			
			else if(enpassant && x1-1 == x2 && (y1-1 == y2 || y1+1 == y2) ) {
				b.board[x2+1][y2] = null;
				return true;
			}
			
			
			else if(b.isOccupied(x2, y2) && x1-1 == x2 && (y1-1 == y2 || y1+1 == y2) && !b.returnColor(x2, y2) == team ) {
				return true;
			}
			
			else {
				return false;
			}
		}
		
	}
}
