package aplicacion;
/**
 * Tant Fant game.
 *
 * @author 
 * @version 1.0 (11/04/2022)
 */
public class TantFant{
	
	private int size;
	private char[][] board;
	private char player;
	private int numberMovements;
    /**
     * Create a new Tant Fant game.
     * @param n Game board size (nxn).
     * @throws TantFantException INVALID_SIZE, If the size of the board is not greater than 2.
     */
    public TantFant(int n) throws TantFantException{
    	if(n < 3) throw new TantFantException(TantFantException.INVALID_SIZE);
    	size = n;
    	board = new char[n][n];
    	player = 'A';
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			if(i == 0) board[i][j] = 'B';
    			if(i == n-1) board[i][j] = 'A';
	    		if(i != 0 && i != n-1) board[i][j] = ' ';
    		}
    	}
    }
    
    /**
     * Makes a move in the game.
     * @param initialRow Horizontal starting position.
     * @param initiaColumn Vertical starting position.
     * @param finalRow Horizontal end position.
     * @param finalColumn Vertical end position.
     * @throws TantFantException INVALID_MOVEMENT, If the move cannot be made. OFF_THE_BOARD, If the given position is not inside the board. INVALID_PLAYER, If it is the other player's turn. 
     * @return If the player wins the game.
     */
    public boolean play(int initialRow, int initiaColumn, int finalRow, int finalColumn) throws TantFantException{
    	if(initialRow < 0 || initialRow >= size || initiaColumn < 0 || initiaColumn >= size || finalRow < 0 || finalRow >= size || finalColumn < 0 || finalColumn >= size) 
    		throw new TantFantException(TantFantException.OFF_THE_BOARD);
    	if(board[finalRow][finalColumn] != ' ' || board[initialRow][initiaColumn] == ' ') throw new TantFantException(TantFantException.INVALID_MOVEMENT);
    	if(board[initialRow][initiaColumn] != player) throw new TantFantException(TantFantException.INVALID_PLAYER);
    	board[initialRow][initiaColumn] = ' ';
    	board[finalRow][finalColumn] = player;
    	player = player == 'A'? 'B' : 'A';
    	numberMovements++;
    	return true;
    }
    
   
    
    /**
     * @return Game board.
     */
    public char[][] board(){
        return board;
    }
    
    /**
     * @return Player who has the turn.
     */
    public char player(){
        return player;
    }
    
    /**
     * @return Number of moves of the player who has the turn.
     */
    public int plays(){
        return numberMovements;
    }
    
    public void restart() {
    	numberMovements = 0;
    	player = 'A';
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
    			if(i == 0) board[i][j] = 'B';
    			if(i == size-1) board[i][j] = 'A';
	    		if(i != 0 && i != size-1) board[i][j] = ' ';
    		}
    	}
    }
    
    
    public int getSize() {
    	return size;
    }
    
    public void setSize(int size) throws TantFantException{
    	if(size < 3) throw new TantFantException(TantFantException.INVALID_SIZE);
    	this.size = size;
    	board = new char[size][size];
    	restart();
    }
    
    public boolean winner() {
    	/*Verificamos las horizontales para las negras*/
    	int conteo = 0;
    	for(int k = 0; k < board.length; k++){
	    	for(int i = 0; i < board.length; i++) {
	    		for(int j = 0; j < board.length; j++) {
	    			if(i == k && board[i][j] == 'A' && i != board.length-1) {
	    				conteo++;
	    			}
	    		}
	    	}
	    	if(conteo == board.length) {
	    		return true;
	    	}else {
	    		conteo = 0;
	    	}
    	}
    	/*Verificamos las horizontales para las blancas*/
    	conteo = 0;
    	for(int k = 0; k < board.length; k++){
	    	for(int i = 0; i < board.length; i++) {
	    		for(int j = 0; j < board.length; j++) {
	    			if(i == k && board[i][j] == 'B' && i != 0) {
	    				conteo++;
	    			}
	    		}
	    	}
	    	if(conteo == board.length) {
	    		return true;
	    	}else {
	    		conteo = 0;
	    	}
    	}
    	
    	
    	/*Verificamos las verticales para las negras*/
    	conteo = 0;
    	for(int k = 0; k < board.length; k++){
	    	for(int i = 0; i < board.length; i++) {
	    		for(int j = 0; j < board.length; j++) {
	    			if(j == k && board[i][j] == 'A') {
	    				conteo++;
	    			}
	    		}
	    	}
	    	if(conteo == board.length) {
	    		return true;
	    	}else {
	    		conteo = 0;
	    	}
    	}
    	
    	
    	/*Verificamos las verticales para las blancas*/
    	conteo = 0;
    	for(int k = 0; k < board.length; k++){
	    	for(int i = 0; i < board.length; i++) {
	    		for(int j = 0; j < board.length; j++) {
	    			if(j == k && board[i][j] == 'B') {
	    				conteo++;
	    			}
	    		}
	    	}
	    	if(conteo == board.length) {
	    		return true;
	    	}else {
	    		conteo = 0;
	    	}
    	}
    	
    	
    	/*Verificamos la diagonal positiva para las  negras*/
    	conteo = 0;

	    for(int i = 0; i < board.length; i++) {
	    	for(int j = 0; j < board.length; j++) {
	    		if(i == j && board[i][j] == 'A') {
	    			conteo++;
	    		}
	    	}
	    }
	    if(conteo == board.length) {
    		return true;
    	}
	    
	    /*Verificamos la diagonal positiva para las  blancas*/
    	conteo = 0;

	    for(int i = 0; i < board.length; i++) {
	    	for(int j = 0; j < board.length; j++) {
	    		if(i == j && board[i][j] == 'B') {
	    			conteo++;
	    		}
	    	}
	    }
	    if(conteo == board.length) {
    		return true;
    	}
	    
	    /*Verificamos la diagonal negativa para las  negras*/
    	conteo = 0;
    	int j = board.length-1;
	    for(int i = 0; i < board.length; i++) {
	    	if(board[i][j] == 'A') {
	    		conteo++;
	    	}
	    	j--;
	    }
	    if(conteo == board.length) {
    		return true;
    	}
	    
	    /*Verificamos la diagonal negativa para las  blancas*/
    	conteo = 0;
    	j = board.length-1;
	    for(int i = 0; i < board.length; i++) {
	    	if(board[i][j] == 'B') {
	    		conteo++;
	    	}
	    	j--;
	    }
	    if(conteo == board.length) {
    		return true;
    	}
	        	
    	
    	return false;
    }
}