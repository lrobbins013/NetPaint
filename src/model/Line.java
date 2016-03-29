package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * The Line type of PaintObject.  Holds both beginning and end points
 * of the line as well as the color of the line.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 */
public class Line extends PaintObject implements Serializable {
	
	private static final long serialVersionUID = 6687497440059856130L;
	
	private int x1, y1, x2, y2;
	private Color color;
	
	/**
	 * Creates a new line by setting the instance variables for the new Line 
	 * given two different coordinates and one color for the line.
	 * 
	 * @param x1
	 * 		The x value of the beginning coordinate of the line.
	 * @param y1
	 * 		The y value of the beginning coordinate of the line.
	 * @param x2
	 * 		The x value of the end coordinate of the line.
	 * @param y2
	 * 		The y value of the end coordinate of the line.
	 * @param color
	 * 		The color of the line.
	 */
	public Line (int x1, int y1, int x2, int y2, Color color) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	
	@Override
	public void changeSize(int newX, int newY) {
		this.x2 = newX;
		this.y2 = newY;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.drawLine(x1, y1, x2, y2);
	}

	@Override
	public int getX() {
		return x1;
	}

	@Override
	public int getY() {
		return y1;
	}
	
	
}
