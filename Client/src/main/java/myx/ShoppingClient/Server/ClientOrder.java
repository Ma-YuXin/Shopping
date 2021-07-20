package myx.ShoppingClient.Server;


import myx.ShoppingClient.Connection.OrderConServer;
import myx.ShoppingClient.Model.Orders;

//和clientuser的作用相同，不过用于操作订单；
public class ClientOrder {

	public static boolean order(Orders o) {

		return new OrderConServer().sendOrdersToServer(o);
	}

}
