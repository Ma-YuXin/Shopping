package myx.ShoppingClient.Windows;

import myx.ShoppingClient.Model.Goods;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.MessageType;
import myx.ShoppingClient.Model.User;
import myx.ShoppingClient.Server.ClientUser;
import myx.ShoppingClient.Tools.ManageClientConServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Objects;

public class Panel_main {
	String userid;

	public JPanel panel() {

		JPanel pl21 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel o1 = new JPanel();

		// JPanel jta = new JPanel();
		// jta.setSize(10, 10);
		JButton bu1 = new JButton("联系卖家");
		JButton bu2 = new JButton("详细信息");
		JLabel l = new JLabel();
		ImageIcon icon = new ImageIcon(Objects.requireNonNull(Panel_main.class.getClassLoader().getResource("wrong.png")));
		icon.setImage(icon.getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT));
		l.setIcon(icon);
		l.setBounds(0, 0, 10, 10);
		p1.add(l);

		o1.add(bu1);
		o1.add(bu2);
		Box vbox1 = Box.createVerticalBox();
		vbox1.add(p1);
		vbox1.add(o1);
		pl21.add(vbox1);
		return pl21;
	}

	public JPanel panel(Goods g, String userid) {
		this.userid = userid;
		JPanel pl21 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel o1 = new JPanel();

		// JPanel jta = new JPanel();
		// jta.setSize(10, 10);
		JButton bu1 = new JButton("联系卖家");
		JButton bu2 = new JButton("详细信息");

		bu2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Detial d = new Detial(userid);
				d.init(g);
			}

		});

		bu1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
//				ManageNewsList.getFriendList(userid).list
//						.add(new JLabel(g.getSellerid(), new ImageIcon("images/but.jpg"), JLabel.LEFT));
				User u = new User();
				u.setId(userid);
				Message m = new Message();
				m.setMesType(MessageType.message_get_onLineFriend);
				m.setSender(userid);
				m.setGetter(g.getSellerid());
				u.setMessage(m);
				if (ClientUser.checkUser(u)) {
					System.out.println("成功将添加信息传给服务端");
				}
				User u1 = new User();
				u1.setId(g.getSellerid());
				Message m1 = new Message();
				m1.setMesType(MessageType.message_get_onLineFriend);
				m1.setSender(g.getSellerid());
				m1.setGetter(userid);
				u1.setMessage(m1);
				if (ClientUser.checkUser(u1)) {
					System.out.println("成功将添加信息传给服务端");
				}
				Message i = new Message();
				i.setMesType(MessageType.message_ret_onLineFriend);// 消息列表
				i.setSender(userid);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread.getClientConServerThread(userid).getS().getOutputStream());
					oos.writeObject(i);
					System.out.println("成功写出信息类型" + i.getMesType());

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showConfirmDialog(null, "您成功将用户" + g.getSellerid() + "添加至消息列表", "请确认", 0);
			}

		});

		JLabel l = new JLabel();
		ImageIcon icon = new ImageIcon(g.getImgs());
		icon.setImage(icon.getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT));
		l.setIcon(icon);
		l.setBounds(0, 0, 10, 10);
		p1.add(l);
		// p1.add(jta);
		o1.add(bu1);
		o1.add(bu2);
		Box vbox1 = Box.createVerticalBox();
		vbox1.add(p1);
		vbox1.add(o1);
		pl21.add(vbox1);
		return pl21;
	}
}