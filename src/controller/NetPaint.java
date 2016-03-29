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
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Line;
import model.ObjectType;
import model.Oval;
import model.PaintImage;
import model.PaintObject;
import model.Rectangle;
import view.NetPaintPanel;

/**
 * The GUI controller for NetPaint.
 * 
 * @author Lucas Robbins
 * @author Ana Huff
 *
 */
public class NetPaint extends JFrame {
	
	private static final String ADDRESS = "localhost";

	/**
	 * Creates a new GUI by initializing netPaintGUI.
	 * 
	 * @param args
	 * 		An array of Strings.
	 */
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
	private Socket socket;
	private ObjectInputStream is;
	private ObjectOutputStream os;
	
	/**
	 * Initializes the new GUI.
	 */
	public NetPaint() {
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
		
		openConnection();
		new ServerListener().start();
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
		panel.setBackground(Color.WHITE);
		mouseListener = new NetPaintMouseListener();
		motionListener = new NetPaintMouseMotionListener();
		panel.addMouseListener(mouseListener);
		panel.addMouseMotionListener(motionListener);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void setupColorChooser() {
		colorChooser = new JColorChooser(Color.BLACK);
		colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				currentColor = colorChooser.getColor();
			}
		});
		colorChooser.setPreviewPanel(new JPanel());
		this.add(colorChooser, BorderLayout.SOUTH);
	}
	
	@SuppressWarnings("unchecked")
	private void openConnection() {
		try {
			this.socket = new Socket(ADDRESS, Server.SERVER_PORT);
			this.os = new ObjectOutputStream(socket.getOutputStream());
			this.is = new ObjectInputStream(socket.getInputStream());
			this.panel.updateDrawing((Vector<PaintObject>)is.readObject());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private class ServerListener extends Thread {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			
			try {
				while (true) {
					NetPaint.this.panel.updateDrawing((Vector<PaintObject>) is.readObject());
				}
			} catch (IOException e) {
				NetPaint.this.cleanUpAndQuit("The server hung up on us-- Exiting...");
			} catch (ClassNotFoundException e) {
				NetPaint.this.cleanUpAndQuit("Got something from server that wasn't a string...");
			}
		}
	}

	/**
	 * Closes the socket and cleans up NetPaint.
	 * 
	 * @param message
	 * 		A String to print when the socket is closed.
	 */
	public void cleanUpAndQuit(String message) {
		JOptionPane.showMessageDialog(NetPaint.this, message);
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		NetPaint.this.dispatchEvent(new WindowEvent(NetPaint.this, WindowEvent.WINDOW_CLOSED));
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
		public void mousePressed(MouseEvent e) {
			if (isClicked) {
				isClicked = false;
				NetPaint.this.panel.addCurrentObjToList();
				try {
					NetPaint.this.os.writeObject(NetPaint.this.panel.getDrawing());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
		public void mouseClicked(MouseEvent e) {
			// Unimplemented
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
