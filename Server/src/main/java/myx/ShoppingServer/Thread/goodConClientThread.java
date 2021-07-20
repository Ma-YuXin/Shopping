package myx.ShoppingServer.Thread;

import myx.ShoppingClient.Model.Message;
import myx.ShoppingServer.Database.GoodsAdd;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



//此类用于保存从客户端传来的socket，
//并开启线程用于把socket，传给信息接收者
public class goodConClientThread extends Thread {

	Socket s;

	public goodConClientThread(Socket s) {

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
