package myx.ShoppingClient.Tools;

import myx.ShoppingClient.Model.Orders;

import java.util.ArrayList;
import java.util.List;



public class ManageOrder {

	public static List<Orders> listall = new ArrayList<Orders>();
	public static List<Orders> list;
	public static List<Orders> buylist;

	public static void setList(String userid) {
		ManageOrder.list = new ArrayList<Orders>();
		ManageOrder.buylist = new ArrayList<Orders>();
		for (int i = 0; i < ManageOrder.listall.size(); i++) {
			if (ManageOrder.listall.get(i).getSellerUserId().equals(userid)) {
				ManageOrder.list.add(ManageOrder.listall.get(i));
			}
			if (ManageOrder.listall.get(i).getBuyerUserId().equals(userid)) {
				ManageOrder.buylist.add(ManageOrder.listall.get(i));
			}
		}
	}

}
