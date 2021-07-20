package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Model.Goods;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.Orders;
import myx.ShoppingClient.Server.ClientOrder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Detial {
	JFrame jf;
	String userid;

	public Detial(String userid) {
		this.userid = userid;
	}

	public void init(Goods g) {
		jf = new JFrame();
		jf.setSize(400, 700);
		jf.setLocationRelativeTo(null);
		jf.setTitle("商品信息");

		if (g.getImgs() == null) {
			g.setImgs("images\\wrong.png");
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

		JPanel jp17 = new JPanel();
		jp17.setLayout(new GridLayout(2, 1));
		JTextArea jtf61 = new JTextArea(10, 10);
		jtf61.setText(g.getCom());
		jtf61.setEditable(false);
		JScrollPane scroll = new JScrollPane(jtf61);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jp17.add(new JLabel("评论"));
		jp17.add(scroll);

		JPanel jp8 = new JPanel();
		JButton but1 = new JButton("购买");
		but1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int n = JOptionPane.showConfirmDialog(null, "您将购买此商品", "请确认", 0);
				if (n == JOptionPane.YES_OPTION) {
					Orders o = new Orders();
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String s = sdf.format(now);
					o.setSellerUserId(g.getSellerid());
					o.setBuydate(s);
					o.setName(g.getName());
					o.setGoodsid(g.getId());
					o.setBuyerUserId(userid);
					Message m = new Message();
					m.setMesType("6");
					o.setMessage(m);
					if (ClientOrder.order(o)) {
						JOptionPane.showConfirmDialog(null, "恭喜您，购买成功", "请确认", 0);
						Com.list.add(new JLabel("商品名:" + g.getName() + "  商品ID:" + g.getId()));
						jf.dispose();
					}

				}

			}
		});
		jp8.add(but1);

		JPanel jp9 = new JPanel();
		JTextField jtf14 = new JTextField(30);
		jtf14.setText(g.getCategory().getCategoryType());
		jtf14.setEditable(false);
		jp9.add(new JLabel("商品种类  "));
		jp9.add(jtf14);

		JPanel jp20 = new JPanel();
		Box vbox = Box.createVerticalBox();
		vbox.add(jp19);
		vbox.add(jp1);
		vbox.add(jp10);
		vbox.add(jp2);
		vbox.add(jp3);
		vbox.add(jp5);
		vbox.add(jp7);
		vbox.add(jp9);
		vbox.add(jp17);
		vbox.add(jp8);
		jp20.add(vbox);
		JScrollPane jsp = new JScrollPane(jp20, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jf.add(jsp);

		// this.add(button2);

		jf.pack();
		jf.setVisible(true);

	}

}
