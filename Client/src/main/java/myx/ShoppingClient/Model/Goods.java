package myx.ShoppingClient.Model;

//
//id varchar(100) primary key ,
//sellerid varchar(20),
//buyerid varchar(20),
// price double,
// introduce varchar(100),
// img longblob,
// stock int (10),
// c_type varchar(11)
public class Goods implements java.io.Serializable {
	/**
	 * 
	 */

	private String id;
	private String name;
	private String introduce;//
	private double price;
	private int stock;// 库存
	private Category category;
	private String imgs;// 图片的保存路径
	Message news;/// 对货物进行标记，好让服务端判断是要上传数据还是删除
	private String sellerid;
	private String buyerid;
	private String com;

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	//
	public Message getMessage() {
		return news;
	}

	public void setMessage(Message news) {
		this.news = news;
	}

	public Goods() {

	}

	public Goods(String id, String name, String introduce, double price, int stock, Category category, String imgs,
				 Message news, String sellerid) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.imgs = imgs;
		this.news = news;
		this.sellerid = sellerid;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}

}
