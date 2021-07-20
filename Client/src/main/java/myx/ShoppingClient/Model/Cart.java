package myx.ShoppingClient.Model;

import java.io.Serializable;
import java.util.List;
public class Cart implements Serializable {
    private String type;
    private List<Goods> goods;
    Message news;

    public Cart() {
    }

    public Message getMessage() {
        return this.news;
    }

    public void setMessage(Message news) {
        this.news = news;
    }

    public List<Goods> getGoods() {
        return this.goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}