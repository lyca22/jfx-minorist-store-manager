package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MinoristStore {

	private List<Product> generalProductList;
	private Account accountList;
	private List<Order> orderList;
	private Category categoryList;
	private PaymentMethod paymentMethod;
	private List<Request> requestList;

	public MinoristStore(List<Product> generalProductList, Account accountList, List<Order> orderList,
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

	public Account getAccountList() {
		return accountList;
	}

	public void setAccountList(Account accountList) {
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

	
	//Add methods
	
	private void addAccount(Account current, Account newAccount) {
		if(current.getNext() == null) {
			current.setNext(newAccount);
		}else {
			addAccount(current.getNext(), newAccount);
		}
	}
	
	
	public void addAdministratorAccount(String username, String password, String names, String surnames) throws FileNotFoundException, IOException {
		Administrator administrator = new Administrator(username, password, names, surnames);
		if(accountList == null) {
			accountList = administrator;
		}else {
			addAccount(accountList, administrator);
		}
	}

	public void addConsumerAccount(String username, String password, String names, String surnames, long phoneNumber, String address,
			List<Order> personalOrderList) throws FileNotFoundException, IOException {
		Consumer consumer = new Consumer(username, password, names, surnames, phoneNumber, address, personalOrderList);
		if(accountList == null) {
			accountList = consumer;
		}else {
			addAccount(accountList, consumer);
		}
	}

	public void addSellerAccount(String username, String password, String tradeName, List<Product> productList) throws FileNotFoundException, IOException {
		Seller seller = new Seller(username, password, tradeName, productList);
		if(accountList == null) {
			accountList = seller;
		}else {
			addAccount(accountList, seller);
		}
	}
}
