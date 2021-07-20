package myx.ShoppingClient.Windows;


//注册界面

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

public class Register {
	/**
	 *
	 */
	JFrame jf = null;

	public void init() {
		jf = new JFrame();
		jf.setSize(900, 500);
		jf.setLocationRelativeTo(null);
		jf.setTitle("用户注册");
		jf.setLayout(new GridLayout(7, 1));
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Login.jf.setEnabled(true);
			}

		});

		JPanel jp1 = new JPanel();
		JTextField jtf = new JTextField(30);
		jp1.add(new JLabel("ID   "));
		jp1.add(jtf);

		JPanel jp2 = new JPanel();
		JTextField jtf1 = new JTextField(30);
		jp2.add(new JLabel("姓名"));
		jp2.add(jtf1);

		JPanel jp3 = new JPanel();
		JTextField jtf2 = new JTextField(30);
		jp3.add(new JLabel("密码"));
		jp3.add(jtf2);

		JPanel jp5 = new JPanel();
		JTextField jtf4 = new JTextField(30);
		jp5.add(new JLabel("昵称"));
		jp5.add(jtf4);

		JPanel jp6 = new JPanel();
		JTextField jtf5 = new JTextField(30);
		jp6.add(new JLabel("电话"));
		jp6.add(jtf5);

		JPanel jp7 = new JPanel();
		JTextField jtf6 = new JTextField(30);
		jp7.add(new JLabel("住址"));
		jp7.add(jtf6);

		JButton button2 = new JButton("取消");
		button2.setSize(200, 100);
		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Login.jf.setEnabled(true);
				jf.dispose();
			}
		});

		JButton button = new JButton("确认");
		button.setSize(200, 100);
		button.addMouseListener(new MouseAdapter() {
			// 将用户添加到数据库
			@Override
			public void mouseClicked(MouseEvent e) {
				// 服务器学完后将id弹出对话框，看id是否使用

				if (jtf2.getText().length() < 6 || jtf.getText() == null) {
					// password.setVisible(true);
					JOptionPane.showMessageDialog(null, "您输入的密码小于6为或您输入的密码为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf1.getText() == null) {
					JOptionPane.showMessageDialog(null, "您输入的名字为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf.getText() == null) {
					JOptionPane.showMessageDialog(null, "您输入的ID为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf4.getText() == null) {
					JOptionPane.showMessageDialog(null, "您输入的昵称为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf5.getText() == null) {
					JOptionPane.showMessageDialog(null, "您输入的电话为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf6.getText() == null) {
					JOptionPane.showMessageDialog(null, "您输入的地址为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else {

					int n = JOptionPane.showConfirmDialog(null, "您将创建名为:" + jtf1.getText() + "的用户", "请确认", 0);
					if (n == JOptionPane.YES_OPTION) {

						User user = new User();
						user.setId(jtf.getText());
						user.setName(jtf1.getText());
						user.setNickname(jtf4.getText());
						user.setPassword(jtf2.getText());
						user.setPhoneNumber(jtf5.getText());
						user.setAddress(jtf6.getText());
						Message m = new Message();
						m.setMesType(MessageType.register);
						user.setMessage(m);

						if (ClientUser.User(user)) {
							JOptionPane.showConfirmDialog(null, "恭喜您，创建用户成功", "请确认", 0);
							jf.dispose();
							Login.jf.setEnabled(true);
						} else {

							JOptionPane.showMessageDialog(null, "对不起，用户ID已被占用，此用户创建失败", "发生错误",
									JOptionPane.ERROR_MESSAGE);
							Login.jf.setEnabled(true);
						}
					}
				}
			}

		});
		JPanel jp8 = new JPanel();
		jp8.add(button);
		jp8.add(button2);
//		JButton button2 = new JButton("返回");
//		button2.addMouseListener(new MouseAdapter() {
//			// 将用户添加到数据库
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//			}
//
//		});
		jf.add(jp1);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp5);
		jf.add(jp6);
		jf.add(jp7);
		jf.add(jp8);
		// this.add(button2);
		jf.pack();
		jf.setVisible(true);

	}
}