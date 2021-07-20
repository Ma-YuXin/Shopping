package myx.ShoppingClient.Windows;


import myx.ShoppingClient.Database.MessageAdd;
import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Tools.ManageChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//好友列表界面/
public class NewsList implements ActionListener, MouseListener {
	JPanel jp;
	JScrollPane jsp;
	JFrame jf;
	String userid;
	String str;

	public static String[] ifRepeat(String[] arr) {
		// 用来记录去除重复之后的数组长度和给临时数组作为下标索引
		int t = 0;
		// 临时数组
		String[] tempArr = new String[arr.length];
		// 遍历原数组
		for (int i = 0; i < arr.length; i++) {
			// 声明一个标记，并每次重置
			boolean isTrue = true;
			// 内层循环将原数组的元素逐个对比
			for (int j = i + 1; j < arr.length; j++) {
				// 如果发现有重复元素，改变标记状态并结束当次内层循环
				if (arr[i] == arr[j]) {
					isTrue = false;
					break;
				}
			}
			// 判断标记是否被改变，如果没被改变就是没有重复元素
			if (isTrue) {
				// 没有元素就将原数组的元素赋给临时数组
				tempArr[t] = arr[i];
				// 走到这里证明当前元素没有重复，那么记录自增
				t++;
			}
		}
		// 声明需要返回的数组，这个才是去重后的数组
		String[] newArr = new String[t];
		// 用arraycopy方法将刚才去重的数组拷贝到新数组并返回
		System.arraycopy(tempArr, 0, newArr, 0, t);
		return newArr;
	}

	public static List<String> getList() {
		return list;
	}

	public static void setList(List<String> list) {
		NewsList.list = list;
	}

	static List<String> list;

	public void upateList(Message m) {

		String l[] = m.getCon().split(" ");
		list = new ArrayList<String>();
		// 除去重复元素
		String[] lists = NewsList.ifRepeat(l);
		for (int i = 0; i < lists.length; i++) {
			System.out.println(lists[i]);
			NewsList.list.add(lists[i]);
		}

	}

	public NewsList(String userid) {
		this.userid = userid;
	}

	public NewsList() {

	}

	public void init(String userid) {

		jf = new JFrame();
		jf.setSize(300, 600);
		jf.setTitle("当前在线用户ID：" + userid);
		jf.setLocationRelativeTo(null);

		jp = new JPanel(new GridLayout(50, 1, 4, 4));

		JLabel[] jlb = new JLabel[50];
		for (int i1 = 0; i1 < list.size(); i1++) {
			jlb[i1] = new JLabel(list.get(i1), new ImageIcon(Objects.requireNonNull(NewsList.class.getClassLoader().getResource("but.jpg"))), JLabel.LEFT);
			jlb[i1].addMouseListener(this);
			jp.add(jlb[i1]);
		}

		JScrollPane jsp = new JScrollPane(jp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jf.add(jsp);

		jf.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			String sellerid = ((JLabel) e.getSource()).getText();
			Chat ch = new Chat(userid, sellerid);
			ch.init(userid, sellerid);
			ManageChat.addChat(userid + " " + sellerid, ch);
			ManageChat.show();
			List<Message> list = new ArrayList<Message>();
			list = (List<Message>) MessageAdd.get(userid);
			for (Message m : list) {
				ch.showMessage(m);
			}

			MessageAdd.delect(userid);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.black);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
