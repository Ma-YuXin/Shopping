package myx.ShoppingClient.Database;

import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Util.Dome;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageAdd {

	public static void add(Message m) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into clientMessage(mesType,sender,getter,con,sendTime )values(?,?,?,?,?)";
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

	public static void delect(String userid) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "delete from clientMessage where getter=?";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
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
			String sql = "select mesType,sender,getter,con,sendTime from clientMessage where getter=?";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);

			set = ps.executeQuery();
			// 遍历结果积
			while (set.next()) {
				// 使用set获取各个字段的值
				// id
				// 列的索引从1开始
				// int id = set.getInt(1);
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
