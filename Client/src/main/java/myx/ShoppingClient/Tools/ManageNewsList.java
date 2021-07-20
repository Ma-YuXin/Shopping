/**
 * ������ѡ�������..������
 */
package myx.ShoppingClient.Tools;

import myx.ShoppingClient.Windows.NewsList;

import java.util.HashMap;



public class ManageNewsList {

	private static HashMap<String, NewsList> hm = new HashMap<String, NewsList>();

	public static void addFriendList(String userid, NewsList newslist) {

		hm.put(userid, newslist);
	}

	public static NewsList getFriendList(String qqId) {
		return (NewsList) hm.get(qqId);
	}
}
