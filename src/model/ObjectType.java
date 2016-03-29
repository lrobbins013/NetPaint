package model;

/**
 * An enum to classify the various PaintObjects to make it easier to
 * determine what to draw.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 *
 */
public enum ObjectType {
	
	/**
	 * Represents the Rectangle PaintObject.
	 */
	RECTANGLE,
	
	/**
	 * Represents the Oval PaintObject.
	 */
	OVAL, 
	
	/**
	 * Represents the Line PaintObject.
	 */
	LINE, 
	
	/**
	 * Represents the PaintImage PaintObject.
	 */
	PAINT_IMAGE;
}
