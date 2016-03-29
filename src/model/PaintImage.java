package model;

import java.awt.Graphics2D;
import java.awt.Image;

public class PaintImage implements PaintObject {
	
	private Image image;
	private int initX, initY, x, y, width, height;
	
	public PaintImage (Image image, int x, int y, int width, int height) {
		this.image = image;
		this.initX = x;
		this.initY = y;
		this.x = x;
		this.y = y;
		this.width = width; 
		this.height = height;
	}
	
	@Override
	public void changeSize(int newX, int newY) {
		if (newX < initX) {
			x = newX;
			width = initX - newX;
		} else {
			x = initX;
			width = newX - initX;
		}
		
		if (newY < initY) {
			y = newY;
			height = initY - newY;
		} else {
			y = initY;
			height = newY - initY;
		}
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, width, height, null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
