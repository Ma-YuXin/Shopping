package myx.ShoppingClient.Connection;

import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Thread.OrderConServerThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//和clientconserver作用相同，不过发送的是订单的数据
public class OrderConServer {
	public static Socket s;

	public boolean sendOrdersToServer(Object o) {
		boolean b = false;
		try {

			s = new Socket("127.0.0.1", 9678);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			Message ms = (Message) ois.readObject();

			if (ms.getMesType().equals("1")) {
				OrderConServerThread ocst = new OrderConServerThread(s);
				ocst.start();
				b = true;
			} else {

				s.close();
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
		return b;
	}

}
