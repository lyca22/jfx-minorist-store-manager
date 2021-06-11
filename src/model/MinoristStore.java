package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.CantAddAccountException;
import exceptions.CantAddCategoryException;
import exceptions.CantAddPaymentMethodException;

public class MinoristStore {

	private List<Product> generalProductList;
	private Account accountList;
	private List<Order> orderList;
	private Category categoryList;
	private PaymentMethod paymentMethods;
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

	public void addProduct(Product product) {
		try {
			generalProductList.add(product);
			Product copy = cloneProduct(product);
			product.getSellerList().get(0).getProductList().add(copy);
			sortProductBySelection(generalProductList);
			sortProductBySelection(product.getSellerList().get(0).getProductList());
		} catch (CloneNotSupportedException e) {
		}
	}

	public long addRequest(String name, Category category, String brand, int price, int stock, String description, Seller seller, RequestType requestType) {
		long ID = randomNumberWithRange(1, Integer.MAX_VALUE);
		Product product = new Product(ID, name, category, brand, price, stock, description);
		product.getSellerList().add(seller);
		Request request = new Request(product, requestType, seller);
		requestList.add(request);
		sortRequestsByInsertion(requestList);
		return ID;
	}

	public long addRequest(long ID, String name, Category category, String brand, int price, int stock, String description, Seller seller, RequestType requestType) {
		Product product = new Product(ID, name, category, brand, price, stock, description);
		Request request = new Request(product, requestType, seller);
		requestList.add(request);
		sortRequestsByInsertion(requestList);
		return ID;
	}

	public long addRequest(Product product, Seller seller, RequestType requestType) {
		Request request = new Request(product, requestType, seller);
		requestList.add(request);
		sortRequestsByInsertion(requestList);
		return product.getID();
	}

	public boolean addAdministratorAccount(String username, String password, String names, String surnames) throws CantAddAccountException{
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
		return added;
	}

	public boolean addConsumerAccount(String username, String password, String names, String surnames, long phoneNumber, String address) throws CantAddAccountException {
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
		return added;
	}

	public boolean addSellerAccount(String username, String password, String tradeName) throws CantAddAccountException {
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
		return added;
	}

	private void addAccount(Account current, Account newAccount) {
		if(current.getNext() == null) {
			current.setNext(newAccount);
		}else {
			addAccount(current.getNext(), newAccount);
		}
	}

	public boolean addCategory(String name) throws CantAddCategoryException {
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
		return added;
	}

	private void addCategory(Category currentCategory, Category newCategory) {
		if(currentCategory.getNext() == null) {
			currentCategory.setNext(newCategory);
		}else {
			addCategory(currentCategory.getNext(), newCategory);
		}
	}

	public boolean addPaymentMethod(String name, PaymentType type) throws CantAddPaymentMethodException {
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
		return added;
	}

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

	public void addOrder(Consumer client, PaymentInformation paymentInformation, ArrayList<Product> productList, ArrayList<Integer> quantity) {
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
		//TODO. Sort orderList. Sort personalOrderList.
	}

	//Deleting methods. ¿Concurrence?

	public void deleteProduct(Product product) {
		boolean canDelete = true;
		for(int i = 0; i <= orderList.size()-1; i++) {
			List<Product> products = orderList.get(i).getProductList();
			for(int j = 0; j <= products.size()-1; j++) {
				if(products.get(i).getID() == product.getID()) {
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
				Product sellerProduct = searchProduct(product.getID(), product.getSellerList().get(i));
				product.getSellerList().get(i).getProductList().remove(sellerProduct);
			}
			generalProductList.remove(product);
		}
	}

	public void deleteOrder(Order order) {
		order.getClient().getPersonalOrderList().remove(order);
		orderList.remove(order);
	}

	public void deleteCategory(Category category) {
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
		}
	}

	public void deletePaymentMethod(PaymentMethod paymentMethod) {
		boolean canDelete = true;
		for(int i = 0; i <= orderList.size()-1; i++) {
			if(orderList.get(i).getPaymentInformation().getPaymentMethod().equals(paymentMethod)) {
				canDelete = false;
			}
		}
		if(canDelete) {
			delete(paymentMethod);
		}
	}

	public void delete(PaymentMethod paymentMethod) {
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

	public PaymentMethod getMin(PaymentMethod current) {
		while(current.getRight() != null) {
			current = current.getRight();
		}
		return current;
	}

	public void deleteRequest(Request request) {
		requestList.remove(request);
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

	//Needs to be implemented in the program. TODO.

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

	public Product searchProduct(Long ID) {
		Product product = null;
		for(int i = 0; i <= generalProductList.size()-1; i++) {
			if(generalProductList.get(i).getID() == ID) {
				product = generalProductList.get(i);
			}
		}
		return product;
	}

	public Product searchProduct(Long ID, Seller seller) {
		Product product = null;
		for(int i = 0; i <= seller.getProductList().size()-1; i++) {
			if(seller.getProductList().get(i).getID() == ID) {
				product = seller.getProductList().get(i);
			}
		}
		return product;
	}

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

	public boolean editCategory(Category category, String newName) {
		boolean edited = false;
		Category current = searchCategory(newName);
		if(current == null) {
			edited = true;
			category.setName(newName);
		}
		return edited;
	}

	public boolean editPaymentMethod(PaymentMethod paymentMethod, String newName, PaymentType newPaymentType) {
		boolean edited = false;
		PaymentMethod current = searchPaymentMethod(newName);
		if(current == null) {
			edited = true;
			paymentMethod.setName(newName);
			paymentMethod.setType(newPaymentType);
		}
		return edited;
	}

	private long randomNumberWithRange(long min, long max) {
		long range = (max - min) + 1;
		return (long)(Math.random() * range) + min;
	}

}
