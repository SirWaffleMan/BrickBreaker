package blu3flux.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import blu3flux.BrickBreaker;
import blu3flux.Player;

public class Ball extends Entity{
	
	Image image;
	
	final double defaultSpeed = 3.0f;
	
	double xSpeed = 0;
	double ySpeed = 0;
	
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
		loadImage();
	}
	
	public Image getImage() {
		return image;
	}
	
	public boolean outOfBounds() {
		if(y > BrickBreaker.ABS_HEIGHT) {
			return true;
		}
		return false;
	}
	
	private void checkWallCollisions() {
		// Right wall
		if(x > BrickBreaker.ABS_WIDTH-width) {
			xSpeed = -1 * Math.abs(xSpeed);
		}
		
		// Left wall
		if(x < 0) {
			xSpeed = Math.abs(xSpeed);
		}
		
		// Upper Wall
		if(y < 0) {
			ySpeed = Math.abs(ySpeed);
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
	
	void loadImage() {
		try {
			image = ImageIO.read(new File("res/ball.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
					
					if(getX() > bricks.get(i).getX()) {
						xSpeed = Math.abs(xSpeed);
					}else {
						xSpeed = -1 * Math.abs(xSpeed);
					}
				}
				if(!collidingBricks.contains(bricks.get(i)))
					collidingBricks.add(bricks.get(i));
			}
		}
		checkCollidedBricks();
	}
	
	public boolean collidingWithBricks() {
		for(int i = 0; i < collidingBricks.size(); i++) {
			if(!collider.intersects(collidingBricks.get(i).collider)) {
				return true;
			}
		}
		return false;
	}
	
	public void setDefaultMotion() {
		xSpeed = defaultSpeed;
		ySpeed = -defaultSpeed;
	}
	
	public void setRandomMotion() {
		xSpeed = Math.random()*defaultSpeed;
		ySpeed = -defaultSpeed;
		
		if(Math.random() > 0.5) {
			xSpeed *= -1;
		}
		
	}
	
	public void stopBallMotion() {
		xSpeed = 0;
		ySpeed = 0;
	}
	
	public double getXSpeed() {
		return xSpeed;
	}
	
	public double getYSpeed() {
		return ySpeed;
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
				Player.points += Player.brickHitPoints;
			}
		}
	}
}
