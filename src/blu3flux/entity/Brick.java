package blu3flux.entity;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brick extends Entity{
	
	int lives = 3;
	Image[] images;
	
	public Brick(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = 200;
		this.height = 40;
		
		collider = new Rectangle((int)x, (int)y, (int)width, (int)height);
		images = new Image[4];
		loadImage();
	}
	
	public Rectangle getCollider() {
		return collider;
	}
	
	public void newBrick() {
		lives = 3;
		collider.width = (int) width;
		collider.height = (int) height;
	}
	
	public void hit() {
		lives--;
		if(lives <= 0) {
			collider.width = 0;
			collider.height = 0;
			lives = 0;
		}
	}
	
	public Image getImage() {
		switch(lives) {
		case 3:
			return images[0];
		case 2:
			return images[1];
		case 1:
			return images[2];
		default:
			return images[3];	
		}
	}
	
	public void loadImage() {
		try {
			images[0] = ImageIO.read(new File("res/brick1.png"));
			images[1] = ImageIO.read(new File("res/brick2.png"));
			images[2] = ImageIO.read(new File("res/brick3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		images[3] = new BufferedImage(200, 40, BufferedImage.TYPE_INT_ARGB);
		
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
