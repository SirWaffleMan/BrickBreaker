package blu3flux.entity;

import java.awt.Rectangle;

import blu3flux.BrickBreaker;

public class Paddle extends Entity implements Controllable{
	
	Ball ball;
	public boolean isMovingLeft = false;
	public boolean isMovingRight = false;
	
	public Paddle(Ball b) {
		this.ball = b;
		width = 200;
		height = 40;
		
		x = 700;
		y = 900 - height - BrickBreaker.ABS_SPACING;
		
		collider = new Rectangle((int)x, (int)y, (int)width, (int)height);
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
		
		checkBallCollision();
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
}
