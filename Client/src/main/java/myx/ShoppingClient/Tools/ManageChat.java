/**
 * ����һ�������û�����������
 */
package myx.ShoppingClient.Tools;

import myx.ShoppingClient.Windows.Chat;

import java.util.HashMap;
import java.util.Iterator;


//管理聊天见面，好让客户端知道信息发送给，谁
public class ManageChat {
	static int i = 0;
	private static HashMap<String, Chat> hm = new HashMap<String, Chat>();

	//
	public static void addChat(String loginIdAnSellerId, Chat Chat) {
		hm.put(loginIdAnSellerId, Chat);
	}

	//
	public static Chat getChat(String loginIdAnFriendId) {
		return (Chat) hm.get(loginIdAnFriendId);
	}

	public static void delete(String str) {
		hm.remove(str);
	}

	public static void show() {

		Iterator<String> itor = hm.keySet().iterator();// 获取key的Iterator便利
		while (itor.hasNext()) {// 存在下一个值
			System.out.println(i + "chat列表" + itor.next());// 当前key值
			i++;
		}

	}
}
