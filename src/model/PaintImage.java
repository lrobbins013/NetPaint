package model;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 * The PaintImage type of PaintObject. Holds the image that will be drawn
 * when it is called. Also holds the  initial value for x and y, the changing
 * value for x and y, and the width and height of the image.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 *
 */
public class PaintImage extends PaintObject {
	
	private Image image;
	private int initX, initY, x, y, width, height;
	
	/**
	 * Creates a new PaintImage by setting the instance variables for the new
	 * PaintImage given the image, initial x and y value, width, and height of
	 * the PaintImage.
	 * 
	 * @param image
	 * 		The image that is to be drawn.
	 * @param x
	 * 		The initial x value of the image.
	 * @param y
	 * 		The initial y value of the image.
	 * @param width
	 * 		The width of the image.
	 * @param height
	 * 		The height of the image.
	 */
	public PaintImage (Image image, int x, int y, int width, int height) {
		super();
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
