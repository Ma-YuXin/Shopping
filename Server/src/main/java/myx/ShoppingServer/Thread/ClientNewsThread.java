package myx.ShoppingServer.Thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import myx.ShoppingClient.Model.Message;
import myx.ShoppingServer.Database.GoodsAdd;

public class ClientNewsThread extends Thread {
	Socket s;

	public ClientNewsThread(Socket s) {
		;
		this.s = s;
	}

	public void run() {

		while (true) {
			try {

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message mes = (Message) ois.readObject();

				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(GoodsAdd.get());

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}
}
