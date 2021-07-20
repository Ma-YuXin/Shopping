package myx.ShoppingServer.Database;
import myx.ShoppingClient.Model.User;
import myx.ShoppingServer.Util.Dome;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class NewsList {
	public static void add(User u) throws IOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into list(userid,newslist)values(?,?)";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getId());
			ps.setString(2, u.getId());
			ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);
		}
	}

	public static void update(User u) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "update list set newslist=CONCAT(newslist,?) WHERE userid=?";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, " " + u.getMessage().getGetter());
			ps.setString(2, u.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}
	}

//	String l[] = m.getCon().split(" ");
//	list = new ArrayList<String>();
//	// 除去重复元素
//	String[] lists = NewsList.ifRepeat(l);
//	for (int i = 0; i < lists.length; i++) {
//		System.out.println(lists[i]);
//		NewsList.list.add(lists[i]);
//	}

	public static boolean judge(User u) {
		Connection con = null;
		boolean flag = false;
		String newslist = null;
		try {
			String sql = "select newslist from list WHERE userid=?";
			con = Dome.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getMessage().getSender());
			ResultSet set = ps.executeQuery();
			// 遍历结果积
			while (set.next()) {
				newslist = set.getString("newslist");
			}

			String l[] = newslist.split(" ");
			for (int i = 0; i < l.length; i++) {
				if (l[i].equals(u.getMessage().getGetter())) {
					flag = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return flag;

	}

	public static String quary(String u) {
		Connection con = null;

		String newslist = null;
		try {
			String sql = "select newslist from list WHERE userid=?";
			con = Dome.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u);
			ResultSet set = ps.executeQuery();
			// 遍历结果积
			while (set.next()) {
				newslist = set.getString("newslist");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return newslist;
	}

}
