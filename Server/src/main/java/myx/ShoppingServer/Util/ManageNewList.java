package myx.ShoppingServer.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageNewList {

	public static HashMap<String, List<String>> hm = new HashMap<String, List<String>>();

	public static boolean judge(String userid) {
		return hm.containsKey(userid);
	}

	public static boolean judgeGetter(String userid, String getter) {
		return ManageNewList.getClientThread(userid).contains(getter);
	}

	// �
	public static void addNewsListThread(String userid, String nt) {
		ManageNewList.getClientThread(userid).add(nt);
	}

	public static void add(String userid, List<String> s) {
		hm.put(userid, s);
	}

	public static List<String> getClientThread(String userid) {
		System.out.println("成功返回用户列表");
		return ((List<String>) hm.get(userid));
	}

	//
	public static String getNewList(String userid) {
		List<String> list = new ArrayList<String>();
		list = ManageNewList.getClientThread(userid);
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res += list.get(i).toString() + " ";
		}
		System.out.println(res);
		return res;

	}

}
