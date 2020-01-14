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
		
		if(e.getKeyCode() == KeyEvent.VK_F1){
			if(!(game.gameSpeedMult <= 10)) {
				game.gameSpeedMult -= 10;
			}else {
				game.gameSpeedMult = 1;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_F2){
			if(game.gameSpeedMult == 1) {
				game.gameSpeedMult = 10;
			}else {
				game.gameSpeedMult += 10;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F3){
			if(!(game.gameSpeedMult <= 100)) {
				game.gameSpeedMult -= 100;
			}else {
				game.gameSpeedMult = 1;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_F4){
			if(game.gameSpeedMult == 1) {
				game.gameSpeedMult = 100;
			}else {
				game.gameSpeedMult += 100;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.togglePause();
		}
		
		if(!game.isPaused()) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				game.getPaddle().detachBall();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				game.getPaddle().isMovingLeft = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.getPaddle().isMovingRight = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(!game.isPaused()) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				game.getPaddle().isMovingLeft = false;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.getPaddle().isMovingRight = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
