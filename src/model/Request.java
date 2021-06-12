package model;

import java.io.Serializable;

public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private RequestType requestType;
	private Seller seller;
	
	/**
	 *Constructor method for Request. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates a request. <br>
	 *@param product It is the product of the request. <br>
	 *@param requestType It is the type of request that the seller could do. It could be ADD, EDIT, DISABLE, ENABLE, DELETE a product. <br>
	 *@param seller It is the seller that did the request. <br>
	 */
	
	public Request(Product product, RequestType requestType, Seller seller) {
		super();
		this.product = product;
		this.requestType = requestType;
		this.seller = seller;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public long getProductID() {
		return product.getID();
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	public String getProductCategoryAsString() {
		return product.getCategory().getName();
	}
	
	public String getProductBrand() {
		return product.getBrand();
	}
	
}
