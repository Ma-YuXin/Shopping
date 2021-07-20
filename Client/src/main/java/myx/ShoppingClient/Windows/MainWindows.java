package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Connection.GoodsConServer;
import myx.ShoppingClient.Connection.OrderConServer;
import myx.ShoppingClient.Model.*;
import myx.ShoppingClient.Server.ClientGoods;
import myx.ShoppingClient.Server.ClientOrder;
import myx.ShoppingClient.Tools.ManageClientConServerThread;
import myx.ShoppingClient.Tools.ManageGoods;
import myx.ShoppingClient.Tools.ManageNewsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainWindows {
	JPanel pl23, pl21, pl22, pl24;
	public static JFrame jf = null;
	String userid;
	String password;
	JPanel jpu;
	JLabel lb;
	JButton bu1, bu2;
	JPanel pl2;
	ManageGoods mg;
	List<Goods> list = null;
	JPanel jpz;
	static Page page;

	public MainWindows(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}

	public void init() {

		jf = new JFrame();
		jf.setSize(200, 200);
		jf.setTitle("当前在线用户ID：" + userid);
		jf.setLocationRelativeTo(null);

		Message i1 = new Message();
		i1.setMesType(MessageType.message_ret_onLineFriend);// 消息列表
		i1.setSender(userid);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					ManageClientConServerThread.getClientConServerThread(userid).getS().getOutputStream());
			oos.writeObject(i1);
			System.out.println("成功写出信息类型" + i1.getMesType());

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		NewsList nl = new NewsList(userid);
		ManageNewsList.addFriendList(userid, nl);
		mg = new ManageGoods();

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints cons = null;

		JPanel pl1 = new JPanel();
		pl1.setBounds(0, 0, 100, jf.getWidth());
		pl1.setLayout(gbl);

		JTextField jtf = new JTextField(20);
		JButton but1 = new JButton("搜索");
		JButton but2 = new JButton("修改个人信息");
		JButton but3 = new JButton("发布商品");
		JButton but4 = new JButton("统计销量");

		JPanel jp9 = new JPanel();
		jp9.add(new JLabel("目录"));
		String[] category = new String[] { "全部", "学习用品", "日用", "电器", "办公", "服装", "化妆品", "食品", "玩具", "药品", "厨具" };
		final JComboBox<String> com = new JComboBox<String>(category);

		com.setSelectedIndex(0);
		jp9.add(com);

		cons = new GridBagConstraints();
		cons.gridheight = 2;
		cons.fill = GridBagConstraints.BOTH;
		gbl.addLayoutComponent(jp9, cons);

		cons = new GridBagConstraints();
		cons.gridwidth = GridBagConstraints.RELATIVE;
		cons.fill = GridBagConstraints.BOTH;
		gbl.addLayoutComponent(jtf, cons);

		cons.gridwidth = GridBagConstraints.REMAINDER;

		gbl.addLayoutComponent(but1, cons);

		cons = new GridBagConstraints();
		gbl.addLayoutComponent(but3, cons);

		cons = new GridBagConstraints();
		gbl.addLayoutComponent(but4, cons);

		cons = new GridBagConstraints();
		gbl.addLayoutComponent(but2, cons);

		pl1.add(jp9);
		pl1.add(jtf);
		pl1.add(but1);
		pl1.add(but3);
		pl1.add(but4);
		pl1.add(but2);

		// 中间面板进行设置
		pl2 = new JPanel();
		pl2.setBounds(200, 0, jf.getHeight() - 200, jf.getWidth());
		pl2.setLayout(new GridLayout(2, 2));

		//
		//
		// 分页
		page = new Page();
		page.setTotal(ManageGoods.listall.size());

		if (ManageGoods.getListall().size() % 4 == 0) {
			if (ManageGoods.getListall().size() == 0) {
				page.setEndPage(1);
			} else {
				page.setEndPage(ManageGoods.getListall().size() / 4);
			}
		} else {

			page.setEndPage(ManageGoods.getListall().size() / 4 + 1);
		}
		page.setStartPage(1);
		page.setCurrentPage(1);

		list = ManageGoods.listall;
		show(0, list);

		//
		// 最下部分面板进行设置

		JPanel pl3 = new JPanel();
		pl3.setLayout(new GridLayout(1, 3));
		bu1 = new JButton("上一页");
		lb = new JLabel("当前页：     " + page.getCurrentPage() + "");
		bu2 = new JButton("下一页");
		pl3.add(bu1);
		pl3.add(lb);
		pl3.add(bu2);

		JPanel pl5 = new JPanel();
		JButton bn = new JButton("消息列表");
		JButton bp = new JButton("  评价    ");
		JButton bs = new JButton("  刷新    ");
		JButton bd = new JButton("我购买的");

		pl5.add(bs);
		pl5.add(bn);
		pl5.add(bp);
		pl5.add(bd);

		Box vbox = Box.createVerticalBox();
		vbox.add(pl1);
		vbox.add(pl2);
		vbox.add(pl3);
		vbox.add(pl5);

		jpz = new JPanel();
		jpz.add(vbox);

		jf.addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					ManageClientConServerThread.getClientConServerThread(userid).getS().close();
					ManageClientConServerThread.delete(userid);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jf.add(jpz);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		// 我购买的
		bd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MyBuy mb = new MyBuy(userid);
				mb.init(userid);
			}

		});
		// 刷新
		bs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Message i1 = new Message();
				i1.setMesType(MessageType.message_ret_onLineFriend);// 消息列表
				i1.setSender(userid);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread.getClientConServerThread(userid).getS().getOutputStream());
					oos.writeObject(i1);
					System.out.println("成功写出信息类型" + i1.getMesType());

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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

				MainWindows mh = new MainWindows(userid, password);
				mh.init();
				MainWindows.jf.dispose();
				JOptionPane.showConfirmDialog(null, "恭喜您，刷新成功", "请确认", 0);

			}

		});
		// 消息列表
		bn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Message i1 = new Message();
				i1.setMesType(MessageType.message_ret_onLineFriend);// 消息列表
				i1.setSender(userid);
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread.getClientConServerThread(userid).getS().getOutputStream());
					oos.writeObject(i1);
					System.out.println("成功写出信息类型" + i1.getMesType());

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nl.init(userid);
			}

		});
		// 评价
		bp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Com c = new Com(userid);
				c.init(userid);
			}

		});
		// 统计销量
		but4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				statistics s = new statistics(userid);
				s.init(userid);
			}

		});
		// 上一页
		bu1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (page.getCurrentPage() == 1) {
					JOptionPane.showMessageDialog(null, "当前页为第一页，不可以再往前", "发生错误", JOptionPane.ERROR_MESSAGE);
					page.setCurrentPage(page.getCurrentPage());
					show(0, list);
				} else {
					page.setCurrentPage(page.getCurrentPage() - 1);
				}
				lb.setText("当前页：     " + page.getCurrentPage() + "");
				show(page.getCurrentPage() * 4 - 4, list);
			}

		});
		// 下一页
		bu2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (page.getCurrentPage() == page.getEndPage()) {
					JOptionPane.showMessageDialog(null, "当前页为最后一页，不可以再往后", "发生错误", JOptionPane.ERROR_MESSAGE);

				} else {

					page.setCurrentPage(page.getCurrentPage() + 1);
					lb.setText("当前页：     " + page.getCurrentPage() + "");
					if (page.getCurrentPage() == page.getEndPage()) {
						show(page.getTotal() - 4, list);
					} else {
						show(page.getCurrentPage() * 4 - 4, list);
					}
				}

			}

		});

		// 发布商品
		but3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Sell sell = new Sell();
				sell.init(userid);
				jf.setEnabled(false);

			}

		});
		// 修改个人信息
		but2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Changeinfo mw = new Changeinfo();
				mw.init(userid);
				jf.setEnabled(false);

			}

		});

		// 收索按钮的监听
		but1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				page = new Page();
				mg.setQuary(jtf.getText());
				page.setTotal(ManageGoods.quary.size());
				if (mg.getQuary().size() % 4 == 0) {
					if (mg.getQuary().size() == 0) {
						page.setEndPage(1);
					} else {
						page.setEndPage(mg.getQuary().size() / 4);
					}
				} else {
					page.setEndPage(mg.getQuary().size() / 4 + 1);
				}
				page.setStartPage(1);
				page.setCurrentPage(1);
				list = mg.getQuary();
				show(0, list);
				jtf.setText("");
			}

		});
		// 目录按钮的监听
		com.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (com.getSelectedItem().equals("全部")) {

						page = new Page();
						page.setTotal(ManageGoods.getListall().size());
						if (ManageGoods.getListall().size() % 4 == 0) {
							if (ManageGoods.getListall().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(ManageGoods.getListall().size() / 4);
							}
						} else {
							page.setEndPage(ManageGoods.getListall().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = ManageGoods.getListall();
						show(0, list);
					} else if (com.getSelectedItem().equals("学习用品")) {
						page = new Page();
						mg.setListStudy();
						page.setTotal(ManageGoods.listStudy.size());
						if (mg.getListStudy().size() % 4 == 0) {
							if (mg.getListStudy().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListStudy().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListStudy().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListStudy();
						show(0, list);
					} else if (com.getSelectedItem().equals("日用")) {
						page = new Page();
						mg.setListDaily();
						page.setTotal(mg.getListDaily().size());
						if (mg.getListDaily().size() % 4 == 0) {
							if (mg.getListDaily().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListDaily().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListDaily().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListDaily();
						show(0, list);
					} else if (com.getSelectedItem().equals("电器")) {
						page = new Page();
						mg.setListElectricity();
						page.setTotal(mg.getListElectricity().size());
						if (mg.getListElectricity().size() % 4 == 0) {
							if (mg.getListElectricity().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListElectricity().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListElectricity().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListElectricity();
						show(0, list);
					} else if (com.getSelectedItem().equals("办公")) {
						page = new Page();
						mg.setListWork();

						page.setTotal(mg.getListWork().size());
						if (mg.getListWork().size() % 4 == 0) {
							if (mg.getListWork().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListWork().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListWork().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListWork();
						show(0, list);
					} else if (com.getSelectedItem().equals("服装")) {
						page = new Page();
						mg.setListCloth();

						page.setTotal(mg.getListCloth().size());
						if (mg.getListCloth().size() % 4 == 0) {
							if (mg.getListCloth().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListCloth().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListCloth().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListCloth();
						show(0, list);
					} else if (com.getSelectedItem().equals("化妆品")) {
						page = new Page();
						mg.setListCosmetics();

						page.setTotal(mg.getListCosmetics().size());
						if (mg.getListCosmetics().size() % 4 == 0) {
							if (mg.getListCosmetics().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListCosmetics().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListCosmetics().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListCosmetics();
						show(0, list);
					} else if (com.getSelectedItem().equals("食品")) {
						page = new Page();
						mg.setListFood();

						page.setTotal(mg.getListFood().size());
						if (mg.getListFood().size() % 4 == 0) {
							if (mg.getListFood().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListFood().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListFood().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListFood();
						show(0, list);
					} else if (com.getSelectedItem().equals("玩具")) {
						page = new Page();
						mg.setListToy();
						page.setTotal(mg.getListToy().size());
						if (mg.getListToy().size() % 4 == 0) {
							if (mg.getListToy().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListToy().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListToy().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListToy();
						show(0, list);
					} else if (com.getSelectedItem().equals("药品")) {
						page = new Page();
						mg.setListMedicine();
						page.setTotal(mg.getListMedicine().size());
						if (mg.getListMedicine().size() % 4 == 0) {
							if (mg.getListMedicine().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListMedicine().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListMedicine().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListMedicine();
						show(0, list);
					} else if (com.getSelectedItem().equals("厨具")) {
						page = new Page();
						mg.setListCook();
						page.setTotal(mg.getListCook().size());
						if (mg.getListCook().size() % 4 == 0) {
							if (mg.getListCook().size() == 0) {
								page.setEndPage(1);
							} else {
								page.setEndPage(mg.getListCook().size() / 4);
							}
						} else {
							page.setEndPage(mg.getListCook().size() / 4 + 1);
						}
						page.setStartPage(1);
						page.setCurrentPage(1);
						list = mg.getListCook();
						show(0, list);
					}
				}

			}

		});

		// bn.add
	}

	public void show(int start, List<Goods> list) {
		int count = pl2.getComponentCount();
		if (count == 0) {
			if (list.size() == 0) {
				pl21 = new Panel_main().panel();
				pl22 = new Panel_main().panel();
				pl23 = new Panel_main().panel();
				pl24 = new Panel_main().panel();

				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
			} else if (list.size() == 1) {
				pl21 = new Panel_main().panel(list.get(0), userid);
				pl22 = new Panel_main().panel();
				pl23 = new Panel_main().panel();
				pl24 = new Panel_main().panel();
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);

			} else if (list.size() == 2) {
				pl21 = new Panel_main().panel(list.get(0), userid);
				pl22 = new Panel_main().panel(list.get(1), userid);
				pl23 = new Panel_main().panel();
				pl24 = new Panel_main().panel();
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
			} else if (list.size() == 3) {
				pl21 = new Panel_main().panel(list.get(0), userid);
				pl22 = new Panel_main().panel(list.get(1), userid);
				pl23 = new Panel_main().panel(list.get(2), userid);
				pl24 = new Panel_main().panel();
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
			} else {
				pl21 = new Panel_main().panel(list.get(start), userid);
				pl22 = new Panel_main().panel(list.get(start + 1), userid);
				pl23 = new Panel_main().panel(list.get(start + 2), userid);
				pl24 = new Panel_main().panel(list.get(start + 3), userid);
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);

			}

		} else {
			if (list.size() == 0) {
				pl2.remove(pl21);
				pl2.remove(pl22);
				pl2.remove(pl23);
				pl2.remove(pl24);

				pl21 = new Panel_main().panel();
				pl22 = new Panel_main().panel();
				pl23 = new Panel_main().panel();
				pl24 = new Panel_main().panel();

				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
				// 验证pl2容器及其组件
				pl2.validate();
				// 重绘组件
				pl2.repaint();

			} else if (list.size() == 1) {
				pl2.remove(pl21);
				pl2.remove(pl22);
				pl2.remove(pl23);
				pl2.remove(pl24);

				pl21 = new Panel_main().panel(list.get(0), userid);
				pl22 = new Panel_main().panel();
				pl23 = new Panel_main().panel();
				pl24 = new Panel_main().panel();
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
				// 验证pl2容器及其组件
				pl2.validate();
				// 重绘组件
				pl2.repaint();

			} else if (list.size() == 2) {
				pl2.remove(pl21);
				pl2.remove(pl22);
				pl2.remove(pl23);
				pl2.remove(pl24);

				pl21 = new Panel_main().panel(list.get(0), userid);
				pl22 = new Panel_main().panel(list.get(1), userid);
				pl23 = new Panel_main().panel();
				pl24 = new Panel_main().panel();
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
				// 验证pl2容器及其组件
				pl2.validate();
				// 重绘组件
				pl2.repaint();

			} else if (list.size() == 3) {
				pl2.remove(pl21);
				pl2.remove(pl22);
				pl2.remove(pl23);
				pl2.remove(pl24);
				pl21 = new Panel_main().panel(list.get(0), userid);
				pl22 = new Panel_main().panel(list.get(1), userid);
				pl23 = new Panel_main().panel(list.get(2), userid);
				pl24 = new Panel_main().panel();
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
				// 验证pl2容器及其组件
				pl2.validate();
				// 重绘组件
				pl2.repaint();

			} else {
				pl2.remove(pl21);
				pl2.remove(pl22);
				pl2.remove(pl23);
				pl2.remove(pl24);
				pl21 = new Panel_main().panel(list.get(start), userid);
				pl22 = new Panel_main().panel(list.get(start + 1), userid);
				pl23 = new Panel_main().panel(list.get(start + 2), userid);
				pl24 = new Panel_main().panel(list.get(start + 3), userid);
				pl2.add(pl21);
				pl2.add(pl22);
				pl2.add(pl23);
				pl2.add(pl24);
				// 验证pl2容器及其组件
				pl2.validate();
				// 重绘组件
				pl2.repaint();
			}

		}

	}

}