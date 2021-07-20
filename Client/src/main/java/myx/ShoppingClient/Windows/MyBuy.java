package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Tools.ManageOrder;

import javax.swing.*;
import java.awt.*;

public class MyBuy {
	JFrame jf;
	String userid;

	public void init(String userid) {
		jf = new JFrame();
		jf.setSize(600, 400);
		jf.setLocationRelativeTo(null);
		jf.setTitle("我购买的");
		ManageOrder.setList(userid);
		JPanel jp = new JPanel(new GridLayout(50, 1, 4, 4));
		JLabel[] jlb = new JLabel[ManageOrder.buylist.size()];

		for (int i = 0; i < ManageOrder.buylist.size(); i++) {
			jlb[i] = new JLabel("您在:" + ManageOrder.buylist.get(i).getBuydate() + "向ID为"
					+ ManageOrder.buylist.get(i).getSellerUserId() + "的用户购买名为:" + ManageOrder.buylist.get(i).getName()
					+ "\r\n的商品其ID为" + ManageOrder.buylist.get(i).getGoodsid());

			jp.add(jlb[i]);
		}

		JScrollPane jsp = new JScrollPane(jp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jf.add(jsp);
		jf.setVisible(true);

	}

	public MyBuy(String userid) {
		super();
		this.userid = userid;
	}
}
