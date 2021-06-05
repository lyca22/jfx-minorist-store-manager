package model;

import java.util.ArrayList;
import java.util.List;

public class MinoristStore {

	private List<Product> generalProductList;
	private Account accountList;
	private List<Order> orderList;
	private Category categoryList;
	private PaymentMethod paymentMethod;
	private List<Request> requestList;

	public MinoristStore() {
		super();
		generalProductList = new ArrayList<Product>();
		orderList = new ArrayList<Order>();
		requestList = new ArrayList<Request>();
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

	//Adding methods.

	public boolean addAdministratorAccount(String username, String password, String names, String surnames) {
		boolean added = false;
		Account account = searchAccount(username);
		if(account == null) {
			added = true;
			Administrator administrator = new Administrator(username, password, names, surnames);
			if(accountList == null) {
				accountList = administrator;
			}else {
				addAccount(accountList, administrator);
			}
		}
		return added;
	}

	public boolean addConsumerAccount(String username, String password, String names, String surnames, long phoneNumber, String address) {
		boolean added = false;
		Account account = searchAccount(username);
		if(account == null) {
			added = true;
			Consumer consumer = new Consumer(username, password, names, surnames, phoneNumber, address);
			if(accountList == null) {
				accountList = consumer;
			}else {
				addAccount(accountList, consumer);
			}
		}
		return added;
	}

	public boolean addSellerAccount(String username, String password, String tradeName) {
		boolean added = false;
		Account account = searchAccount(username);
		if(account == null) {
			added = true;
			Seller seller = new Seller(username, password, tradeName);
			if(accountList == null) {
				accountList = seller;
			}else {
				addAccount(accountList, seller);
			}
		}
		return added;
	}

	private void addAccount(Account current, Account newAccount) {
		if(current.getNext() == null) {
			current.setNext(newAccount);
		}else {
			addAccount(current.getNext(), newAccount);
		}
	}

	public boolean addCategory(String name) {
		boolean added = false;
		Category category = searchCategory(name);
		if(category == null) {
			added = true;
			Category newCategory = new Category(name);
			if(categoryList == null) {
				categoryList = newCategory;
			}else {
				addCategory(categoryList, newCategory);
			}
		}
		return added;
	}
	
	private void addCategory(Category currentCategory, Category newCategory) {
		if(currentCategory.getNext() == null) {
			currentCategory.setNext(newCategory);
		}else {
			addCategory(currentCategory.getNext(), newCategory);
		}
	}
	
	//Searching methods.

	public Account searchAccount(String username) {
		boolean found = false;
		Account actualAccount = accountList;
		while (actualAccount != null && !found) {
			if(actualAccount.getUsername().equals(username)) {
				found = true;
			}else {
				actualAccount = actualAccount.getNext();
			}
		}
		return actualAccount;
	}

	public Category searchCategory(String name) {
		boolean found = false;
		Category actualCategory = categoryList;
		while(actualCategory != null && !found) {
			if(actualCategory.getName().equals(name)) {
				found = true;
			}else {
				actualCategory = actualCategory.getNext();
			}
		}
		return actualCategory;
	}
	
	public boolean editCategory(Category category, String newName) {
		boolean edited = false;
		Category current = searchCategory(newName);
		if(current == null) {
			edited = true;
			category.setName(newName);
		}
		return edited;
	}
	
}
