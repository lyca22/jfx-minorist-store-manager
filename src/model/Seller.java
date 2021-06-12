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
	
	/**
	 *Constructor method for Seller. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates an account for a seller. <br>
	 *@param username It is the username used in the account. <br>
	 *@param password It is the password of the account. <br>
	 *@param tradeName It is the trade name of the seller. <br>
	 */
	
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
