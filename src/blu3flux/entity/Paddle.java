package blu3flux.entity;

import blu3flux.BrickBreaker;

public class Paddle extends Entity{
	
	public Paddle() {
		width = 200;
		height = 30;
		
		x = 700;
		y = 900 - height - BrickBreaker.ABS_SPACING;
	}
}
