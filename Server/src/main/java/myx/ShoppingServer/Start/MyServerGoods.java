package myx.ShoppingServer.Start;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import myx.ShoppingClient.Model.Goods;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingServer.Database.GoodsAdd;
import myx.ShoppingServer.Thread.goodConClientThread;

//此类和 Myserver做用相同，用来接受货物的数据
public class MyServerGoods extends Thread {
	ServerSocket ss;

	public void run() {
		try {

			ss = new ServerSocket(5678);
			System.out.println("  等待连接  ");
			while (true) {
				Socket s = ss.accept();
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

				Goods u = (Goods) ois.readObject();
				if (u.getMessage().getMesType().equals("7")) {

				} else if (u.getMessage().getMesType().equals("6")) {
					if (GoodsAdd.quary(u.getId())) {
						m.setMesType("2");
						oos.writeObject(m);
					} else {
						GoodsAdd.add(u, u.getCategory());
						m.setMesType("1");
						oos.writeObject(m);
					}
				} else if (u.getMessage().getMesType().equals("8")) {

					goodConClientThread gcct = new goodConClientThread(s);
					gcct.start();
					m.setMesType("1");
					oos.writeObject(m);
				} else if (u.getMessage().getMesType().equals("9")) {
					System.out.println("接收到数据");
					GoodsAdd.update(u);
					System.out.println("成功上传数据库");
					m.setMesType("1");
					oos.writeObject(m);
				}

			} // while 循环中的}

		} catch (Exception e) {
			//e.printStackTrace();
			// TODO: handle exception
		} finally {

		}

	}

}
