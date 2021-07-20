package myx.ShoppingClient.Model;

//目录
public class Category extends Object implements java.io.Serializable {
	/**
	 * 
	 */

	private String CategoryType;// 目录类型，，如电器，学习用品等等。详情见CategoryType类中
	private String type;
	Message news;

	public Message getMessage() {
		return news;
	}

	public void setMessage(Message news) {
		this.news = news;
	}

	public Category(String categorytype) {
		CategoryType = categorytype;
	}

	public String getCategoryType() {
		return CategoryType;
	}

	public void setCategoryType(String categoryType) {
		CategoryType = categoryType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
