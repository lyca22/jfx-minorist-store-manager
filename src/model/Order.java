package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long ID;
	private LocalDateTime date;
	private Consumer client;
	private List<Product> productList;
	private List<Integer> productQuantity;
	private int price;
	private OrderState orderState;
	private PaymentInformation paymentInformation;
	
	public Order(long iD, LocalDateTime date, Consumer client, OrderState orderState, PaymentInformation paymentInformation) {
		super();
		ID = iD;
		this.date = date;
		this.client = client;
		productList = new ArrayList<Product>();
		productQuantity = new ArrayList<Integer>();
		this.orderState = orderState;
		this.paymentInformation = paymentInformation;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Consumer getClient() {
		return client;
	}

	public void setClient(Consumer client) {
		this.client = client;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Integer> getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(List<Integer> productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	
	public int calculatePrice() {
		int price = 0;
		for(int i = 0; i <= productList.size()-1; i++) {
			price += productList.get(i).getPrice()*productQuantity.get(i);
		}
		return price;
	}
	
}
