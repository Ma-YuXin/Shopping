
package myx.ShoppingServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import myx.ShoppingServer.Start.MyServer;
import myx.ShoppingServer.Start.MyServerGoods;
import myx.ShoppingServer.Start.MyServerOrder;


//public class StartServer extends JFrame implements ActionListener {
//服务器启动界面
public class StartServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	JPanel jp1;
//	JButton jb1, jb2;
	MyServer ms = new MyServer();
	MyServerGoods msg = new MyServerGoods();
	MyServerOrder mso = new MyServerOrder();

	public static void main(String[] args) {
		new StartServer();
	}

	public StartServer() {
		msg.start();
		mso.start();
		ms.start();
//		jp1 = new JPanel();
//		jb1 = new JButton("启动服务器");
//		jb1.addActionListener(this);
//		jb2 = new JButton("关闭服务器");
//		jp1.add(jb1);
//		jp1.add(jb2);

//		this.add(jp1);
//		this.setSize(500, 400);
//		this.pack();
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);

	}

//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource() == jb1) {
//			msg.start();
//			mso.start();
//			ms.start();
//
//		} else if (e.getSource() == jb2) {
//			try {
//				msg.wait();
//				mso.wait();
//				ms.wait();
//			} catch (InterruptedException e1) {
//
//				e1.printStackTrace();
//			}
//
//		}
//	}

}
