package blu3flux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import blu3flux.entity.Ball;
import blu3flux.entity.Brick;
import blu3flux.entity.Paddle;

public class Renderer extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	BrickBreaker game;
	Paddle paddle;
	Ball ball;
	ArrayList<Brick> bricks;
	
	public Renderer(int width, int height, BrickBreaker game) {
		setPreferredSize(new Dimension(width, height));
		this.game = game;
		paddle = game.paddle;
		ball = game.ball;
		bricks = game.bricks;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// Draw Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw Paddle
		g.drawImage(paddle.getImage(),	
					(int)(paddle.getX()/BrickBreaker.ABS_WIDTH * getWidth()),
					(int)(paddle.getY()/BrickBreaker.ABS_HEIGHT * getHeight()), 
					(int)(paddle.getWidth()/BrickBreaker.ABS_WIDTH * getWidth()), 
					(int)(paddle.getHeight()/BrickBreaker.ABS_HEIGHT * getHeight()), null);
		
		// Draw Ball
		g.drawImage(ball.getImage(),
					(int)(ball.getX()/BrickBreaker.ABS_WIDTH * getWidth()),
					(int)(ball.getY()/BrickBreaker.ABS_HEIGHT * getHeight()), 
					(int)(ball.getWidth()/BrickBreaker.ABS_WIDTH * getWidth()), 
					(int)(ball.getHeight()/BrickBreaker.ABS_HEIGHT * getHeight()), null);
		
		// Draw Bricks
		for(Brick brick : bricks) {
			g.drawImage(brick.getImage(),
						(int)(brick.getX()/BrickBreaker.ABS_WIDTH * getWidth()),
						(int)(brick.getY()/BrickBreaker.ABS_HEIGHT * getHeight()), 
						(int)(brick.getWidth()/BrickBreaker.ABS_WIDTH * getWidth()), 
						(int)(brick.getHeight()/BrickBreaker.ABS_HEIGHT * getHeight()), null);
		}
		
		// Draw Player HUD
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 16)); 
		// Draw Points
		g.drawString(	String.valueOf(Player.points), 
						(int)(10 / BrickBreaker.ABS_WIDTH * getWidth()), 
						(int)(890/BrickBreaker.ABS_HEIGHT * getHeight()));
		// Draw Lives
		g.drawString(	String.valueOf(Player.lives), 
				(int)(1580 / BrickBreaker.ABS_WIDTH * getWidth()), 
				(int)(890/BrickBreaker.ABS_HEIGHT * getHeight()));
		
		// Draw paused screen
		if(game.isPaused()) {
			g.drawString("PAUSED", 
						(int)(BrickBreaker.ABS_WIDTH/2/BrickBreaker.ABS_WIDTH * getWidth() - 27), 
						(int)(BrickBreaker.ABS_HEIGHT/2/BrickBreaker.ABS_HEIGHT * getHeight()));
		}
		
	}
}
