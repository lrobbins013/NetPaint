package model;

import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class PaintObject implements Serializable {
	
	private static final long serialVersionUID = -3089051455706162523L;
	//private int x, y;
	
	public abstract void changeSize(int newParam1, int newParam2);
	
	public abstract void draw(Graphics2D g2);
	
	public abstract int getX();
	
	public abstract int getY();
}
