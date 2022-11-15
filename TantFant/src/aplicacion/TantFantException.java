package aplicacion;

public class TantFantException extends Exception{
	
	public static final String INVALID_SIZE = "The size of the board is not greater than 2";
	public static final String INVALID_MOVEMENT = "There is another Piece in that position o there is not a Piece";
	public static final String OFF_THE_BOARD = "Off of the board";
	public static final String INVALID_PLAYER = "It's not your turn";
	
	public TantFantException(String message) {
		super(message);
	}
}
