package myx.ShoppingServer.Database;

import myx.ShoppingClient.Model.Orders;
import myx.ShoppingServer.Util.Dome;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class OrderAdd {
	public static void add(Orders order) throws IOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into orders(goodsid,buy_date,name,buy_user_id,sell_user_id )values(?,?,?,?,?)";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, order.getGoodsid());
			ps.setString(2, order.getBuydate());
			ps.setString(3, order.getName());
			ps.setString(4, order.getBuyerUserId());
			ps.setString(5, order.getSellerUserId());
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}
	}

	public static Object get(String userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<Orders> list = new ArrayList<Orders>();
		try {
			String sql = "select goodsid,buy_date,name,buy_user_id,sell_user_id from orders";
			// id,buyerid,sellerid,price,introduce,img,stock, ,name
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			set = ps.executeQuery(sql);
			// 遍历结果积
			while (set.next()) {
				Orders o = new Orders();
				o.setGoodsid(set.getString("goodsid"));
				o.setBuydate(set.getString("buy_date"));
				o.setName(set.getString("name"));
				o.setBuyerUserId(set.getString("buy_user_id"));
				o.setSellerUserId(set.getString("sell_user_id"));
				list.add(o);
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
