package model;

import java.util.List;

public class MinoristStore {

	private List<Product> generalProductList;
	private List<Account> accountList;
	private List<Order> orderList;
	private Category categoryList;
	private PaymentMethod paymentMethod;
	private List<Request> requestList;
	
	public MinoristStore(List<Product> generalProductList, List<Account> accountList, List<Order> orderList,
			Category categoryList, PaymentMethod paymentMethod, List<Request> requestList) {
		super();
		this.generalProductList = generalProductList;
		this.accountList = accountList;
		this.orderList = orderList;
		this.categoryList = categoryList;
		this.paymentMethod = paymentMethod;
		this.requestList = requestList;
	}

	public List<Product> getGeneralProductList() {
		return generalProductList;
	}

	public void setGeneralProductList(List<Product> generalProductList) {
		this.generalProductList = generalProductList;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public Category getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Category categoryList) {
		this.categoryList = categoryList;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

}
