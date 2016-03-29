package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;


public class PaintImage extends PaintObject implements Serializable{
	
	private static final long serialVersionUID = -3280984178883701076L;
	
	private ImageIcon image;
	private int initX, initY, x, y, width, height;
	
	public PaintImage (Image image, int x, int y, int width, int height) {
		super();
		this.image = new ImageIcon(image);
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
		g2.drawImage(image.getImage(), x, y, width, height, null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
