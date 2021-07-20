package myx.ShoppingClient.Tools;

import myx.ShoppingClient.Model.Goods;

import java.util.ArrayList;
import java.util.List;



public class ManageGoods {
	public static List<Goods> listall = null;
	public static List<Goods> listStudy;
	public static List<Goods> listDaily;
	public static List<Goods> listElectricity;
	public static List<Goods> listWork;
	public static List<Goods> listCloth;
	public static List<Goods> listCosmetics;
	public static List<Goods> listFood;
	public static List<Goods> listToy;
	public static List<Goods> listMedicine;
	public static List<Goods> listCook;
	public static List<Goods> quary;

	public List<Goods> getListDaily() {
		return listDaily;
	}

	public void setListDaily() {
		ManageGoods.listDaily = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("日用")) {
				ManageGoods.listDaily.add(ManageGoods.listall.get(i));
			}
		}

	}

//	String a1 = "日用";
//	String a2 = "学习用品";
//	String a3 = "电器";
//	String a4 = "办公";
//	String a5 = "服装";
//	String a6 = "化妆品"
	public List<Goods> getListElectricity() {
		return listElectricity;
	}

	public void setListElectricity() {
		ManageGoods.listElectricity = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("电器")) {
				ManageGoods.listElectricity.add(ManageGoods.listall.get(i));
			}
		}

	}

	public List<Goods> getListWork() {
		return listWork;
	};

	public void setListWork() {
		ManageGoods.listWork = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("办公")) {
				ManageGoods.listWork.add(ManageGoods.listall.get(i));
			}
		}

	}

	public List<Goods> getListCloth() {
		return listCloth;
	}

	public void setListCloth() {
		ManageGoods.listCloth = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("服装")) {
				ManageGoods.listCloth.add(ManageGoods.listall.get(i));
			}
		}

	}

	public List<Goods> getListCosmetics() {
		return listCosmetics;
	}

	public void setListCosmetics() {
		ManageGoods.listCosmetics = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("化妆品")) {
				ManageGoods.listCosmetics.add(ManageGoods.listall.get(i));
			}
		}

	}

	public List<Goods> getListFood() {
		return listFood;
	}

	public void setListFood() {
		ManageGoods.listFood = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("食品")) {
				ManageGoods.listFood.add(ManageGoods.listall.get(i));
			}
		}
	}

	public List<Goods> getListToy() {
		return listToy;
	}

	public void setListToy() {
		ManageGoods.listToy = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("玩具")) {
				ManageGoods.listToy.add(ManageGoods.listall.get(i));
			}
		}
	}

	public List<Goods> getListMedicine() {
		return listMedicine;
	}

	public void setListMedicine() {
		ManageGoods.listMedicine = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("药品")) {
				ManageGoods.listMedicine.add(ManageGoods.listall.get(i));
			}
		}
	}

	public List<Goods> getListCook() {
		return listCook;
	}

	public void setListCook() {
		ManageGoods.listCook = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("厨具")) {
				ManageGoods.listCook.add(ManageGoods.listall.get(i));
			}
		}
	}

	public static List<Goods> getListall() {
		return listall;
	}

	public static void setListall(List<Goods> list) {
		listall = list;

	}

	public List<Goods> getListStudy() {
		return listStudy;
	}

	public void setListStudy() {
		ManageGoods.listStudy = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {
			if (ManageGoods.listall.get(i).getCategory().getCategoryType().equals("学习用品")) {
				ManageGoods.listStudy.add(ManageGoods.listall.get(i));

			}
		}

	}

	public List<Goods> getQuary() {
		return quary;
	}

	public void setQuary(String str) {
		ManageGoods.quary = new ArrayList<Goods>();
		for (int i = 0; i < ManageGoods.listall.size(); i++) {

			if (ManageGoods.listall.get(i).getName().contains(str)) {
				ManageGoods.quary.add(ManageGoods.listall.get(i));
			}
		}

	}
}
