package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Line;
import model.ObjectType;
import model.Oval;
import model.PaintImage;
import model.Rectangle;
import view.NetPaintPanel;

public class NetPaint extends JFrame {

	public static void main(String[] args) {
		JFrame netPaintGUI = new NetPaint();
		netPaintGUI.setVisible(true);
	}
	
	private NetPaintPanel panel;
	private ObjectType currentObjectType;
	private NetPaintMouseListener mouseListener;
	private NetPaintMouseMotionListener motionListener;
	private Image image;
	private JColorChooser colorChooser;
	private Color currentColor;
	
	public NetPaint() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setTitle("NetPaint");
		
		currentColor = Color.BLACK;
		
		setupButtons();
		
		setupDrawingPanel();
		
		setupColorChooser();
		
		try {
			image = ImageIO.read(new File("./images/doge.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setupButtons() {
		JPanel buttonPanel = new JPanel();
		
		JButton rectButton = new JButton();
		rectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentObjectType = ObjectType.RECTANGLE;
			}
		});
		rectButton.setText("Rectangle");
		buttonPanel.add(rectButton);
		
		JButton ovalButton = new JButton();
		ovalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentObjectType = ObjectType.OVAL;
			}
		});
		ovalButton.setText("Oval");
		buttonPanel.add(ovalButton);
		
		JButton lineButton = new JButton();
		lineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentObjectType = ObjectType.LINE;
			}
		});
		lineButton.setText("Line");
		buttonPanel.add(lineButton);
		
		JButton imageButton = new JButton();
		imageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentObjectType = ObjectType.PAINT_IMAGE;
			}
		});
		imageButton.setText("Image");
		buttonPanel.add(imageButton);
		
		buttonPanel.setVisible(true);
		buttonPanel.setSize(600, 50);
		this.add(buttonPanel, BorderLayout.NORTH);
	}
	
	private void setupDrawingPanel() {
		panel = new NetPaintPanel();
		panel.setPreferredSize(new Dimension(600, 600));
		mouseListener = new NetPaintMouseListener();
		motionListener = new NetPaintMouseMotionListener();
		panel.addMouseListener(mouseListener);
		panel.addMouseMotionListener(motionListener);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void setupColorChooser() {
		JColorChooser colorChooser = new JColorChooser(Color.BLACK);
		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				currentColor = colorChooser.getColor();
			}
		});
		colorChooser.setPreviewPanel(new JPanel());
		this.add(colorChooser, BorderLayout.SOUTH);
	}
	
	private class NetPaintMouseListener implements MouseListener {
		
		private boolean isClicked;
		
		public NetPaintMouseListener() {
			isClicked = false;
		}
		
		public boolean isMouseClicked() {
			return isClicked;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (isClicked) {
				isClicked = false;
				NetPaint.this.panel.addCurrentObjToList();
				System.out.println("Mouse unclicked");
			} 
			else if (NetPaint.this.currentObjectType != null){
				isClicked = true;
				int x, y;
				switch(NetPaint.this.currentObjectType) {
					case RECTANGLE:
						x = e.getX();
						y = e.getY();
						NetPaint.this.panel.newCurrentObject(new Rectangle(x, y, 0, 0, NetPaint.this.currentColor));
						System.out.println("ayy");
						break;
					case OVAL:
						x = e.getX();
						y = e.getY();
						NetPaint.this.panel.newCurrentObject(new Oval(x, y, 0, 0, NetPaint.this.currentColor));
						break;
					case LINE:
						x = e.getX();
						y = e.getY();
						NetPaint.this.panel.newCurrentObject(new Line(x, y, x, y, NetPaint.this.currentColor));
						break;
					case PAINT_IMAGE:
						x = e.getX();
						y = e.getY();
						NetPaint.this.panel.newCurrentObject(new PaintImage(NetPaint.this.image, x, y, 0, 0));
						break;
					default:
						break;
				}
				System.out.println("Mouse clicked");
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// Unimplemented
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// Unimplemented
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Unimplemented
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// Unimplemented
		}
	}
	
	private class NetPaintMouseMotionListener implements MouseMotionListener {
		
		@Override
		public void mouseMoved(MouseEvent arg0) {
			if (NetPaint.this.mouseListener.isMouseClicked()) {
				int x = arg0.getX();
				int y = arg0.getY();
				
				NetPaint.this.panel.changeCurrentObjSize(x, y);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// Unimplemented
		}
	}
}
