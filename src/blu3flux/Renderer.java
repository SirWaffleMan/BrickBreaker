package blu3flux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import blu3flux.entity.Ball;
import blu3flux.entity.Brick;
import blu3flux.entity.Paddle;

public class Renderer extends JPanel{
	
	Paddle paddle;
	Ball ball;
	ArrayList<Brick> bricks;
	
	public Renderer(int width, int height, BrickBreaker game) {
		setPreferredSize(new Dimension(width, height));
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
		g.setColor(Color.WHITE);
		g.fillRect(	(int)(paddle.getX()/BrickBreaker.ABS_WIDTH * getWidth()),
					(int)(paddle.getY()/BrickBreaker.ABS_HEIGHT * getHeight()), 
					(int)(paddle.getWidth()/BrickBreaker.ABS_WIDTH * getWidth()), 
					(int)(paddle.getHeight()/BrickBreaker.ABS_HEIGHT * getHeight()));
		
		// Draw Ball
		g.setColor(Color.RED);
		g.fillOval(	(int)(ball.getX()/BrickBreaker.ABS_WIDTH * getWidth()),
					(int)(ball.getY()/BrickBreaker.ABS_HEIGHT * getHeight()), 
					(int)(ball.getWidth()/BrickBreaker.ABS_WIDTH * getWidth()), 
					(int)(ball.getHeight()/BrickBreaker.ABS_HEIGHT * getHeight()));
		
		// Draw Bricks
		for(Brick brick : bricks) {
			g.setColor(brick.getColor());
			g.fillRect(	(int)(brick.getX()/BrickBreaker.ABS_WIDTH * getWidth()),
						(int)(brick.getY()/BrickBreaker.ABS_HEIGHT * getHeight()), 
						(int)(brick.getWidth()/BrickBreaker.ABS_WIDTH * getWidth()), 
						(int)(brick.getHeight()/BrickBreaker.ABS_HEIGHT * getHeight()));
		}
	}
}
