package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import model.PaintObject;



public class Server {

	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = Collections
			.synchronizedList(new ArrayList<ObjectOutputStream>());
	public static Vector<PaintObject> drawings = new Vector<PaintObject>();
	
	public static void main(String[] args) throws IOException {
		sock = new ServerSocket(SERVER_PORT);
		
		while (true) {
			// TODO 1: Accept a connection from the ServerSocket.
			Socket s = sock.accept();

			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

			// Write the current image stored on the server to the new client
			os.writeObject(drawings);
			os.reset();
			
			// TODO 2: Save the output stream to our clients list so we can
			// broadcast to this client later
			clients.add(os);
			
			// TODO 3: Start a new ClientHandler thread for this client.
			ClientHandler c = new ClientHandler(is, clients);
			c.start();
			
			System.out.println("Accepted a new connection from "
					+ s.getInetAddress());
		}
	}

}

class ClientHandler extends Thread {

	private ObjectInputStream input;
	private List<ObjectOutputStream> clients;
	public List<PaintObject> drawings;

	public ClientHandler(ObjectInputStream input,
			List<ObjectOutputStream> clients) {
		this.input = input;
		this.clients = clients;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (true) {
			
			// TODO 4: Read a String from the client
			try {
				Server.drawings = (Vector<PaintObject>)input.readObject();
			} catch (ClassNotFoundException e) {
				// This one is probably a bug
				e.printStackTrace();
				this.cleanUp();
				return;
			} catch (IOException e) {
				/* Client left -- clean up and let the thread die */
				this.cleanUp();
				return;
			}

			this.writeVectorToClients(Server.drawings);
		}

	}

	private void cleanUp() {
		try{
			this.input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeVectorToClients(Vector<PaintObject> drawings) {
		// TODO 5: Send a string to all clients in the client list
		synchronized(clients) {
			Set<ObjectOutputStream> closed = new HashSet<ObjectOutputStream>();
			for (ObjectOutputStream client : clients) {
				try {
					client.writeObject(drawings);
					client.reset();
				} catch (IOException e) {
					//If they aren't there the socket was closed and they need to be removed
					closed.add(client);
				}
			}
			//Remove the closed connections from the list
			clients.removeAll(closed);
		}
	}
}
