package myx.ShoppingServer.Thread;

import java.util.HashMap;
import java.util.Iterator;

public class ManageClientThread {

	public static HashMap<String, SerConClientThread> hm = new HashMap<String, SerConClientThread>();

	public static boolean judge(String userid) {
		return hm.containsKey(userid);
	}

	// �
	public static void addClientThread(String userid, SerConClientThread ct) {
		hm.put(userid, ct);
	}

	public static SerConClientThread getClientThread(String uid) {
		return (SerConClientThread) hm.get(uid);
	}

	public static void delete(String str) {
		hm.remove(str);
	}

	//
	public static String getAllOnLineUserid() {
		//
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}

}
