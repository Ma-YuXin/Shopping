package myx.ShoppingServer.Thread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import myx.ShoppingServer.Database.MessageAdd;
import myx.ShoppingClient.Model.Message;

public class OffLine extends Thread {
	String mes;

	public OffLine(String mes) {
		this.mes = mes;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			try {

				ManageClientThread.getClientThread(mes).s.sendUrgentData(0);
				sleep(300);

				List<Message> list = new ArrayList<Message>();
				list = (List<Message>) MessageAdd.get(mes);
				for (Message m : list) {
					System.out.println(m.getSender() + " 对 " + m.getGetter() + "说:" + m.getCon());
					SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());

					try {
						ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);
						MessageAdd.delect(m);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				// interrupt();
				break;

			} catch (Exception e) {
				continue;
			}

		}
	}
}