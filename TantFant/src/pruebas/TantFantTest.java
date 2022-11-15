package pruebas;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import aplicacion.TantFant;
import aplicacion.TantFantException;

public class TantFantTest {
	
	@Test
    public void shouldVerifyTantFantSize(){
        try {
			TantFant t = new TantFant(2);
		} catch (TantFantException e) {
			// TODO Auto-generated catch block
			assertEquals(TantFantException.INVALID_SIZE,e.getMessage());
		}
    }    
	
	@Test
    public void shouldVerifyWhenAPlayerPutAPieceOffTheBoard(){
        try {
			TantFant t = new TantFant(3);
			t.play(2, 2, 10, 10);
		} catch (TantFantException e) {
			// TODO Auto-generated catch block
			assertEquals(TantFantException.OFF_THE_BOARD,e.getMessage());
		}
    }    
	
	@Test
    public void shouldVerifyWhenIsNotPlayerTurn(){
        try {
			TantFant t = new TantFant(3);
			//We are starting with the black´s always, and here are starting the White Pieces
			t.play(0, 0, 1, 0);
		} catch (TantFantException e) {
			// TODO Auto-generated catch block
			assertEquals(TantFantException.INVALID_PLAYER,e.getMessage());
		}
    }    
	
	
	@Test
    public void shouldVerifyWhenIsNotAPiece(){
        try {
			TantFant t = new TantFant(3);
			//In position (1,0) there are not pieces
			t.play(1, 0, 1, 1);
		} catch (TantFantException e) {
			// TODO Auto-generated catch block
			assertEquals(TantFantException.INVALID_MOVEMENT,e.getMessage());
		}
    }    
	
	@Test
    public void shouldVerifyWhenIsAlreadyAPieceInThatPosition(){
        try {
			TantFant t = new TantFant(3);
			//In position (1,0) there are not pieces
			t.play(2, 2, 0, 0);
		} catch (TantFantException e) {
			// TODO Auto-generated catch block
			assertEquals(TantFantException.INVALID_MOVEMENT,e.getMessage());
		}
    }    
	
	
	
	
}
