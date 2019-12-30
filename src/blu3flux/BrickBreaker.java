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
	boolean paused = false;
	int gameSpeed = 5500000;
	public static double ABS_WIDTH = 1600;
	public static double ABS_HEIGHT = 900;
	public static double ABS_SPACING = 10;
	
	// GUI
	String title = "Brick Breaker - Blu3Flux";
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
	
	void newGame() {
		Player.lives = 3;
		Player.points = 0;
		paddle.newPaddle();
		for(Brick brick: bricks) {
			brick.newBrick();
		}
	}
	
	void checkPlayerDeath() {
		if(Player.lives < 0) {
			newGame();
		}
	}
	
	void tick() {
		paddle.tick();
		ball.tick();
		checkPlayerDeath();
		checkFinishedLevel();
	}
	
	private void checkFinishedLevel() {
		for(Brick brick: bricks) {
			if(brick.getCollider().width > 0) {
				return;
			}
		}
		for(Brick brick: bricks) {
			brick.newBrick();
		}
		
		paddle.newPaddle();
	}

	public boolean isPaused() {
		return paused;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	void addBricks() {
		int numOfRows = 6;
		int bricksPerRow = 7;
		
		for(int i = 0; i < numOfRows; i++) {
			for(int j = 0; j < bricksPerRow; j++) {
				bricks.add(new Brick(100 + j*200, 50 + i*50));
			}
		}
	}
	
	void init() {
		frame = new JFrame(title);
		bricks = new ArrayList<Brick>();
		addBricks();
		ball = new Ball(bricks);
		paddle = new Paddle(ball);
		renderer = new Renderer(1024, 576, this);
		thread = new Thread(this);
		input = new KeyboardControl(this);
		frame.addKeyListener(input);
	}
	
	public void togglePause() {
		paused = !paused;
	}

	@Override
	public void run() {
		long last = System.nanoTime();
		while(isRunning) {
			if(!paused) {
				if(System.nanoTime() - last > gameSpeed) {
					last = System.nanoTime();
					tick();
				}
			}
		}
	}
}
