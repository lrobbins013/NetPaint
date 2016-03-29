package model;

import java.awt.Graphics2D;

public interface PaintObject {
	
	public void changeSize(int newParam1, int newParam2);
	
	public void draw(Graphics2D g2);
	
	public int getX();
	
	public int getY();
}
