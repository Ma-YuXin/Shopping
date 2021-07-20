package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Tools.ManageOrder;

import javax.swing.*;
import java.awt.*;

public class statistics {
	JFrame jf;
	String userid;

	public void init(String userid) {
		jf = new JFrame();
		jf.setSize(600, 400);
		jf.setLocationRelativeTo(null);
		jf.setTitle("统计反馈");
		ManageOrder.setList(userid);
		JPanel jp = new JPanel(new GridLayout(50, 1, 4, 4));
		JLabel[] jlb = new JLabel[ManageOrder.list.size()];

		for (int i = 0; i < ManageOrder.list.size(); i++) {
			jlb[i] = new JLabel("您在:" + ManageOrder.list.get(i).getBuydate() + "向ID为"
					+ ManageOrder.list.get(i).getBuyerUserId() + "的用户售出名为:" + ManageOrder.list.get(i).getName()
					+ "\r\n的商品其ID为" + ManageOrder.list.get(i).getGoodsid());

			jp.add(jlb[i]);
		}

		JScrollPane jsp = new JScrollPane(jp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jf.add(jsp);
		jf.setVisible(true);

	}

	public statistics(String userid) {
		this.userid = userid;
	}
}
