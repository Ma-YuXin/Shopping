package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Model.Goods;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Server.ClientGoods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Com {
	JFrame jf;
	String userid;
	int i;

	public Com(String userid) {
		super();
		this.userid = userid;
	}

	static List<JLabel> list = new ArrayList<JLabel>();

	public void init(String userid) {
		jf = new JFrame();
		jf.setSize(400, 400);
		jf.setLocationRelativeTo(null);
		jf.setTitle("评论");
		JPanel jzp = new JPanel(new GridLayout(list.size(), 1));

		for (i = 0; i < list.size(); i++) {
			JPanel jp = new JPanel();
			int index1 = list.get(i).getText().indexOf("商品ID:");
			String str = list.get(i).getText().substring(index1 + 5);
			JButton but = new JButton("确定对ID为" + str + "的商品进行评论");
			JTextField jtf = new JTextField(15);

			GridBagLayout gbl = new GridBagLayout();
			GridBagConstraints cons = null;

			cons = new GridBagConstraints();
			cons.gridwidth = GridBagConstraints.REMAINDER;
			gbl.addLayoutComponent(list.get(i), cons);
			cons = new GridBagConstraints();
			cons.gridwidth = GridBagConstraints.RELATIVE;
			cons.fill = GridBagConstraints.BOTH;
			gbl.addLayoutComponent(jtf, cons);

			cons.gridwidth = GridBagConstraints.REMAINDER;
			gbl.addLayoutComponent(but, cons);

			jp.setLayout(gbl);
			jp.add(list.get(i));
			jp.add(jtf);
			jp.add(but);
			jzp.add(jp);
			but.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					Goods g = new Goods();
					Message message = new Message();
					message.setMesType("9");
					g.setCom("ID为" + userid + "的用户评论说：" + jtf.getText());
					int index1 = but.getText().indexOf("确定对ID为");
					int index2 = but.getText().indexOf("的商品进行评论");
					String str = but.getText().substring(index1 + 6, index2);
					System.out.println(str);
					g.setId(str);
					g.setMessage(message);
					if (ClientGoods.goods(g)) {
						JOptionPane.showConfirmDialog(null, "恭喜您，评论成功", "请确认", 0);
						jtf.setText("");
					}
				}
			});
		}

		JScrollPane jsp = new JScrollPane(jzp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jf.add(jsp);
		jf.setVisible(true);
	}

}
