package myx.ShoppingServer.Util;

import java.util.HashMap;


import myx.ShoppingServer.Thread.OffLine;

public class ManageOffline {

	public static HashMap<String, OffLine> hm = new HashMap<String, OffLine>();

	public static boolean judge(String userid) {
		return ManageOffline.hm.containsKey(userid);
	}

	public static void add(String userid, OffLine nt) {
		ManageOffline.hm.put(userid, nt);
	}

	public static OffLine get(String userid) {

		return hm.get(userid);
	}
}
