package blu3flux.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import blu3flux.BrickBreaker;

public class KeyboardControl implements KeyListener{
	
	BrickBreaker game;
	
	public KeyboardControl(BrickBreaker game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			game.getPaddle().isMovingLeft = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.getPaddle().isMovingRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			game.getPaddle().isMovingLeft = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.getPaddle().isMovingRight = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
