package model;

import java.awt.Graphics2D;

public abstract class PaintObject {
	
	private int x, y;
	
	public abstract void changeSize(int newParam1, int newParam2);
	
	public abstract void draw(Graphics2D g2);
	
	public abstract int getX();
	
	public abstract int getY();
}
