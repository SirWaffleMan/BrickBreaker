package blu3flux;

import java.util.ArrayList;

import javax.swing.JFrame;

import blu3flux.controls.KeyboardControl;
import blu3flux.entity.Ball;
import blu3flux.entity.Brick;
import blu3flux.entity.Paddle;

/*
 * Game runs on a 1600 x 900 grid.
 */

public class BrickBreaker implements Runnable{
	
	Thread thread;
	
	// Parameters
	boolean isRunning = false;
	int gameSpeed = 5500000;
	public static double ABS_WIDTH = 1600;
	public static double ABS_HEIGHT = 900;
	public static double ABS_SPACING = 10;
	
	// GUI
	String title = "Brick Breaker";
	JFrame frame;
	Renderer renderer;
	
	// Entities
	Paddle paddle;
	Ball ball;
	ArrayList<Brick> bricks;
	
	// Controls
	KeyboardControl input;
	
	
	public BrickBreaker() {
		init();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(renderer);
		frame.pack();
	}
	
	void start() {
		isRunning = true;
		thread.start();
		while(isRunning) {
			renderer.repaint();
		}
	}
	
	void tick() {
		paddle.tick();
		ball.tick();
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	void init() {
		frame = new JFrame(title);
		bricks = new ArrayList<Brick>();
		// Adding temporary bricks
		bricks.add(new Brick(20, 10));
		bricks.add(new Brick(230, 10));
		bricks.add(new Brick(440, 10));
		bricks.add(new Brick(650, 10));
		bricks.add(new Brick(860, 10));
		bricks.add(new Brick(1070, 10));
		bricks.add(new Brick(1280, 10));
		
		ball = new Ball(bricks);
		paddle = new Paddle(ball);
		renderer = new Renderer(800, 450, this);
		thread = new Thread(this);
		input = new KeyboardControl(this);
		frame.addKeyListener(input);
		
		
	}

	@Override
	public void run() {
		long last = System.nanoTime();
		while(isRunning) {
			if(System.nanoTime() - last > gameSpeed) {
				last = System.nanoTime();
				tick();
			}
		}
	}
}
