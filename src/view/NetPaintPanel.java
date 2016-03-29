package view;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import model.PaintObject;

public class NetPaintPanel extends JPanel {
	
	private List<PaintObject> drawings;
	private PaintObject currentObject;
	
	public NetPaintPanel() {
		drawings = new Vector<PaintObject>();
		currentObject = null;
	}
	
	public void newCurrentObject(PaintObject newObj) {
		this.currentObject = newObj;
		repaint();
	}
	
	public PaintObject getCurrentObject() {
		if (currentObject != null) {
			return currentObject;
		}
		else {
			System.out.println("No current obj found");
			return null;
		}
	}
	
	public void addCurrentObjToList() {
		if (currentObject != null){
			this.drawings.add(currentObject);
		}
		currentObject = null;
		repaint();
	}
	
	public void changeCurrentObjSize(int newParam1, int newParam2) {
		this.currentObject.changeSize(newParam1, newParam2);
		repaint();
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
