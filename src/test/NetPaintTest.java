package test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Line;
import model.ObjectType;
import model.Oval;
import model.PaintImage;
import model.Rectangle;

import org.junit.Test;

/**
 * Tests for NetPaint
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 *
 */
public class NetPaintTest {

	@Test
	public void test() throws IOException {
		Color color = Color.BLACK;
		Line line = new Line(0, 0, 1, 1, color);
		Oval oval = new Oval(5, 5, 2, 2, color);
		Rectangle rect = new Rectangle(20, 200, 6, 3, color);
		PaintImage doge = new PaintImage(ImageIO.read(new File("./images/doge.jpeg")), 3, 3, 20, 20);
		ObjectType type;
		
		line.changeSize(2, 2);
		assertEquals(0, line.getX());
		assertEquals(0, line.getY());
		type = ObjectType.LINE;
		
		oval.changeSize(10, 10);
		assertEquals(5, oval.getX());
		assertEquals(5, oval.getY());
		oval.changeSize(4, 4);
		assertEquals(4, oval.getX());
		assertEquals(4, oval.getY());
		type = ObjectType.OVAL;
		
		rect.changeSize(30, 205);
		assertEquals(20, rect.getX());
		assertEquals(200, rect.getY());
		rect.changeSize(10, 195);
		assertEquals(10, rect.getX());
		assertEquals(195, rect.getY());
		type = ObjectType.RECTANGLE;
		
		doge.changeSize(5, 5);
		assertEquals(3, doge.getX());
		assertEquals(3, doge.getY());
		doge.changeSize(0, 0);
		assertEquals(0, doge.getX());
		assertEquals(0, doge.getY());
		type = ObjectType.PAINT_IMAGE;
	}

}
