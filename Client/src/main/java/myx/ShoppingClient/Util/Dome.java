package myx.ShoppingClient.Util;

//用于连接数据库
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dome {
	// 加载数据库驱动 com.mysql.jdbc.Driver
	private static String dbdriver = "com.mysql.cj.jdbc.Driver";
	// 获取mysql连接地址
	private static String dburl = "jdbc:mysql://127.0.0.1:3306/shop?useSSL=false&allowPublicKeyRetrieval=true&&serverTimezone=UTC";
	// 数据名称
	private static String username = "root";
	// 数据库密码
	private static String userpassword = "superme699";
	// 获取一个数据的连接
	public static Connection conn = null;
	// 获取连接的一个状态

	public static void main(String[] args) throws SQLException {
		List<List<Object>> x = getData("select name,age from user");
		System.out.println("x=" + x);
	}

	/**
	 * 获取数据库连接
	 * 
	 * @param myProjName
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(dbdriver);
			// String myjdbcUrl = dburl.replace("cmxDatabaseName", myProjName);
			conn = DriverManager.getConnection(dburl, username, userpassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 重载函数closeaAll
	public static void closeAll(PreparedStatement ps, Connection conn) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查表，返回行的列表，每个列表中包含列的列表。
	 * 
	 * @param ProjName
	 * @param sql
	 * @return
	 */
	public static List<List<Object>> getData(String sql) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		List<List<Object>> list = new ArrayList<List<Object>>();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				List<Object> lst = new ArrayList<Object>();
				for (int i = 1; i <= columnCount; ++i) {
					lst.add(rs.getObject(i) == null ? "" : rs.getObject(i));
				}
				list.add(lst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
}
