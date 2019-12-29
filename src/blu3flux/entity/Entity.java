package blu3flux.entity;

public abstract class Entity {
	
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	
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
}
