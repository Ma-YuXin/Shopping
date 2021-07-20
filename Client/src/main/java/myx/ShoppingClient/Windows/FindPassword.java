package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.MessageType;
import myx.ShoppingClient.Model.User;
import myx.ShoppingClient.Server.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FindPassword {
	/**
	 * 
	 */
	public static JFrame jf = null;

	public void init() {

		jf = new JFrame();
		jf.setSize(900, 500);
		jf.setLocationRelativeTo(null);
		jf.setTitle("找回密码");
		jf.setLayout(new GridLayout(7, 1));
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				jf.dispose();
			}

		});

		JPanel jp1 = new JPanel();
		JTextField jtf = new JTextField(30);
		jp1.add(new JLabel("ID "));
		jp1.add(jtf);

		JPanel jp2 = new JPanel();
		JTextField jtf1 = new JTextField(30);
		jp2.add(new JLabel("姓名"));
		jp2.add(jtf1);

		JPanel jp3 = new JPanel();
		JTextField jtf2 = new JTextField(30);
		jp3.add(new JLabel("电话"));
		jp3.add(jtf2);

		JPanel jp6 = new JPanel();
		JPasswordField jtf5 = new JPasswordField(30);
		jp6.add(new JLabel("新密码"));
		jp6.add(jtf5);

		JPanel jp7 = new JPanel();
		JPasswordField jtf6 = new JPasswordField(30);
		jp7.add(new JLabel("确认新密码"));
		jp7.add(jtf6);

		JButton button = new JButton("确认");
		button.setSize(200, 100);
		button.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					User user = new User();
					user.setId(jtf.getText());
					user.setName(jtf1.getText());
					user.setPhoneNumber(jtf2.getText());
					user.setPassword(new String(jtf6.getPassword()));
					Message m = new Message();
					m.setMesType(MessageType.checkid);
					user.setMessage(m);

					if (new String(jtf5.getPassword()).length() < 6 || new String(jtf6.getPassword()).length() < 6) {
						JOptionPane.showMessageDialog(null, "您输入的密码长度小于6位", "发生错误", JOptionPane.ERROR_MESSAGE);
					} else if (new String(jtf5.getPassword()).equals(new String(jtf6.getPassword()))) {
						int n = JOptionPane.showConfirmDialog(null, "您的密码将修改为:" + new String(jtf6.getPassword()), "请确认",
								0);
						if (n == JOptionPane.YES_OPTION) {
							if (ClientUser.User(user)) {
								JOptionPane.showConfirmDialog(null, "恭喜您，修改密码成功", "请确认", 0);

								jf.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "对不起，修改失败!\n请检查个人信息是否准确！", "发生错误",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					} else if (!new String(jtf5.getPassword()).equals(new String(jtf6.getPassword()))) {
						JOptionPane.showMessageDialog(null, "您两次输入的密码不一致", "发生错误", JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		JPanel jp8 = new JPanel();
		jp8.add(button);

		jf.add(jp1);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp6);
		jf.add(jp7);
		jf.add(jp8);

		jf.pack();
		jf.setVisible(true);
	}
}