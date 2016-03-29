package model;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Rectangle type of PaintObject. Holds the initial x and y values of the
 * Rectangle and the changing value for x and y. Also holds the width and height
 * of the Rectangle and the color of the Rectangle.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 *
 */
public class Rectangle extends PaintObject {
	
	private int initX, initY, x, y, width, height;
	private Color color;
	
	/**
	 * Creates a new Rectangle by setting the instance variables for the new
	 * Rectangle given the initial x and y values, width, height, and color of
	 * the new Rectangle
	 * 
	 * @param x
	 * 		The initial x value of the Rectangle.
	 * @param y
	 * 		The initial y value of the Rectangle.
	 * @param width
	 * 		The width of the Rectangle.
	 * @param height
	 * 		The height of the Rectangle.
	 * @param color
	 * 		The color of the Rectangle.
	 */
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
