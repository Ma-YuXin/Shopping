package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.MessageType;
import myx.ShoppingClient.Tools.ManageChat;
import myx.ShoppingClient.Tools.ManageClientConServerThread;

import javax.swing.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.util.Date;



//聊天界面
public class Chat {
	JFrame jf;
	JTextArea jta;
	JTextField jtf;
	JButton but;
	JPanel jp;
	String userid;
	String friendid;

	public Chat(String userid, String friendid) {

		this.userid = userid;
		this.friendid = friendid;
	}

	public Chat(String userid) {

		this.userid = userid;
	}

	public Chat() {

	}

	public void init(String userid, String friendid) {

		jf = new JFrame();
		jf.setTitle("您正在和" + friendid + "聊天");
		jta = new JTextArea();
		jta.setEditable(false);
		jtf = new JTextField(30);
		but = new JButton("发送");

		but.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				Message mes = new Message();
				mes.setMesType(MessageType.message_comm_mes);
				mes.setSender(userid);
				mes.setGetter(friendid);
				mes.setCon(jtf.getText());
				mes.setSendTime(new Date().toString());

				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread.getClientConServerThread(userid).getS().getOutputStream());

					oos.writeObject(mes);
					jta.append("你对" + mes.getGetter() + "说" + mes.getCon() + "\r\n");
					jtf.setText("");

				} catch (Exception e1) {
					e1.printStackTrace();
					// TODO: handle exception
				}

			}

		});
		but.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Message mes = new Message();
					mes.setMesType(MessageType.message_comm_mes);
					mes.setSender(userid);
					mes.setGetter(friendid);
					mes.setCon(jtf.getText());
					mes.setSendTime(new Date().toString());
					try {
						ObjectOutputStream oos = new ObjectOutputStream(
								ManageClientConServerThread.getClientConServerThread(userid).getS().getOutputStream());

						oos.writeObject(mes);
						jta.append("你对" + mes.getGetter() + "说" + mes.getCon() + "\r\n");
						jtf.setText("");

					} catch (Exception e1) {
						e1.printStackTrace();
						// TODO: handle exception
					}
				}
			}
		});
		jp = new JPanel();
		jp.add(jtf);
		jp.add(but);

		JScrollPane text = new JScrollPane(jta);

		jf.addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ManageChat.delete(userid + " " + friendid);
				// ManageChat.show();
				jf.dispose();
			}
		});

		jf.add(text, "Center");
		jf.add(jp, "South");
		jf.setSize(400, 400);

		jf.setVisible(true);
	}

	public void showMessage(Message m) {
		String info = m.getSender() + " 对你说:" + m.getCon() + "\r\n";
		jta.append(info);
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

}
