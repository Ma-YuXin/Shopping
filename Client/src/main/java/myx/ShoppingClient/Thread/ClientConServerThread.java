
package myx.ShoppingClient.Thread;

import myx.ShoppingClient.Database.MessageAdd;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.MessageType;
import myx.ShoppingClient.Tools.ManageChat;
import myx.ShoppingClient.Tools.ManageNewsList;
import myx.ShoppingClient.Windows.Chat;
import myx.ShoppingClient.Windows.NewsList;

import java.io.ObjectInputStream;
import java.net.Socket;



public class ClientConServerThread extends Thread {

	private Socket s;

	// 管理网络连接，并开始一个线程用于和用户交流
	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {

			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					if (ManageChat.getChat(m.getGetter() + " " + m.getSender()) != null) {
						Chat ch = ManageChat.getChat(m.getGetter() + " " + m.getSender());
						ch.showMessage(m);
					} else {
						MessageAdd.add(m);

					}

				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {

					NewsList newslist = ManageNewsList.getFriendList(m.getGetter());
					newslist.upateList(m);

				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
