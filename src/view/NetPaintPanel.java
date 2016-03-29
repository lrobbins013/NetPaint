package view;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JPanel;

import model.PaintObject;

/**
 * The JPanel that has the shapes drawn onto it.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 *
 */
public class NetPaintPanel extends JPanel {
	
	private Vector<PaintObject> drawings;
	private PaintObject currentObject;
	
	/**
	 * Creates a new JPanel to hold all of the PaintObjects that are to be
	 * drawn on it. Creates a new Vector<PaintObjec>() to hold all of the
	 * PaintObjects that are drawn on it. Also creates a temporary PaintObject
	 * to store the information while it is being drawn.
	 */
	public NetPaintPanel() {
		drawings = new Vector<PaintObject>();
		currentObject = null;
	}
	
	/**
	 * Sets the currentObject instance variable to the given PaintObject. Then
	 * the JPanel is repainted.
	 * 
	 * @param newObj
	 * 		The new PaintObject.
	 */
	public void newCurrentObject(PaintObject newObj) {
		this.currentObject = newObj;
		repaint();
	}
	
	/**
	 * Gets and returns the current PaintObject that is in the instance variable
	 * currentObject. It currentObject is null, it prints a message and returns
	 * null.
	 * 
	 * @return
	 * 		The current PaintObject in the instance variable currentObject.
	 */
	public PaintObject getCurrentObject() {
		if (currentObject != null) {
			return currentObject;
		}
		else {
			System.out.println("No current obj found");
			return null;
		}
	}
	
	/**
	 * Adds the currentObject to the list of PaintObjects.
	 */
	public void addCurrentObjToList() {
		if (currentObject != null){
			this.drawings.add(currentObject);
		}
		currentObject = null;
		repaint();
	}
	
	/**
	 * Changes the size of the currentPaintObject.
	 * 
	 * @param newParam1
	 * 		The new x value of the PaintObject.
	 * @param newParam2
	 * 		The new y value of the PaintObject.
	 */
	public void changeCurrentObjSize(int newParam1, int newParam2) {
		this.currentObject.changeSize(newParam1, newParam2);
		repaint();
	}
	
	/**
	 * Updates the Vector of PaintObjects to be drawn and repaints the panel.
	 * 
	 * @param newDrawings
	 * 		The updated Vector of PaintObjects
	 */
	public void updateDrawing(Vector<PaintObject> newDrawings) {
		this.drawings = newDrawings;
		repaint();
	}
	
	/**
	 * Gets the current Vector of PaintObjects and returns it.
	 * 
	 * @return
	 * 		A Vector of PaintObjects to be drawn.
	 */
	public Vector<PaintObject> getDrawing() {
		return drawings;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for (PaintObject object : drawings) {
			object.draw(g2);
		}
		
		if (currentObject != null) {
			currentObject.draw(g2);
		}
	}
}
