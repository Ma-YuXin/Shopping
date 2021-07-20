package myx.ShoppingClient.Connection;
//此类用于和服务端进行连接

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import myx.ShoppingClient.Thread.ClientConServerThread;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.User;
import myx.ShoppingClient.Tools.ManageClientConServerThread;

public class ClientConServer {

	private Socket s;

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

//此方法用于向服务端发送数据
	// 发送的数据关于用户
	public boolean sendLoginInfoToServer(User o) {
		boolean b = false;
		try {
			s = new Socket("127.0.0.1", 6789);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			Message ms = (Message) ois.readObject();
System.out.println(ms.getMesType());
			if (ms.getMesType().equals("1") || ms.getMesType().equals("10")) {
				b = true;
				// if (o.getMessage().getMesType().equals("7")) {
				if (ms.getMesType().equals("1")) {
					ClientConServerThread ccst = new ClientConServerThread(s);
					ccst.start();
					ManageClientConServerThread.addClientConServerThread(o.getId(), ccst);
				}
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
