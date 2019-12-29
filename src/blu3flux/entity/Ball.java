package blu3flux.entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import blu3flux.BrickBreaker;

public class Ball extends Entity{
	
	double xSpeed = 3.0f;
	double ySpeed = -3.0f;
	
	ArrayList<Brick> bricks;
	
	public Ball(ArrayList<Brick> bricks) {
		
		width = 32;
		height = 32;
		
		x = 800 - width/2;
		y = 810;
		
		collider = new Rectangle((int)x, (int)y, (int)width, (int)height);
		this.bricks = bricks;
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
				}else {
					xSpeed *= -1;
				}
				bricks.remove(bricks.get(i));
			}
		}
	}
}
