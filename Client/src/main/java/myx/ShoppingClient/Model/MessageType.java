/**
 * �����������
 */
package myx.ShoppingClient.Model;

public interface MessageType {
//作用1：看发送信息的类型；
	// 作用2：在user类（即用户类）中的Message的类型标记发送，好让服务端进行判断，是要登录还是更改用户数据；
	// 作用3：在good类（即货物类）中的Message的类型标记发送，好让服务端进行判断，是要上传商品还是更改删除商品；
	String message_succeed = "1";// 表示登录，注册，修改信息等成功
	String message_login_fail = "2";// 表示登录，注册，修改信息等失败
	String message_comm_mes = "3";// 普通用户聊天信息
	String message_get_onLineFriend = "4";// 表示得到在线好友列表
	String message_ret_onLineFriend = "5";// 表示返回在线好友列表
	String register = "6";// 表示注册朝左
	String check = "7";// 表示登陆操作
	String change = "8";// 表示修改个人信息 在货物中表示返回所有物品
	String checkid = "9";// 表示找回密码
	String excpetlogin = "10";
}
