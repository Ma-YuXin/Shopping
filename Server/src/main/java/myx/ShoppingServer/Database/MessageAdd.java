package myx.ShoppingServer.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import myx.ShoppingClient.Model.Message;
import myx.ShoppingServer.Util.Dome;


public class MessageAdd {

	public static void add(Message m) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into message(mesType,sender,getter,con,sendTime )values(?,?,?,?,?)";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getMesType());
			ps.setString(2, m.getSender());
			ps.setString(3, m.getGetter());
			ps.setString(4, m.getCon());
			ps.setString(5, m.getSendTime());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}
	}

	public static void delect(Message m) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from message where sendtime=?";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getSendTime());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}
	}

	public static List<Message> get(String userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<Message> list = new ArrayList<Message>();
		try {
			String sql = "select mesType,sender,getter,con,sendTime from message where sender=1 or getter=1";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);

			set = ps.executeQuery(sql);
			// 遍历结果积
			while (set.next()) {
				Message m = new Message();
				m.setCon(set.getString("con"));
				m.setGetter(set.getString("getter"));
				m.setMesType(set.getString("mesType"));
				m.setSender(set.getString("sender"));
				m.setSendTime(set.getString("sendTime"));
				list.add(m);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

}
