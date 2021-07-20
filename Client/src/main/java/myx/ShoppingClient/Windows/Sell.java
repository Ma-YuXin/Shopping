package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Model.Category;
import myx.ShoppingClient.Model.Goods;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.MessageType;
import myx.ShoppingClient.Server.ClientGoods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//发布商品界面
public class Sell {
	public static JFrame jf = null;
	String str = "学习用品";

	public void init(String userid) {
		jf = new JFrame();
		jf.setSize(900, 900);
		jf.setLocationRelativeTo(null);
		jf.setTitle("发布商品");
		jf.setLayout(new GridLayout(10, 1));
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				MainWindows.jf.setEnabled(true);
			}

		});

		JPanel jp1 = new JPanel();
		JTextField jtf = new JTextField(30);
		jp1.add(new JLabel("ID   "));
		jp1.add(jtf);

		JPanel jp10 = new JPanel();
		JTextField jtf10 = new JTextField(30);
		jtf10.setText(userid);
		jtf10.setEditable(false);
		jp10.add(new JLabel("卖家ID"));
		jp10.add(jtf10);

		JPanel jp2 = new JPanel();
		JTextField jtf1 = new JTextField(30);
		jp2.add(new JLabel("商品名"));
		jp2.add(jtf1);

		JPanel jp3 = new JPanel();
		JTextField jtf2 = new JTextField(30);
		jp3.add(new JLabel("价格"));
		jp3.add(jtf2);

		JPanel jp5 = new JPanel();
		JTextField jtf4 = new JTextField(30);
		jp5.add(new JLabel("库存"));
		jp5.add(jtf4);

		JPanel jp6 = new JPanel();
		FileDialog open = new FileDialog(jf, "打开", FileDialog.LOAD);
		JTextField jtf5 = new JTextField(30);
		jp6.add(new JLabel("图片"));
		jp6.add(jtf5);
		JButton but = new JButton("选择");
		jp6.add(but);

		but.addMouseListener(new MouseAdapter() {
			// 将商品添加到数据库
			@Override
			public void mouseClicked(MouseEvent e) {
				open.setVisible(true);
				jtf5.setText(open.getDirectory() + open.getFile());

			}
		});
//		JPanel jp6 = new JPanel();
//		JPanel jpq = new JPanel();
//		JPanel jpp = new JPanel();
//		JTextArea jtf5 = new JTextArea(10, 10);
//		jpq.add(new JLabel("图片"));
//		jpp.add(jtf5);
//		jp6.add(jpq, BorderLayout.SOUTH);
//		jp6.add(jpp, BorderLayout.NORTH);

		JPanel jp7 = new JPanel();
		JTextField jtf6 = new JTextField(30);
		jp7.add(new JLabel("介绍"));
		jp7.add(jtf6);

		JPanel jp9 = new JPanel();
		// JTextField jtf7 = new JTextField(30);
		jp9.add(new JLabel("商品种类"));
		String[] category = new String[] { "学习用品", "日用", "电器", "办公", "服装", "化妆品", "食品", "玩具", "药品", "厨具" };
		final JComboBox<String> com = new JComboBox<String>(category);
		com.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					str = (String) com.getSelectedItem();
				}

			}

		});

		com.setSelectedIndex(0);
		jp9.add(com);

		// jp9.add(jtf7);

		JButton button2 = new JButton("取消");
		button2.setSize(200, 100);
		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MainWindows.jf.setEnabled(true);
				jf.dispose();
			}
		});
		JButton button = new JButton("确认");
		button.setSize(200, 100);
		button.addMouseListener(new MouseAdapter() {
			// 将商品添加到数据库

			public void mouseClicked(MouseEvent e) {
				if (jtf.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的ID为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf10.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的卖家ID为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf1.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的商品名为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf2.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的价格为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf4.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的库存为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf6.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的介绍为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else if (jtf5.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "您输入的图片路径为空", "发生错误", JOptionPane.ERROR_MESSAGE);
				} else {
					int n = JOptionPane.showConfirmDialog(null,
							"您将创建ID为:" + jtf.getText() + "\n名为" + jtf1.getText() + "\n价格为" + jtf2.getText() + "的商品",
							"请确认", 0);
					if (n == JOptionPane.YES_OPTION) {
						Category category = new Category(str);

						Goods good = new Goods();
						good.setSellerid(jtf10.getText());
						good.setImgs(jtf5.getText());
						good.setIntroduce(jtf6.getText());
						good.setName(jtf1.getText());
						good.setPrice(Double.parseDouble(jtf2.getText()));
						good.setStock(Integer.parseInt(jtf4.getText()));
						good.setId(jtf.getText());
						good.setCategory(category);
						Message m = new Message();
						m.setMesType(MessageType.register);
						good.setMessage(m);

						if (ClientGoods.goods(good)) {
							JOptionPane.showConfirmDialog(null, "恭喜您，上传商品成功", "请确认", 0);
							jf.dispose();
							MainWindows.jf.setEnabled(true);
						} else {

							JOptionPane.showMessageDialog(null, "对不起，商品ID已经被占用，上传失败", "发生错误",
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
		jf.add(jp10);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp5);
		jf.add(jp6);
		jf.add(jp7);
		jf.add(jp9);
		jf.add(jp8);
		// this.add(button2);
		jf.pack();
		jf.setVisible(true);
	}

}
