package model;

public class Request {

	private Product product;
	private RequestType requestType;
	
	public Request(Product product, RequestType requestType) {
		super();
		this.product = product;
		this.requestType = requestType;
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

}
