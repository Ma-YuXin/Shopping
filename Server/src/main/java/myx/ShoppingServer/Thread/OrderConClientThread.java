package myx.ShoppingServer.Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingServer.Database.OrderAdd;

public class OrderConClientThread extends Thread {
	Socket s;

	public OrderConClientThread(Socket s) {
		this.s = s;
	}

	public void run() {

		while (true) {
			try {

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message mes = (Message) ois.readObject();

				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(OrderAdd.get(mes.getSender()));

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}
}
