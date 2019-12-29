package blu3flux.entity;

import java.awt.Color;
import java.awt.Rectangle;

public class Brick extends Entity{
	
	int lives = 3;
	
	public Brick(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = 200;
		this.height = 40;
		
		collider = new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public void hit() {
		lives--;
		if(lives <= 0) {
			collider.width = 0;
			collider.height = 0;
			lives = 0;
		}
	}
	
	public Color getColor() {
		switch(lives) {
		case 3:
			return Color.red;
		case 2:
			return Color.orange;
		case 1:
			return Color.yellow;
		}
		
		return new Color(255, 255, 255, 0);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
