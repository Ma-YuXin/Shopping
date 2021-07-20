package myx.ShoppingClient.Model;


public class Orders extends Object implements java.io.Serializable {

	// 订单
	private String buyerUserId;
	private String goodsid;
	private String buydate;
	private String name;
	private String confirmdate;
	private String status;
	private String sellerUserId;
	private Message news;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public Message getMessage() {
		return news;
	}

	public void setMessage(Message news) {
		this.news = news;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getBuydate() {
		return buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	public String getConfirmdate() {
		return confirmdate;
	}

	public void setConfirmdate(String confirmdate) {
		this.confirmdate = confirmdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

}
