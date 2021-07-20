package myx.ShoppingServer.Start;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.Orders;
import myx.ShoppingServer.Database.OrderAdd;
import myx.ShoppingServer.Thread.OrderConClientThread;

//此类和 Myserver做用相同，用来接受订单的数据
public class MyServerOrder extends Thread {

	ServerSocket ss;

	public void run() {
		try {

			System.out.println("  等待连接  ");
			ss = new ServerSocket(9678);

			while (true) {
				Socket s = ss.accept();
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

				Orders u = (Orders) ois.readObject();
				if (u.getMessage().getMesType().equals("6")) {
					System.out.println("成功接收到订单信息");
					OrderAdd.add(u);
					m.setMesType("1");
					oos.writeObject(m);
				} else if (u.getMessage().getMesType().equals("7")) {
					m.setMesType("1");
					oos.writeObject(m);
				} else if (u.getMessage().getMesType().equals("8")) {
					OrderConClientThread occt = new OrderConClientThread(s);
					occt.start();
					m.setMesType("1");
					oos.writeObject(m);
				} else if (u.getMessage().getMesType().equals("9")) {

				}

			} // while 循环中的}

		} catch (Exception e) {
			//e.printStackTrace();
			// TODO: handle exception
		} finally {

		}

	}

}
