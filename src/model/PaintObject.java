package model;

import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * The PaintObject abstract class with methods to be used by the various
 * other PaintObject types.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 */
public abstract class PaintObject implements Serializable {
	
	private static final long serialVersionUID = -3089051455706162523L;
	
	/**
	 * Holds the new x and y values of the PaintObject.
	 * 
	 * @param newParam1
	 * 		The new x value of the PaintObject.
	 * @param newParam2
	 * 		The new y value of the PaintObject.
	 */
	public abstract void changeSize(int newParam1, int newParam2);
	
	/**
	 * The method that draws the shapes or image on the JPanel.
	 * 
	 * @param g2
	 * 		The Graphics2D component of the JPanel.
	 */
	public abstract void draw(Graphics2D g2);
	
	/**
	 * Gets the current x value of the PaintObject and returns it.
	 * 
	 * @return
	 * 		The current x value of the PaintObject.
	 */
	public abstract int getX();
	
	/**
	 * Gets the current y value of the PaintObject and returns it.
	 * 
	 * @return
	 * 		The current y value of the PaintObject.
	 */
	public abstract int getY();
}
