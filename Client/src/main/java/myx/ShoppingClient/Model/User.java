package myx.ShoppingClient.Model;

import myx.ShoppingClient.Windows.NewsList;

public class User extends Object implements java.io.Serializable {
	/**
	 * 
	 */

	/**
	 * 
	 */
	// 用户类

	private String id;
	private String name = null;
	private String password = null;
	private String status = null;

	private String nickname = null;
	private String phoneNumber = null;
	private String address = null;
	private NewsList nl = null;

	public void setAddress(String address) {
		this.address = address;
	}

	private Message news = null;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public Message getMessage() {
		return news;
	}

	public void setMessage(Message news) {
		this.news = news;
	}

	public NewsList getNl() {
		return nl;
	}

	public void setNl(NewsList nl) {
		this.nl = nl;
	}

}
