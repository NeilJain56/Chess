package pieces;
import chess.Board;

/**
 * Creates a King piece and has rules that define the way it can move
 * @author nj243
 * @author rp930
 * @version 1.0
 */
public class King extends Piece{
	public boolean hasMoved = false;
	
	/**
	 * Constructor that initializes a King piece 
	 * @param team what team the piece is on
	 */
	public King(boolean team) {
		super(team, "King");
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
	 * Returns if a move is a possiblity for a king given two points
	 * @param x1 first x-coordinate
	 * @param y1 first y-coordinate
	 * @param x2 second x-coordinate
	 * @param y2 second y-coordinate
	 * @return boolean
	 */
	public boolean possibleMoves(int x1, int y1, int x2, int y2) {
		
		if(x1 == x2+1 || x1 == x2-1) {
			if(y1 == y2 || y1 == y2+1 || y1 == y2-1) {
				return true;
			}
		}
		else if(x1==x2 && (y1==y2+1 || y1 == y2-1)) {
			return true;
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

		if(x1 == 7 && x2 == 7 && y1 == 4 && y2 == 6){
			if (team){
				if ((!b.isOccupied(7,5) && !b.isOccupied(7,6)) && b.board[7][7] instanceof Rook){
					if (!hasMoved){
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (x1 == 7 && x2 == 7 && y1 == 4 && y2 == 2){
			if (team){
				if ((!b.isOccupied(7,3) && !b.isOccupied(7,2)&& !b.isOccupied(7,1)) && b.board[7][0] instanceof Rook){
					if (!hasMoved){
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		if(x1 == 0 && x2 == 0 && y1 == 4 && y2 == 6){
			if (!team){
				if ((!b.isOccupied(0,5) && !b.isOccupied(0,6)) && b.board[0][7] instanceof Rook){
					if (!hasMoved){
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (x1 == 0 && x2 == 0 && y1 == 4 && y2 == 2){
			if (!team){
				if ((!b.isOccupied(0,3) && !b.isOccupied(0,2)&& !b.isOccupied(0,1)) && b.board[0][0] instanceof Rook){
					if (!hasMoved){
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		
		
		if(possibleMoves(x1,y1,x2,y2)) {
			if(b.isOccupied(x2, y2)) {
				if(!b.returnColor(x2, y2) == team) {
					this.hasMoved = true;
					return true;
				}
				else {
					return false;
				}
			}
			else {
				this.hasMoved = true;
				return true;
			}
		}
		
		return false;	
		
		
		
	}
	

}
