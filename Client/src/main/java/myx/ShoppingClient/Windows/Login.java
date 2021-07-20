package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Connection.GoodsConServer;
import myx.ShoppingClient.Connection.OrderConServer;
import myx.ShoppingClient.Model.*;
import myx.ShoppingClient.Server.ClientGoods;
import myx.ShoppingClient.Server.ClientOrder;
import myx.ShoppingClient.Server.ClientUser;
import myx.ShoppingClient.Tools.ManageClientConServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

//聊天界面
public class Login {

	public static JFrame jf;

	public void init() {
		jf = new JFrame("login");
		jf.setSize(500, 800);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jp1 = new JPanel();
		JTextField jtf = new JTextField(30);
		jp1.add(new JLabel("用户名"));
		jp1.add(jtf);

		JPanel jp2 = new JPanel();
		JPasswordField jpf = new JPasswordField(20);
		JButton jb1 = new JButton("找回密码");
		jp2.add(new JLabel("密码"));
		jp2.add(jpf);
		jp2.add(jb1);

		jb1.addMouseListener(new MouseAdapter() {
			// 弹出注册界面
			@Override
			public void mouseClicked(MouseEvent e) {
				FindPassword fp = new FindPassword();
				fp.init();
				jf.dispose();
			}

		});

		JPanel jp3 = new JPanel();
		JButton jb2 = new JButton("登录");
		JButton jb3 = new JButton("注册");
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp3.add(jb2);
		jp3.add(jb3);
		//
		jb2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					if (jtf.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "您输入的用户名为空", "发生错误", JOptionPane.ERROR_MESSAGE);
					} else if (new String(jpf.getPassword()).length() == 0) {
						JOptionPane.showMessageDialog(null, "您输入的密码为空", "发生错误", JOptionPane.ERROR_MESSAGE);
					} else {

						User u = new User();
						u.setId(jtf.getText().trim());
						u.setPassword(new String(jpf.getPassword()));
						Message m1 = new Message();
						m1.setMesType(MessageType.check);
						u.setMessage(m1);

						// 开启一个货物线程
						Goods good = new Goods();
						Message mes = new Message();
						mes.setMesType("8");
						good.setMessage(mes);

						if (ClientGoods.goods(good)) {
							System.out.println("成功与货物端链接");
						}

						Message mes1 = new Message();
						mes1.setMesType("8");// 返回所有货物

						try {
							ObjectOutputStream oos = new ObjectOutputStream(GoodsConServer.s.getOutputStream());
							oos.writeObject(mes1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();

						}
						Orders o = new Orders();
						Message m = new Message();
						m.setMesType("8");
						o.setMessage(m);
						if (ClientOrder.order(o)) {
							System.out.println("成功与订单端链接");
						}
						Message i = new Message();
						i.setMesType("8");// 返回所有货物

						try {
							ObjectOutputStream oos = new ObjectOutputStream(OrderConServer.s.getOutputStream());
							oos.writeObject(i);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						if (ClientUser.checkUser(u)) {
							try {
								MainWindows mh = new MainWindows(jtf.getText(), u.getPassword());
								mh.init();
								jf.dispose();

								Message i1 = new Message();
								i1.setMesType(MessageType.message_ret_onLineFriend);// 消息列表
								i1.setSender(u.getId());
								try {
									ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread
											.getClientConServerThread(u.getId()).getS().getOutputStream());
									oos.writeObject(i1);
									System.out.println("成功写出信息类型" + i1.getMesType());

								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							} catch (Exception e1) {
								e1.printStackTrace();
								// TODO: handle exception
							}
						} else {
							JOptionPane.showMessageDialog(null, "您输入的用户不存在或密码不正确", "发生错误", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		jb2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (jtf.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的用户名为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (new String(jpf.getPassword()).length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的密码为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else {

					User u = new User();
					u.setId(jtf.getText().trim());
					u.setPassword(new String(jpf.getPassword()));
					Message m1 = new Message();
					m1.setMesType(MessageType.check);
					u.setMessage(m1);

					// 开启一个货物线程
					Goods good = new Goods();
					Message mes = new Message();
					mes.setMesType("8");
					good.setMessage(mes);

					if (ClientGoods.goods(good)) {
						System.out.println("成功与货物端链接");
					}

					Message mes1 = new Message();
					mes1.setMesType("8");// 返回所有货物

					try {
						ObjectOutputStream oos = new ObjectOutputStream(GoodsConServer.s.getOutputStream());
						oos.writeObject(mes1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}
					Orders o = new Orders();
					Message m = new Message();
					m.setMesType("8");
					o.setMessage(m);
					if (ClientOrder.order(o)) {
						System.out.println("成功与订单端链接");
					}
					Message i = new Message();
					i.setMesType("8");// 返回所有货物

					try {
						ObjectOutputStream oos = new ObjectOutputStream(OrderConServer.s.getOutputStream());
						oos.writeObject(i);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (ClientUser.checkUser(u)) {
						try {
							MainWindows mh = new MainWindows(jtf.getText(), u.getPassword());
							mh.init();
							jf.dispose();

							Message i1 = new Message();
							i1.setMesType(MessageType.message_ret_onLineFriend);// 消息列表
							i1.setSender(u.getId());
							try {
								ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread
										.getClientConServerThread(u.getId()).getS().getOutputStream());
								oos.writeObject(i1);
								System.out.println("成功写出信息类型" + i1.getMesType());

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} catch (Exception e1) {
							e1.printStackTrace();
							// TODO: handle exception
						}
					} else {
						JOptionPane.showMessageDialog(null, "您输入的用户不存在或密码不正确", "发生错误", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		jb3.addMouseListener(new MouseAdapter() {
//弹出注册界面
			@Override
			public void mouseClicked(MouseEvent e) {
				Register r = new Register();
				r.init();
				jf.setEnabled(false);
			}

		});

		Box vBox = Box.createVerticalBox();
		vBox.add(jp1);
		vBox.add(jp2);
		vBox.add(jp3);

		jf.setContentPane(vBox);
		jf.pack();

		jf.setVisible(true);

	}
}
