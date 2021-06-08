package model;

public class Request {

	private Product product;
	private RequestType requestType;
	private Seller seller;
	
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
