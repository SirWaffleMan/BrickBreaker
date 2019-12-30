package blu3flux.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import blu3flux.BrickBreaker;
import blu3flux.Player;

public class Paddle extends Entity implements Controllable{
	
	Ball ball;
	Image image;
	public boolean isMovingLeft = false;
	public boolean isMovingRight = false;
	public boolean ballAttached = true;
	
	public Paddle(Ball b) {
		this.ball = b;
		width = 200;
		height = 40;
		
		x = 700;
		y = 900 - height - BrickBreaker.ABS_SPACING;
		
		collider = new Rectangle((int)x, (int)y, (int)width, (int)height);
		loadImage();
	}
	
	public void newPaddle() {
		x = 700;
		y = 900 - height - BrickBreaker.ABS_SPACING;
		attachBall();
	}
	
	void loadImage() {
		try {
			image = ImageIO.read(new File("res/paddle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage() {
		return image;
	}

	@Override
	public void moveLeft() {
		this.x -= speed;
		if(this.x < 0) {
			this.x = 0;
		}
		updateCollider();
	}

	@Override
	public void moveRight() {
		this.x += speed;
		if(this.x > BrickBreaker.ABS_WIDTH - getWidth()) {
			this.x = BrickBreaker.ABS_WIDTH - getWidth();
		}
		updateCollider();
	}

	@Override
	public void tick() {
		if(isMovingRight) {
			moveRight();
		}
		
		if(isMovingLeft) {
			moveLeft();
		}
		
		moveAttachedBall();
		checkBallCollision();
		checkBallOutOfBounds();
	}

	private void checkBallOutOfBounds() {
		if(ball.getY() > BrickBreaker.ABS_HEIGHT) {
			Player.lives--;
			
			if(Player.lives <= 0) {
				// TODO: Reset Game
			}
			
			attachBall();
		}
	}

	private void moveAttachedBall() {
		if(ballAttached) {
			double centerX = (2 * getX() + getWidth()) / 2;
			ball.setX(centerX - ball.width/2);
			ball.setY(getY() - 34);
		}
	}

	private void checkBallCollision() {
		if(collider.intersects(ball.collider)) {
			ball.ySpeed = -1 * Math.abs(ball.ySpeed);
			
			double paddleCenter = (2 * x + width) / 2;
			double ballCenter = (2 * ball.x + ball.width) / 2;
			double diff = ballCenter - paddleCenter;
			
			ball.xSpeed = 5 * (diff/(width/2 + ball.width/2));
		}
		
	}
	
	public void attachBall() {
		ball.stopBallMotion();
		ballAttached = true;
	}

	public void detachBall() {
		if(ballAttached) {
			ballAttached = false;
			ball.setDefaultMotion();
		}
	}
}
