package myx.ShoppingServer.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myx.ShoppingClient.Model.User;
import myx.ShoppingServer.Util.Dome;


public class UserAdd {

	public static void add(User u) {
		System.out.println("将要添加用户");
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into user(id,username,password,nickname,status,phonenumber,address )values(?,?,?,?,?,?,?)";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getId());
			ps.setString(2, u.getName());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getNickname());
			ps.setString(5, u.getStatus());
			ps.setString(6, u.getPhoneNumber());
			ps.setString(7, u.getAddress());
			ps.executeUpdate();
			System.out.println("添加用户成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
				System.out.println("添加用户失败");
		} finally {
			Dome.closeAll(ps, con);

		}
	}

	// 改个人信息
	public static void update(String id, String username, String phonenumber, String nickname, String address) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE user SET nickname=?,username=?,phonenumber=?,address=? WHERE id=?;";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setString(2, username);
			ps.setString(3, phonenumber);
			ps.setString(4, address);
			ps.setString(5, id);

			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}
	}

	// 改密码
	public static void update(String id, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE user SET password=? WHERE id=?;";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, id);
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}
	}

	public static void update(User u) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE user SET nickname=?,username=?,phonenumber=?,address=? WHERE id=?;";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, u.getNickname());
			ps.setString(2, u.getName());
			ps.setString(3, u.getPhoneNumber());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}

	}

	public static void quary() {	System.out.println("将要查询用户信息");
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "select id,username,phonenumber from user;";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			// 执行
			ResultSet set = ps.executeQuery(sql);
			// 遍历结果积
			while (set.next()) {

				String idget = set.getString("id");
				String nameget = set.getString("username");
				String phnoeget = set.getString("phonenumber");
				System.out.println(idget + "    " + nameget + "    " + phnoeget);
				System.out.println("成功查询用户信息");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查询用户信息失败");
		} finally {
			Dome.closeAll(ps, con);

		}
	}

//登录查询
	public static boolean quary(String id, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		try {
			String sql = "select id,password FROM user;";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			// 执行
			set = ps.executeQuery(sql);
			// 遍历结果积
			while (set.next()) {
				// 使用set获取各个字段的值
				// id
				// 列的索引从1开始
				// int id = set.getInt(1);
				String idget = set.getString("id");
				String pass = set.getString("password");

				if (id.equals(idget)) {
					if (password.equals(pass)) {

						return true;
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (set != null)
					set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			Dome.closeAll(ps, con);

		}
		return false;
	}

	public static boolean quary(String id, String name, String phonenumber) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		try {
			String sql = "select id,username,phonenumber FROM user;";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			// 执行
			set = ps.executeQuery(sql);
			// 遍历结果积
			while (set.next()) {

				String idget = set.getString("id");
				String nameget = set.getString("username");
				String phnoeget = set.getString("phonenumber");
				if (id.equals(idget)) {
					if (name.equals(nameget)) {
						if (phonenumber.equals(phnoeget)) {
							return true;
						}
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (set != null)
					set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			Dome.closeAll(ps, con);

		}
		return false;
	}

//改个人信息时查是否有此人
	public static boolean quary(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		try {
			String sql = "select id FROM user;";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			// 执行
			set = ps.executeQuery(sql);
			// 遍历结果积
			while (set.next()) {
				// 使用set获取各个字段的值
				// id
				// 列的索引从1开始
				// int id = set.getInt(1);
				String idget = set.getString("id");
				if (id.equals(idget)) {
					return true;

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (set != null)
					set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			Dome.closeAll(ps, con);

		}
		return false;
	}

}
