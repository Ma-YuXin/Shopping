package myx.ShoppingClient.Windows;

//修改个人信息界面

import myx.ShoppingClient.Model.MessageType;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.User;
import myx.ShoppingClient.Server.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Changeinfo {
	public static JFrame jf = null;

	public void init(String userid) {
		jf = new JFrame();
		jf.setSize(900, 500);
		jf.setLocationRelativeTo(null);
		jf.setTitle("修改个人信息");
		jf.setLayout(new GridLayout(7, 1));
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				MainWindows.jf.setEnabled(true);
				jf.dispose();
			}

		});

		JPanel jp1 = new JPanel();
		JTextField jtf = new JTextField(30);
		jp1.add(new JLabel("ID "));
		jtf.setText(userid);
		jtf.setEditable(false);
		jp1.add(jtf);

		JPanel jp9 = new JPanel();
		JPasswordField jtf9 = new JPasswordField(30);
		jp9.add(new JLabel("密码 "));
		jp9.add(jtf9);

		JPanel jp2 = new JPanel();
		JTextField jtf1 = new JTextField(30);
		jp2.add(new JLabel("新姓名"));
		jp2.add(jtf1);

		JPanel jp3 = new JPanel();
		JTextField jtf2 = new JTextField(30);
		jp3.add(new JLabel("新电话"));
		jp3.add(jtf2);

		JPanel jp6 = new JPanel();
		JTextField jtf5 = new JTextField(30);
		jp6.add(new JLabel("新昵称"));
		jp6.add(jtf5);

		JPanel jp7 = new JPanel();
		JTextField jtf6 = new JTextField(30);
		jp7.add(new JLabel("新地址"));
		jp7.add(jtf6);

		JButton button = new JButton("确认");
		button.setSize(200, 100);
		button.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					User user = new User();
					user.setId(jtf.getText());
					user.setName(jtf1.getText());
					user.setNickname(jtf5.getText());
					user.setPassword(new String(jtf9.getPassword()));
					user.setPhoneNumber(jtf2.getText());
					user.setAddress(jtf6.getText());
					Message m = new Message();
					m.setMesType(MessageType.change);
					user.setMessage(m);
					int n = JOptionPane.showConfirmDialog(null,
							"您将把名字改为:" + jtf1.getText() + "\n您将把电话改为:" + jtf2.getText() + "\n您将把昵称改为:" + jtf5.getText(),
							"请确认", 0);
					if (n == JOptionPane.YES_OPTION) {
						if (ClientUser.checkUser(user)) {
							JOptionPane.showConfirmDialog(null, "恭喜您修改成功", "请确认", 0);
							MainWindows.jf.setEnabled(true);
							jf.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "对不起，您修改失败", "发生错误", JOptionPane.ERROR_MESSAGE);
							MainWindows.jf.setEnabled(true);
						}
					}

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		JButton button2 = new JButton("取消");
		button2.setSize(200, 100);
		button2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				jf.dispose();
			}
		});
		JPanel jp8 = new JPanel();
		jp8.add(button);
		jp8.add(button2);

		jf.add(jp1);
		jf.add(jp9);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp6);
		jf.add(jp7);
		jf.add(jp8);

		jf.pack();
		jf.setVisible(true);
	}

}
