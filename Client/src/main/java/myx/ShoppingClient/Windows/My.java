package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Model.Goods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class My {
	JFrame jf;

	public void init(Goods g) {
		jf = new JFrame();
		jf.setSize(900, 900);
		jf.setLocationRelativeTo(null);
		jf.setTitle("商品信息");
		jf.setLayout(new GridLayout(10, 1));

		if (g.getImgs() == null) {
			g.setImgs("D:\\Application\\java-2020-03\\WorkSpace\\按钮\\2.jpg");
		}
		JPanel jp19 = new JPanel();
		JLabel l = new JLabel();
		ImageIcon icon = new ImageIcon(g.getImgs());
		icon.setImage(icon.getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT));
		l.setIcon(icon);
		l.setBounds(0, 0, 10, 10);
		jp19.add(l);

		JPanel jp1 = new JPanel();
		JTextField jtf = new JTextField(30);
		jtf.setText(g.getId());
		jtf.setEditable(false);
		jp1.add(new JLabel("ID   "));
		jp1.add(jtf);

		JPanel jp10 = new JPanel();
		JTextField jtf10 = new JTextField(30);
		jtf10.setText(g.getSellerid());
		jtf10.setEditable(false);
		jp10.add(new JLabel("卖家ID"));
		jp10.add(jtf10);

		JPanel jp2 = new JPanel();
		JTextField jtf1 = new JTextField(30);
		jtf1.setText(g.getName());
		jtf1.setEditable(false);
		jp2.add(new JLabel("商品名"));
		jp2.add(jtf1);

		JPanel jp3 = new JPanel();
		JTextField jtf2 = new JTextField(30);
		jtf2.setText(g.getPrice() + "");
		jtf2.setEditable(false);
		jp3.add(new JLabel("价格"));
		jp3.add(jtf2);

		JPanel jp5 = new JPanel();
		JTextField jtf4 = new JTextField(30);
		jtf4.setText(g.getStock() + "");
		jtf4.setEditable(false);
		jp5.add(new JLabel("库存"));
		jp5.add(jtf4);

		JPanel jp7 = new JPanel();
		JTextField jtf6 = new JTextField(30);
		jtf6.setText(g.getIntroduce() + "");
		jtf6.setEditable(false);
		jp7.add(new JLabel("介绍"));
		jp7.add(jtf6);

		JPanel jp8 = new JPanel();
		JButton but1 = new JButton("购买");
		but1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showConfirmDialog(null, "您将购买此商品", "请确认", 0);
			}
		});
		jp8.add(but1);

		JPanel jp9 = new JPanel();
		JTextField jtf14 = new JTextField(30);
		jtf14.setText(g.getCategory().getCategoryType());
		jtf14.setEditable(false);
		jp9.add(new JLabel("商品种类  "));
		jp9.add(jtf14);

		jf.add(jp1);
		jf.add(jp10);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp5);
		jf.add(jp7);
		jf.add(jp9);

		// this.add(button2);
		jf.add(jp19);
		jf.add(jp8);
		jf.pack();
		jf.setVisible(true);

	}

}
