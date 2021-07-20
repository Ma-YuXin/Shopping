package myx.ShoppingClient.Server;


import myx.ShoppingClient.Connection.ClientConServer;
import myx.ShoppingClient.Model.User;

////此方法调用ClientConServer中的方法，
//起到间接使别的类调用ClientConServe的方法；
public class ClientUser {

	public static boolean checkUser(User o) {
		boolean ans=new ClientConServer().sendLoginInfoToServer(o);
		System.out.println("检查用户信息= "+ans);
		return ans;
	}

	public static boolean User(User o) {

		return new ClientConServer().sendLoginInfoToServer(o);
	}

}
