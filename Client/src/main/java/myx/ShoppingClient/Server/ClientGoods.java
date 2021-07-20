package myx.ShoppingClient.Server;


import myx.ShoppingClient.Connection.GoodsConServer;
import myx.ShoppingClient.Model.Goods;

//和clientuser的作用相同，不过用于操作货物；
public class ClientGoods {
	public static boolean goods(Goods o) {

		return new GoodsConServer().sendGoodsToServer(o);
	}
}
