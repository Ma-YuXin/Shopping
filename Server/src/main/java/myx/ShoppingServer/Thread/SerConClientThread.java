
package myx.ShoppingServer.Thread;

import myx.ShoppingClient.Database.MessageAdd;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.MessageType;
import myx.ShoppingServer.Database.NewsList;
import myx.ShoppingServer.Util.ManageOffline;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



//此类用于保存从客户端传来的socket，
//并开启线程用于把socket，传给信息接收者
public class SerConClientThread extends Thread {
	OffLine of;
	Socket s;

	public SerConClientThread(Socket s) {
		this.s = s;
	}

	public void run() {

		while (true) {
			try {

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();

				if (m.getMesType().equals("3")) {

					try {
						// 发送测试数据
						ManageClientThread.getClientThread(m.getGetter()).s.sendUrgentData(0);
						sleep(300);
						System.out.println(m.getSender() + " 对 " + m.getGetter() + "说:" + m.getCon());
						SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
						ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);
					} catch (Exception e) {
						MessageAdd.add(m);
						if (ManageOffline.judge(m.getGetter())) {

						} else {
							of = new OffLine(m.getGetter());
							ManageOffline.add(m.getGetter(), of);
							if (!ManageOffline.get(m.getGetter()).isAlive()) {
								of.start();
							}
						}
					}
				} else if (m.getMesType().equals("5")) {

					String res = NewsList.quary(m.getSender());
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					SerConClientThread sc = ManageClientThread.getClientThread(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m2);
					System.out.println("成功写出用户列表");
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}
}
