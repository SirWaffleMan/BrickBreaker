package blu3flux.entity;

import java.awt.Rectangle;

public class Brick extends Entity{
	public Brick(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = 200;
		this.height = 40;
		
		collider = new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
