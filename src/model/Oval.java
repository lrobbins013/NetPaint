package model;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Oval type of PaintObject. Holds the x and y value of the starting
 * position of the Oval, the width, the height, and the color of the Oval.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 *
 */
public class Oval extends PaintObject {
	
	private int initX, initY, x, y, width, height;
	private Color color;
	
	/**
	 * Creates a new Oval by setting the instance variables for the new Oval
	 * given the start coordinate of the Oval, the height and width of the Oval,
	 * and the color of the Oval.
	 * 
	 * @param x
	 * 		The x value of the starting position of the Oval.
	 * @param y
	 * 		The y value of the starting position of the Oval.
	 * @param width
	 * 		The width of the Oval.
	 * @param height
	 * 		The height of the Oval.
	 * @param color
	 * 		The color of the Oval.
	 */
	public Oval (int x, int y, int width, int height, Color color) {
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
		g2.fillOval(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
