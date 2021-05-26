package model;

import java.util.List;

public class Seller extends Account {

	private String tradeName;
	private List<Product> productList;
	private Seller left;
	private Seller right;
	
	public Seller(String username, String password, String tradeName, List<Product> productList) {
		super(username, password);
		this.tradeName = tradeName;
		this.productList = productList;
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

	public Seller getLeft() {
		return left;
	}

	public void setLeft(Seller left) {
		this.left = left;
	}

	public Seller getRight() {
		return right;
	}

	public void setRight(Seller right) {
		this.right = right;
	}
	
}
