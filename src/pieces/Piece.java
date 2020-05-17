package pieces;

import chess.Board;

/**
 * Creates a Piece type object, acts as a super class to many pieces
 * @author nj243
 * @author rp930
 * @version 1.0
 */
public class Piece {
	boolean team;
	String type;
	
	/**
	 * Constructor to make a Piece of a certain type
	 * @param team what team the piece is on
	 * @param type what type of piece
	 */
	public Piece(boolean team, String type) {
		this.team = team;
		this.type = type;
	}
	/**
	 * Returns what team the piece is on
	 * @return boolean
	 */
	public boolean what_team() {
		return this.team;
	}
	/**
	 * Returns what piece type
	 * @return String 
	 */
	public String what_piece() {
		return this.type;
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
		return false;
	}
	/**
	 * Return if a piece has moved
	 * @return boolean
	 */
	public boolean isHasMoved() {
		return false;
	}
	/**
	 * Set empassant variable
	 * @param truth if allowed for empassant
	 */
	public void set_empassant(boolean truth) {
		
	}
}
