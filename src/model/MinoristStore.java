package model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.CantAddAccountException;
import exceptions.CantAddCategoryException;
import exceptions.CantAddPaymentMethodException;
import thread.OrderReportThread;
import thread.ProductReportThread;

public class MinoristStore implements Utility {

	private static final String GENERAL_PRODUCT_LIST_NAME = "data/generalProductList.minsma";
	private static final String ACCOUNT_LIST_NAME = "data/accountList.minsma";
	private static final String ORDER_LIST_NAME = "data/orderList.minsma";
	private static final String CATEGORY_LIST_NAME = "data/categoryList.minsma";
	private static final String PAYMENT_METHODS_NAME = "data/paymentMethods.minsma";
	private static final String REQUEST_LIST_NAME = "data/requestList.minsma";

	private List<Product> generalProductList;
	private Account accountList;
	private List<Order> orderList;
	private Category categoryList;
	private PaymentMethod paymentMethods;
	private List<Request> requestList;

	/**
	 * Constructor method of MinoristStore.
	 */

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

	public PaymentMethod getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(PaymentMethod paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	public Product cloneProduct(Product product) throws CloneNotSupportedException {
		Product copy = (Product) product.clone();
		return copy;
	}

	//Sorting methods.

	/**
	 * This method sort the products by selection.
	 * Pre: The list of products has been created. <br>
	 * Post: The list of products is sorted. <br>
	 * @param list Is a list of products.
	 */

	public void sortProductBySelection(List<Product> list) {
		for(int i = 0; i <= list.size()-1; i++) {
			Product min = list.get(i);
			for(int j = i+1; j <= list.size()-1; j++) {
				Product current = list.get(j);
				if(min.getID() > current.getID()) {
					list.set(j, min);
					list.set(i, current);
				}
			}
		}
	}

	/**
	 * This method sort the requests by insertion. <br>
	 * Pre: The list of requests has been created. <br>
	 * Post: The list of requests is sorted. <br>
	 * @param list Is a list of requests.
	 */

	public void sortRequestsByInsertion(List<Request> list) {
		for(int i = 1;i < list.size(); i++) {
			for(int j = i; j > 0 && list.get(j-1).getProductID()>list.get(j).getProductID();j--) {
				Request temp = list.get(j);
				list.set(j,list.get(j-1));
				list.set(j-1,temp);
			}
		}
	}

	//Adding methods.

	/**
	 * This method adds a product to the general productProductList and to the SellerList. <br>
	 * Pre: The method sortProductBySelection has been called. <br>
	 * Post: A product has been added to the lists. <br>
	 * @param product It is the product.
	 */

	public void addProduct(Product product) {
		try {
			generalProductList.add(product);
			Product copy = cloneProduct(product);
			product.getSellerList().get(0).getProductList().add(copy);
			sortProductBySelection(generalProductList);
			sortProductBySelection(product.getSellerList().get(0).getProductList());
			saveAll();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method adds a request to the general requestList. <br>
	 * Pre: The methods randomNumberWithRange and sortRequestsByInsertion have been called. <br>
	 * Post: A request has been added to the list. <br>
	 * @param name It is the name of the product. 
	 * @param category It is the category of the product. 
	 * @param brand It is the brand of the product. 
	 * @param price It is the price of the product. 
	 * @param stock It is the number of products available. 
	 * @param description It is the description of the product. 
	 * @param seller It is the seller that did the request. 
	 * @param requestType It is the type of request that the seller could do. It could be ADD, EDIT, DISABLE, ENABLE, DELETE a product. 
	 * @return Returns a long with the ID.
	 */

	public long addRequest(String name, Category category, String brand, int price, int stock, String description, Seller seller, RequestType requestType) throws FileNotFoundException, IOException {
		long ID = randomNumberWithRange(1, Integer.MAX_VALUE);
		Product product = new Product(ID, name, category, brand, price, stock, description);
		product.getSellerList().add(seller);
		Request request = new Request(product, requestType, seller);
		requestList.add(request);
		sortRequestsByInsertion(requestList);
		saveAll();
		return ID;
	}

	public long addRequestWithoutSaving(String name, Category category, String brand, int price, int stock, String description, Seller seller, RequestType requestType) throws FileNotFoundException, IOException {
		long ID = randomNumberWithRange(1, Integer.MAX_VALUE);
		Product product = new Product(ID, name, category, brand, price, stock, description);
		product.getSellerList().add(seller);
		Request request = new Request(product, requestType, seller);
		requestList.add(request);
		sortRequestsByInsertion(requestList);
		return ID;
	}

	public long addRequest(long ID, String name, Category category, String brand, int price, int stock, String description, Seller seller, RequestType requestType) throws FileNotFoundException, IOException {
		Product product = new Product(ID, name, category, brand, price, stock, description);
		Request request = new Request(product, requestType, seller);
		requestList.add(request);
		sortRequestsByInsertion(requestList);
		saveAll();
		return ID;
	}

	public long addRequest(Product product, Seller seller, RequestType requestType) throws FileNotFoundException, IOException {
		Request request = new Request(product, requestType, seller);
		requestList.add(request);
		sortRequestsByInsertion(requestList);
		saveAll();
		return product.getID();
	}



	/**
	 * This method adds an administrator account to the accountList. <br>
	 * Pre: The methods addAccount and searchAccount have been called. <br>
	 * Post: An administrator account has been added to the list. <br>
	 * @param username It is the username used in the account.
	 * @param password It is the password of the account.
	 * @param names It is the name(s) of the administrator.
	 * @param surnames It is the surname(s) of the administrator. 
	 * @return Returns a boolean indicating if the account was added.
	 */

	public boolean addAdministratorAccount(String username, String password, String names, String surnames) throws CantAddAccountException, FileNotFoundException, IOException{
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
		}else {
			throw new CantAddAccountException(username);
		}
		saveAll();
		return added;
	}



	/**
	 * This method adds a consumer account to the accountList. <br>
	 * Pre: The methods addAccount and searchAccount have been called. <br>
	 * Post: A consumer account has been added to the list. <br>
	 * @param username It is the username used in the account.
	 * @param password It is the password of the account.
	 * @param names It is the name(s) of the consumer.
	 * @param surnames It is the surname(s) of the consumer. 
	 * @param phoneNumber It is the phone number of the consumer.
	 * @param address It is the address of the consumer.
	 * @return Returns a boolean indicating if the account was added.
	 */

	public boolean addConsumerAccount(String username, String password, String names, String surnames, long phoneNumber, String address) throws CantAddAccountException, FileNotFoundException, IOException {

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
		}else {
			throw new CantAddAccountException(username);
		}
		saveAll();
		return added;
	}


	/**
	 * This method adds a seller account to the accountList. <br>
	 * Pre: The methods addAccount and searchAccount have been called. <br>
	 * Post: A seller account has been added to the list. <br>
	 * @param username It is the username used in the account.
	 * @param password It is the password of the account.
	 * @param tradeName It is the trade name of the seller.
	 * @return Returns a boolean indicating if the account was added.
	 */

	public boolean addSellerAccount(String username, String password, String tradeName) throws CantAddAccountException, FileNotFoundException, IOException {

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
		}else {
			throw new CantAddAccountException(username);
		}
		saveAll();
		return added;
	}

