package blu3flux.entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;

import blu3flux.BrickBreaker;

public class Ball extends Entity{
	
	double xSpeed = 3.0f;
	double ySpeed = -3.0f;
	
	ArrayList<Brick> bricks;
	ArrayList<Brick> collidingBricks;
	
	public Ball(ArrayList<Brick> bricks) {
		
		width = 32;
		height = 32;
		
		x = 800 - width/2;
		y = 810;
		
		collider = new Rectangle((int)x, (int)y, (int)width, (int)height);
		this.bricks = bricks;
		collidingBricks = new ArrayList<Brick>();
	}
	
	private void checkWallCollisions() {
		// Right wall
		if(x > BrickBreaker.ABS_WIDTH-width) {
			xSpeed *= -1;
		}
		
		// Left wall
		if(x < 0) {
			xSpeed *= -1;
		}
		
		// Upper Wall
		if(y < 0) {
			ySpeed *= -1;
		}
	}

	@Override
	public void tick() {
		x += xSpeed;
		y += ySpeed;
		checkWallCollisions();
		checkBrickCollisions();
		updateCollider();
	}

	private void checkBrickCollisions() {
		for(int i = 0; i < bricks.size(); i++) {
			if(collider.intersects(bricks.get(i).collider)) {
				Rectangle intersection = collider.intersection(bricks.get(i).collider);
				if(intersection.width > intersection.height) {
					ySpeed *= -1;
					if(getY() > bricks.get(i).getY()) {
						ySpeed = Math.abs(ySpeed);
					}else {
						ySpeed = -1 * Math.abs(ySpeed);
					}
				}else {
					xSpeed *= -1;
				}
				if(!collidingBricks.contains(bricks.get(i)))
					collidingBricks.add(bricks.get(i));
			}
		}
		checkCollidedBricks();
	}
	
	void checkCollidedBricks() {
		/*
		 * Checks when ball has been removed from brick collider. It will
		 * decrement the brick's health.
		 */
		for(int i = 0; i < collidingBricks.size(); i++) {
			if(!collider.intersects(collidingBricks.get(i).collider)) {
				collidingBricks.get(i).hit();
				collidingBricks.remove(collidingBricks.get(i));
			}
		}
	}
}
