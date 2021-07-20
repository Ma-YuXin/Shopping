package myx.ShoppingServer.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myx.ShoppingClient.Model.Category;
import myx.ShoppingClient.Model.Goods;
import myx.ShoppingServer.Util.Dome;

public class GoodsAdd {
	public static boolean quary(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		try {
			String sql = "select id FROM goods;";
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

	public static void add(Goods good, Category category) throws IOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String sql = "insert into goods(id,sellerid,price,introduce,img,stock,c_type ,name,commend)values(?,?,?,?,?,?,?,?,?)";
			con = Dome.getConn();

			if (good.getImgs() == null) {
				good.setImgs("D:\\Application\\java-2020-03\\WorkSpace\\按钮\\2.jpg");
			}
			ps = con.prepareStatement(sql);
			ps.setString(1, good.getId());
			ps.setString(2, good.getSellerid());
			ps.setDouble(3, good.getPrice());
			ps.setString(4, good.getIntroduce());
			ps.setString(5, good.getImgs());
			ps.setLong(6, good.getStock());
			ps.setString(7, category.getCategoryType());
			ps.setString(8, good.getName());
			ps.setString(9, "卖家说请放心购买\r\n");
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);
			// in.close();
		}
	}

	public static void update(Goods g) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "update goods set commend=CONCAT(commend,?) WHERE id=?";
			con = Dome.getConn();
			ps = con.prepareStatement(sql);
			ps.setString(1, g.getCom() + "\r\n");
			ps.setString(2, g.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Dome.closeAll(ps, con);

		}
	}

	public static void readimg() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		FileOutputStream fos = null;
		String sql = "select id,img from goods;";
		con = Dome.getConn();
		try {
			ps = con.prepareStatement(sql);
			set = ps.executeQuery();
			while (set.next()) {
				Blob img = set.getBlob("img");
				String filename = "picture" + set.getString("id");
				File saveimg = new File("D:\\Application\\eclipse\\database picture\\" + filename);
				fos = new FileOutputStream(saveimg);
				fos.write(img.getBytes(1, (int) img.length()));
				fos.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dome.closeAll(set, ps, con);
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<Goods> get() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			String sql = "select id,buyerid,sellerid,price,introduce,img,stock,c_type ,name ,commend from goods";
			// id,buyerid,sellerid,price,introduce,img,stock, ,name
			con = Dome.getConn();
			ps = con.prepareStatement(sql);

			set = ps.executeQuery(sql);
			// 遍历结果积
			while (set.next()) {
				Goods good = new Goods();
				good.setBuyerid(set.getString("buyerid"));
				good.setId(set.getString("id"));
				good.setImgs(set.getString("img"));
				good.setIntroduce(set.getString("introduce"));
				good.setName(set.getString("name"));
				good.setPrice(set.getDouble("price"));
				good.setSellerid(set.getString("sellerid"));
				good.setStock((int) set.getLong("stock"));
				Category c = new Category(set.getString("c_type"));
				good.setCategory(c);
				good.setCom(set.getString("commend"));
				list.add(good);
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