	/**
	 * This method adds an account. <br>
	 * At first it checks if 'current.getNext()' is null. If that's the case, it setNext() the current account with 'newAccount' as a parameter. <br>
	 * If it is not null, this method is called by itself with 'current.getNext()' and 'newAccount' as parameters. <br>
	 * Pre: The method addAccount has been called. <br>
	 * Post: An account has been added to the list. <br>
	 * @param current It is the current account.
	 * @param newAccount It is the new account.
	 */

	private void addAccount(Account current, Account newAccount) {
		if(current.getNext() == null) {
			current.setNext(newAccount);
		}else {
			addAccount(current.getNext(), newAccount);
		}
	}


	/**
	 * This method adds a category to the categoryList. <br>
	 * Pre: The methods addCategory and searchCategory have been called. <br>
	 * Post: A category has been added to the list. <br>
	 * @param name It is the name of the category.
	 * @return Returns a boolean indicating if the category was added.
	 */

	public boolean addCategory(String name) throws CantAddCategoryException, FileNotFoundException, IOException {
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
		}else {
			throw new CantAddCategoryException(name);
		}
		saveAll();
		return added;
	}

	/**
	 * This method adds a category. <br>
	 * At first it checks if 'currentCategory.getNext()' is null. If that's the case, it setNext() the current category with 'newCategory' as a parameter. <br>
	 * If it is not null, this method is called by itself with 'currentCategory.getNext()' and 'newCategory' as parameters. <br>
	 * Pre: The method addCategory has been called. <br>
	 * Post: A category has been added to the list. <br>
	 * @param currentCategory It is the current category.
	 * @param newCategory It is the new category.
	 */

	private void addCategory(Category currentCategory, Category newCategory) {
		if(currentCategory.getNext() == null) {
			currentCategory.setNext(newCategory);
		}else {
			addCategory(currentCategory.getNext(), newCategory);
		}
	}



	/**
	 * This method adds a payment method. <br>
	 * Pre: The method searchPaymentMethod has been called. <br>
	 * Post: A payment method has been added. <br>
	 * @param name It is the name of the payment method.
	 * @param type It is the payment type. It could be CARD or ONLINE_SYSTEM
	 * @return Returns a boolean indicating if the payment method was added.
	 */

	public boolean addPaymentMethod(String name, PaymentType type) throws CantAddPaymentMethodException, FileNotFoundException, IOException {

		boolean added = false;
		PaymentMethod paymentMethod = searchPaymentMethod(name);
		if(paymentMethod == null) {
			added = true;
			paymentMethod = new PaymentMethod(name, type);
			if(paymentMethods == null) {
				paymentMethods = paymentMethod;
				paymentMethods.setParent(paymentMethods);
			}else {
				addPaymentMethod(paymentMethods, paymentMethod);
			}
		}else {
			throw new CantAddPaymentMethodException(name);
		}
		saveAll();
		return added;
	}

	/**
	 * This method adds a payment method. <br>
	 * At first it checks if 'currentPayment.getName().compareTo(newPayment.getName())' is greater than 0. If that's the case, it checks if 'currentPayment.getLeft()' is null. <br>
	 * If it is not null, this method is called by itself with 'currentPayment.getLeft()' and 'newPayment' as parameters. <br>
	 * Pre: The method addPaymentMethod has been called. <br>
	 * Post: A payment method has been added. <br>
	 * @param currentPayment It is the current payment method.
	 * @param newPayment It is the payment method.
	 */

	private void addPaymentMethod(PaymentMethod currentPayment, PaymentMethod newPayment) {
		if(currentPayment.getName().compareTo(newPayment.getName()) > 0) {
			if(currentPayment.getLeft() == null) {
				currentPayment.setLeft(newPayment);
				newPayment.setParent(currentPayment);
			}else {
				addPaymentMethod(currentPayment.getLeft(), newPayment);
			}
		}else if(currentPayment.getName().compareTo(newPayment.getName()) <= 0) {
			if(currentPayment.getRight() == null) {
				currentPayment.setRight(newPayment);
				newPayment.setParent(currentPayment);
			}else {
				addPaymentMethod(currentPayment.getRight(), newPayment);
			}
		}
	}



	/**
	 * This method adds an order. <br>
	 * Pre: The methods randomNumberWithRange, setProductList, setProductQuantity and setPrice have been called. <br>
	 * Post: An order has been added. <br>
	 * @param client It is the client of the order. 
	 * @param paymentInformation It is the payment information of the order.
	 * @param productList It is the list of the products of the order.
	 * @param quantity It is the number of products of the order. <br>
	 */

	public void addOrder(Consumer client, PaymentInformation paymentInformation, ArrayList<Product> productList, ArrayList<Integer> quantity) throws FileNotFoundException, IOException {

		long ID = randomNumberWithRange(1, Integer.MAX_VALUE);
		LocalDateTime date = LocalDateTime.now();
		Order order = new Order(ID, date, client, OrderState.REQUESTED, paymentInformation);
		@SuppressWarnings("unchecked")
		ArrayList<Product> orderProducts = (ArrayList<Product>) productList.clone();
		@SuppressWarnings("unchecked")
		ArrayList<Integer> orderQuantity = (ArrayList<Integer>) quantity.clone();
		order.setProductList(orderProducts);
		order.setProductQuantity(orderQuantity);
		order.setPrice(order.calculatePrice());
		orderList.add(order);
		client.getPersonalOrderList().add(order);
		saveAll();
	}

	//Deleting methods.




	/**
	 * This method deletes a product. <br>
	 * Pre: The methods getProductList and getID have been called. <br>
	 * Post: A product has been removed. <br>
	 * @param product It is the product. 
	 */

	public void deleteProduct(Product product) throws FileNotFoundException, IOException {
		boolean canDelete = true;
		for(int i = 0; i <= orderList.size()-1; i++) {
			List<Product> products = orderList.get(i).getProductList();
			for(int j = 0; j <= products.size()-1; j++) {
				if(products.get(j).getID() == product.getID()) {
					canDelete = false;
				}
			}
		}
		for(int i = 0; i <= requestList.size()-1; i++) {
			if(requestList.get(i).getProduct().getID() == product.getID()) {
				canDelete = false;
			}
		}
		if(canDelete) {
			for(int i = 0; i <= product.getSellerList().size()-1; i++) {
				Product sellerProduct = searchProductByBinarySearch(product.getID(), product.getSellerList().get(i));
				product.getSellerList().get(i).getProductList().remove(sellerProduct);
			}
			generalProductList.remove(product);
			saveAll();
		}
	}




	/**
	 * This method deletes an order. <br>
	 * Pre: The methods getClient and getPersonalOrderList have been called. <br>
	 * Post: An order has been removed. <br>
	 * @param order It is the order. 
	 */

	public void deleteOrder(Order order) throws FileNotFoundException, IOException {
		order.getClient().getPersonalOrderList().remove(order);
		orderList.remove(order);
		saveAll();
	}




	/**
	 * This method deletes a category if it is not instantiated in generalProductList. <br>
	 * Pre: The method getCategory has been called. <br>
	 * Post: A category has been removed. <br>
	 * @param category It is the category. 
	 */

	public void deleteCategory(Category category) throws FileNotFoundException, IOException {
		boolean canDelete = true;
		for(int i = 0; i <= generalProductList.size()-1; i++) {
			if(generalProductList.get(i).getCategory() == category) {
				canDelete = false;
			}
		}
		if(canDelete) {
			if(categoryList.equals(category)) {
				setCategoryList(category.getNext());
			}else {
				boolean found = false;
				Category actualCategory = categoryList.getNext();
				Category backCategory = categoryList;
				while(actualCategory != null && !found) {
					if(actualCategory.equals(category)) {
						backCategory.setNext(actualCategory.getNext());
						actualCategory.setNext(null);
						found = true;
					}else {
						backCategory = actualCategory;
						actualCategory = actualCategory.getNext();
					}
				}
			}
			saveAll();
		}
	}



	/**
	 * This method deletes a payment method if it is not instantiated in gorderList. <br>
	 * Pre: The methods getPaymentInformation, getPaymentMethod and delete have been called. <br>
	 * Post: A payment method has been removed. <br>
	 * @param paymentMethod It is the payment method. 
	 */

	public void deletePaymentMethod(PaymentMethod paymentMethod) throws FileNotFoundException, IOException {

		boolean canDelete = true;
		for(int i = 0; i <= orderList.size()-1; i++) {
			if(orderList.get(i).getPaymentInformation().getPaymentMethod().equals(paymentMethod)) {
				canDelete = false;
			}
		}
		if(canDelete) {
			delete(paymentMethod);
			saveAll();
		}
	}



	/**
	 * This method deletes a payment method. <br>
	 * At first it checks if 'paymentMethod.getLeft() == null && paymentMethod.getRight())' is null. If that's the case, it checks if it is not instantiated. <br>
	 * If it is not null, this method is called by itself with 'currentPayment.getLeft()' and 'newPayment' as parameters. <br>
	 * Pre: The methods deletePaymentMethod and getMin have been called. <br>
	 * Post: A payment method has been removed. <br>
	 * @param paymentMethod It is the payment method.
	 */

	public void delete(PaymentMethod paymentMethod) throws FileNotFoundException, IOException {

		if(paymentMethod.getLeft() == null && paymentMethod.getRight() == null) {
			if(paymentMethod == paymentMethods) {
				paymentMethods = null;
			}else if(paymentMethod == paymentMethod.getParent().getLeft()) {
				paymentMethod.getParent().setLeft(null);
			}else {
				paymentMethod.getParent().setRight(null);
			}
			paymentMethod.setParent(null);
		}else if(paymentMethod.getLeft() == null || paymentMethod.getRight() == null) {
			PaymentMethod onlyChild;
			if(paymentMethod.getLeft() != null) {
				onlyChild = paymentMethod.getLeft();
			}else {
				onlyChild = paymentMethod.getRight();
			}
			onlyChild.setParent(paymentMethod.getParent());
			if(paymentMethod == paymentMethods) {
				paymentMethods = onlyChild;
			}else if(paymentMethod == paymentMethod.getParent().getLeft()) {
				paymentMethod.getParent().setLeft(onlyChild);
			}else {
				paymentMethod.getParent().setRight(onlyChild);
			}
		}else{
			PaymentMethod successor = getMin(paymentMethod.getLeft());
			paymentMethod.setName(successor.getName());
			paymentMethod.setType(successor.getType());
			paymentMethod.setDisabled(successor.isDisabled());
			deletePaymentMethod(successor);
		}
	}

	/**
	 * This method gets a payment method. <br>
	 * At first it checks if 'current.getRight()' != null. While that's the case, current = current.getRight();. <br>
	 * Pre: The method getRight has been called. <br>
	 * @param current It is the current payment method.
	 * @return current.
	 */

	public PaymentMethod getMin(PaymentMethod current) {
		while(current.getRight() != null) {
			current = current.getRight();
		}
		return current;
	}



	/**
	 * This method deletes a request of the requestList. <br>
	 * Post: A request has been removed. <br>
	 * @param request It is the request. 
	 */

	public void deleteRequest(Request request) throws FileNotFoundException, IOException {
		requestList.remove(request);
		saveAll();
	}

	//Searching methods.

	/**
	 * This method searches an account. <br>
	 * Pre: The methods getUsername, and getNext have been called. <br>
	 * Post: An account has been found. <br>
	 * @param username It is the username.
	 * @return actualAccount It is the account found.
	 */

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

	/**
	 * This method searches a category. <br>
	 * Pre: The methods getName and getNext have been called. <br>
	 * Post: A category has been found. <br>
	 * @param name It is the name of the category.
	 * @return actualCategory It is the category found.
	 */

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

	/**
	 * This method searches a product with binary search in the generalProductList. <br>
	 * Post: A product has been found. <br>
	 * @param ID It is the ID of the product.
	 * @return product It is the product found.
	 */

	public Product searchProductByBinarySearch(Long ID) {
		Product product = null;
		int pos = -1;
		int i = 0;
		int j = generalProductList.size()-1;
		while(i <=j && pos < 0) {
			int middle = (i+j)/2;
			if(generalProductList.get(middle).getID() == ID) {
				pos = middle;
			}else if(generalProductList.get(middle).getID() > ID) {
				j = middle-1;
			}else {
				i = middle+1;
			}
		}
		if(pos >= 0) {
			product = generalProductList.get(pos);
		}
		return product;
	}

	/**
	 * This method searches a product with binary search in the seller.getProductList. <br>
	 * Post: A product has been found. <br>
	 * @param ID It is the ID of the product.
	 * @param seller It is the seller of the product.
	 * @return product It is the product found.
	 */

	public Product searchProductByBinarySearch(Long ID, Seller seller) {
		Product product = null;
		int pos = -1;
		int i = 0;
		int j = seller.getProductList().size()-1;
		while(i <=j && pos < 0) {
			int middle = (i+j)/2;
			if(seller.getProductList().get(middle).getID() == ID) {
				pos = middle;
			}else if(seller.getProductList().get(middle).getID() > ID) {
				j = middle-1;
			}else {
				i = middle+1;
			}
		}
		if(pos >= 0) {
			product = seller.getProductList().get(pos);
		}
		return product;
	}

	/**
	 * This method searches a product in the generalProductList. <br>
	 * Post: A product has been found. <br>
	 * @param ID It is the ID of the product.
	 * @return product It is the product found.
	 */


	public Product searchProduct(Long ID) {
		Product product = null;
		for(int i = 0; i <= generalProductList.size()-1; i++) {
			if(generalProductList.get(i).getID() == ID) {
				product = generalProductList.get(i);
			}
		}
		return product;
	}

	/**
	 * This method searches a product in the seller.getProductList. <br>
	 * Post: A product has been found. <br>
	 * @param ID It is the ID of the product.
	 * @param seller It is the seller of the product.
	 * @return product It is the product found.
	 */

	public Product searchProduct(Long ID, Seller seller) {
		Product product = null;
		for(int i = 0; i <= seller.getProductList().size()-1; i++) {
			if(seller.getProductList().get(i).getID() == ID) {
				product = seller.getProductList().get(i);
			}
		}
		return product;
	}

	/**
	 * This method searches a payment method. <br>
	 * Pre: The methods searchPaymentMethod, getName and getNext have been called. <br>
	 * Post: A payment method has been found. <br>
	 * @param name It is the name of the payment method.
	 * @return payment It is the payment method found.
	 */

	public PaymentMethod searchPaymentMethod(String name) {
		PaymentMethod payment = null;
		if(paymentMethods != null) {
			if(paymentMethods.getName().equalsIgnoreCase(name)) {
				payment = paymentMethods;
			}else {
				payment = searchPaymentMethod(name, paymentMethods);
			}
		}
		return payment;
	}

	/**
	 * This method searches a payment method. <br>
	 * At first it checks if 'currentPayment.getName().compareTo(name)' is greater than 0. Then go over the left and then right of the currentPayment. <br>
	 * Pre: The methods getRight, getLeft searchPaymentMethod and others have been called. <br>
	 * @param name It is the name of the payment method.
	 * @param currentPayment It is the current payment method.
	 * @return payment It is the payment method found.
	 */

	private PaymentMethod searchPaymentMethod(String name, PaymentMethod currentPayment) {
		PaymentMethod payment = null;
		if(currentPayment.getName().compareTo(name) > 0) {
			if(currentPayment.getLeft() != null) {
				if(currentPayment.getLeft().getName().equalsIgnoreCase(name)) {
					payment = currentPayment.getLeft();
				}else {
					payment = searchPaymentMethod(name, currentPayment.getLeft());
				}
			}
		}else if(currentPayment.getName().compareTo(name) <= 0) {
			if(currentPayment.getRight() != null) {
				if(currentPayment.getRight().getName().equalsIgnoreCase(name)) {
					payment = currentPayment.getRight();
				}else {
					payment = searchPaymentMethod(name, currentPayment.getRight());
				}
			}
		}
		return payment;
	}

	//Editing methods.



	/**
	 * This method edits a category. <br>
	 * At first it checks if 'current' is null. <br>
	 * Pre: The method searchCategory has been called. <br>
	 * @param category It is the category.
	 * @param newName It is the new name of the category.
	 * @return Returns a boolean indicating if the category was edited.
	 */

	public boolean editCategory(Category category, String newName) throws FileNotFoundException, IOException {

		boolean edited = false;
		Category current = searchCategory(newName);
		if(current == null) {
			edited = true;
			category.setName(newName);
		}
		saveAll();
		return edited;
	}


	/**
	 * This method edits a payment method. <br>
	 * At first it checks if 'current' is null. <br>
	 * Pre: The method searchPaymentMethod has been called. <br>
	 * @param paymentMethod It is the payment method.
	 * @param newName It is the new name of the payment method.
	 * @param newPaymentType It is the new payment type of the payment method.
	 * @return Returns a boolean indicating if the payment method was edited.
	 */

	public boolean editPaymentMethod(PaymentMethod paymentMethod, String newName, PaymentType newPaymentType) throws FileNotFoundException, IOException {

		boolean edited = false;
		PaymentMethod current = searchPaymentMethod(newName);
		if(current == null) {
			edited = true;
			paymentMethod.setName(newName);
			paymentMethod.setType(newPaymentType);
		}
		saveAll();
		return edited;
	}


	/**
	 * It returns a random number given a range. <br>
	 * Post: Returns a random int given a range of ints. <br>
	 * @param min the minimal number.
	 * @param max the maximum number.
	 * @return Returns a random int given a range of ints.
	 */

	@Override
	public long randomNumberWithRange(long min, long max) {
		long range = (max - min) + 1;
		return (long)(Math.random() * range) + min;
	}

	//Exporting methods.


	/**
	 * It generates seller product report. <br>
	 * @param fileName It is the name of file.
	 * @param separator It is the separator of the information.
	 * @param seller It is the seller.
	 */

	public void generateSellerProductReport(String fileName, String separator, Seller seller) throws FileNotFoundException, InterruptedException {
		PrintWriter pw = new PrintWriter(fileName);
		int mid = (seller.getProductList().size()-1)/3;
		ProductReportThread prt1 = new ProductReportThread(pw, seller.getProductList(), 0, mid, separator);
		ProductReportThread prt2 = new ProductReportThread(pw, seller.getProductList(), mid+1, (mid*2), separator);
		ProductReportThread prt3 = new ProductReportThread(pw, seller.getProductList(), (mid*2)+1, seller.getProductList().size()-1, separator);
		prt1.start();
		prt2.start();
		prt3.start();
		prt1.join();
		prt2.join();
		prt3.join();
		pw.close();
	}

	/**
	 * It generates administrator product report. <br>
	 * @param fileName It is the name of file.
	 * @param separator It is the separator of the information.
	 */
	
	public void generateAdministratorProductReport(String fileName, String separator) throws FileNotFoundException, InterruptedException {
		PrintWriter pw = new PrintWriter(fileName);
		int mid = (generalProductList.size()-1)/3;
		ProductReportThread prt1 = new ProductReportThread(pw, generalProductList, 0, mid, separator);
		ProductReportThread prt2 = new ProductReportThread(pw, generalProductList, mid+1, (mid*2), separator);
		ProductReportThread prt3 = new ProductReportThread(pw, generalProductList, (mid*2)+1, generalProductList.size()-1, separator);
		prt1.start();
		prt2.start();
		prt3.start();
		prt1.join();
		prt2.join();
		prt3.join();
		pw.close();
	}


	/**
	 * It generates an order report. <br>
	 * @param fileName It is the name of file.
	 * @param separator It is the separator of the information.
	 **@param minDate It is the minimum date.
	 * @param maxDate It is the maximum date.
	 */
	
	public void generateOrderReport(String fileName, String separator, LocalDateTime minDate, LocalDateTime maxDate) throws FileNotFoundException, InterruptedException {
		PrintWriter pw = new PrintWriter(fileName);
		int mid = (orderList.size()-1)/3;
		OrderReportThread ort1 = new OrderReportThread(pw, orderList, 0, mid, separator, minDate, maxDate);
		OrderReportThread ort2 = new OrderReportThread(pw, orderList, mid+1, (mid*2), separator, minDate, maxDate);
		OrderReportThread ort3 = new OrderReportThread(pw, orderList, (mid*2)+1, orderList.size()-1, separator, minDate, maxDate);
		ort1.start();
		ort2.start();
		ort3.start();
		ort1.join();
		ort2.join();
		ort3.join();
		pw.close();
	}

	//Importing methods.

	/**
	 * It imports products information. <br>
	 * @param fileName It is the name of file.
	 * @param separator It is the separator of the information.
	 * @param seller It is the seller.
	 */

	public void importProducts(String fileName, String separator, Seller seller) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line != null) {
			String[] productsData = line.split(separator);
			String name = productsData[0];
			Category category = searchCategory(productsData[1]);
			String brand = productsData[2];
			int price = Integer.valueOf(productsData[3]);
			int stock = Integer.valueOf(productsData[4]);
			String description = productsData[5];
			RequestType requestType = RequestType.ADD;
			if(category != null) {
				addRequestWithoutSaving(name, category, brand, price, stock, description, seller, requestType);
			}
			line = br.readLine();
		}
		saveAll();
		br.close();
	}

	//Saving methods.

	/**
	 * It saves products. <br>
	 */

	public void saveProducts() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GENERAL_PRODUCT_LIST_NAME));
		oos.writeObject(generalProductList);
		oos.close();
	}

	/**
	 * It saves accounts. <br>
	 */

	public void saveAccounts() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_LIST_NAME));
		oos.writeObject(accountList);
		oos.close();
	}

	/**
	 * It saves orders. <br>
	 */

	public void saveOrders() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDER_LIST_NAME));
		oos.writeObject(orderList);
		oos.close();
	}

	/**
	 * It saves categories. <br>
	 */

	public void saveCategories() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CATEGORY_LIST_NAME));
		oos.writeObject(categoryList);
		oos.close();
	}

	/**
	 * It saves payment methods. <br>
	 */

	public void savePaymentMethods() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PAYMENT_METHODS_NAME));
		oos.writeObject(paymentMethods);
		oos.close();
	}

	/**
	 * It saves requests. <br>
	 */

	public void saveRequests() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REQUEST_LIST_NAME));
		oos.writeObject(requestList);
		oos.close();
	}

	//Loading methods.

	/**
	 * It loads the products. <br>
	 */

	@SuppressWarnings("unchecked")
	public boolean loadProducts() throws NullPointerException, IOException, ClassNotFoundException, FileNotFoundException {
		File file = new File(GENERAL_PRODUCT_LIST_NAME);
		boolean loaded = false;
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			generalProductList = (List<Product>)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	/**
	 * It loads the accounts. <br>
	 */

	public boolean loadAccounts() throws NullPointerException, IOException, ClassNotFoundException, FileNotFoundException {
		File file = new File(ACCOUNT_LIST_NAME);
		boolean loaded = false;
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			accountList = (Account)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	/**
	 * It loads the orders. <br>
	 */

	@SuppressWarnings("unchecked")
	public boolean loadOrders() throws NullPointerException, IOException, ClassNotFoundException, FileNotFoundException {
		File file = new File(ORDER_LIST_NAME);
		boolean loaded = false;
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			orderList = (List<Order>)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	/**
	 * It loads the categories. <br>
	 */

	public boolean loadCategories() throws NullPointerException, IOException, ClassNotFoundException, FileNotFoundException {
		File file = new File(CATEGORY_LIST_NAME);
		boolean loaded = false;
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			categoryList = (Category)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	/**
	 * It loads the payment methods. <br>
	 */

	public boolean loadPaymentMethods() throws NullPointerException, IOException, ClassNotFoundException, FileNotFoundException {
		File file = new File(PAYMENT_METHODS_NAME);
		boolean loaded = false;
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			paymentMethods = (PaymentMethod)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}

	/**
	 * It loads the requests. <br>
	 */

	@SuppressWarnings("unchecked")
	public boolean loadRequests() throws NullPointerException, IOException, ClassNotFoundException, FileNotFoundException {
		File file = new File(REQUEST_LIST_NAME);
		boolean loaded = false;
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			requestList = (List<Request>)ois.readObject();
			ois.close();
			loaded = true;
		}
		return loaded;
	}




	/**
	 * It saves all the information. <br>
	 */

	@Override
	public void saveAll() throws FileNotFoundException, IOException {
		saveProducts();
		saveAccounts();
		saveOrders();
		saveCategories();
		savePaymentMethods();
		saveRequests();
	}




	/**
	 * It loads all the information. <br>
	 */
	@Override

	public void loadAll() throws NullPointerException, ClassNotFoundException, FileNotFoundException, IOException {
		loadProducts();
		loadAccounts();
		loadOrders();
		loadCategories();
		loadPaymentMethods();
		loadRequests();
	}

}
