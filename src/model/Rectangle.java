package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends PaintObject {
	
	private int initX, initY, x, y, width, height;
	private Color color;
	
	public Rectangle (int x, int y, int width, int height, Color color) {
		super();
		this.initX = x;
		this.initY = y;
		this.x = x;
		this.y = y;
		this.width = width; 
		this.height = height;
		this.color = color;
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
		g2.setColor(color);
		g2.fillRect(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
