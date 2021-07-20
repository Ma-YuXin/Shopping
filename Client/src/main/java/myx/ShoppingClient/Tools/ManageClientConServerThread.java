
package myx.ShoppingClient.Tools;

import myx.ShoppingClient.Thread.ClientConServerThread;

import java.util.HashMap;

//在hashmap中用用户id标记socket，好让聊天界面调用聊天对象的socket
public class ManageClientConServerThread {

	private static HashMap<String, ClientConServerThread> hm = new HashMap<String, ClientConServerThread>();

//添加
	public static void addClientConServerThread(String userid, ClientConServerThread ccst) {
		hm.put(userid, ccst);
	}

//调用
	public static ClientConServerThread getClientConServerThread(String userid) {
		return (ClientConServerThread) hm.get(userid);
	}

	public static void delete(String userid) {
		hm.remove(userid);
	}
}
