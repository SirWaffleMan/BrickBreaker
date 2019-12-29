package blu3flux;

import java.util.ArrayList;

import javax.swing.JFrame;

import blu3flux.entity.Ball;
import blu3flux.entity.Brick;
import blu3flux.entity.Paddle;

/*
 * Game runs on a 1600 x 900 grid.
 */

public class BrickBreaker{
	
	public static double ABS_WIDTH = 1600;
	public static double ABS_HEIGHT = 900;
	public static double ABS_SPACING = 10;
	
	String title = "Brick Breaker";
	JFrame frame;
	Renderer renderer;
	
	Paddle paddle;
	Ball ball;
	ArrayList<Brick> bricks;
	
	
	public BrickBreaker() {
		init();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(renderer);
		frame.pack();
	}
	
	void start() {
		while(true) {
			renderer.repaint();
		}
	}
	
	void init() {
		frame = new JFrame(title);
		paddle = new Paddle();
		ball = new Ball();
		bricks = new ArrayList<Brick>();
		renderer = new Renderer(800, 450, this);
	}
}
