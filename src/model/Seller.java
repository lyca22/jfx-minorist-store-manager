package model;

import java.util.ArrayList;
import java.util.List;

public class Seller extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tradeName;
	private List<Product> productList;
	
	public Seller(String username, String password, String tradeName) {
		super(username, password);
		this.tradeName = tradeName;
		productList = new ArrayList<Product>();
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
