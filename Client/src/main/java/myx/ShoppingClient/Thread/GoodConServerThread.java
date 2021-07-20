package myx.ShoppingClient.Thread;

import myx.ShoppingClient.Model.Goods;
import myx.ShoppingClient.Tools.ManageGoods;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GoodConServerThread extends Thread {

	private Socket s;

	// 管理网络连接，并开始一个线程用于和用户交流
	// 目前只实现了在线交流，离线交流尚未实现
	public GoodConServerThread(Socket s) {
		this.s = s;
	}

	@SuppressWarnings("unchecked")
	public void run() {

		List<Goods> list = new ArrayList<Goods>();
		while (true) {

			try {

				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

				list = (List<Goods>) ois.readObject();

				ManageGoods.listall = list;
				System.out.println("得到商品");
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
