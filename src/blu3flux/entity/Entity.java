package blu3flux.entity;

import java.awt.Rectangle;

public abstract class Entity {
	
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	
	Rectangle collider;
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getCenterX() {
		return (x + x + width)/2;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	protected void updateCollider() {
		collider.x = (int)x;
		collider.y = (int)y;
		collider.width = (int)width;
		collider.height = (int)height;
	}
	
	public abstract void tick();
}
