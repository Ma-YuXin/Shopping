package myx.ShoppingClient.Thread;

import myx.ShoppingClient.Model.Orders;
import myx.ShoppingClient.Tools.ManageOrder;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class OrderConServerThread extends Thread {
	private Socket s;

	@SuppressWarnings("unchecked")
	public void run() {

		List<Orders> list = new ArrayList<Orders>();
		while (true) {

			try {

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				list = (List<Orders>) ois.readObject();
				ManageOrder.listall = list;

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	public OrderConServerThread(Socket s) {
		this.s = s;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
