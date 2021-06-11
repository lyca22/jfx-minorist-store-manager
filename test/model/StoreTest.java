package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class StoreTest {
	private MinoristStore minoristStore;

	public void setupScenary1() {
		minoristStore = new MinoristStore();

		Seller testSeller = new Seller("apple", "12345", "Apple Inc.");
		minoristStore.addSellerAccount("apple", "12345", "Apple Inc.");

		Category testCategory = new Category("Electronic devices");
		minoristStore.addCategory("Electronic devices");

		Product testProduct = new Product(82498429, "iPhone 11", testCategory, "Apple", 2300000, 10, "This is a phone");
		testProduct.getSellerList().add(testSeller);
		minoristStore.addProduct(testProduct);
	}

	public void setupScenary2() {
		minoristStore = new MinoristStore();

		Seller testSeller = new Seller("apple", "12345", "Apple Inc.");
		minoristStore.addSellerAccount("apple", "12345", "Apple Inc.");
		Seller testSeller2 = new Seller("official-LG", "2468", "LG");
		minoristStore.addSellerAccount("official-LG", "2468", "LG");

		Category testCategory = new Category("Electronic devices");
		minoristStore.addCategory("Electronic devices");
		Category testCategory2 = new Category("Home Appliances");
		minoristStore.addCategory("Home Appliances");

		Product testProduct = new Product(82498429, "iPhone 11", testCategory, "Apple", 2300000, 10, "This is a phone");
		testProduct.getSellerList().add(testSeller);
		minoristStore.addProduct(testProduct);
		Product testProduct2 = new Product(56789012, "WashTowers", testCategory2, "LG", 1890000, 4,  "This is a washin machine");
		testProduct2.getSellerList().add(testSeller2);
		minoristStore.addProduct(testProduct2);

		ArrayList<Product> productList = new ArrayList<Product>();
		productList.add(testProduct2);
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		quantity.add(1);

		Consumer testClient = new Consumer("ArielP", "abcd", "Ariel", "Pabon", 234567547, "Cr 78 # 2-21");
		minoristStore.addConsumerAccount("ArielP", "abcd", "Ariel", "Pabon", 234567547, "Cr 78 # 2-21");
		
		PaymentMethod paymentMethod = new PaymentMethod("BBVA", PaymentType.CARD);
		PaymentInformation paymentInfo = new PaymentInformation(paymentMethod, 123456);
		
		minoristStore.addOrder(testClient, paymentInfo, productList, quantity);
	}


	//Add product correctly
	@Test
	public void testAddProduct() {
		setupScenary1();

		Seller testSeller = new Seller("apple", "12345", "Apple Inc.");
		Category testCategory = new Category("Electronic devices");
		Product product = new Product(234576, "iMac", testCategory, "Apple", 7300000, 5, "This is a desktop computer");
		product.getSellerList().add(testSeller);
		minoristStore.addProduct(product);

		List<Product> productList = minoristStore.getGeneralProductList();
		assertEquals(2, productList.size());
	}

	@Test
	public void testAddProduct2() {
		setupScenary2();
		
		Seller testSeller2 = new Seller("official-LG", "2468", "LG");
		Category testCategory = new Category("Electronic devices");
			
		Product product = new Product(135790, "LG OLED UHD", testCategory, "LG", 6400000, 3, "This is a TV");
		product.getSellerList().add(testSeller2);
		minoristStore.addProduct(product);

		List<Product> productList = minoristStore.getGeneralProductList();
		assertEquals(3, productList.size());
	}


	//Add request correctly
	@Test
	public void testAddRequest() {
		setupScenary1();

		Seller testSeller = new Seller("apple", "12345", "Apple Inc.");
		minoristStore.addSellerAccount("apple", "12345", "Apple Inc.");

		Category testCategory = new Category("Electronic devices");
		minoristStore.addCategory("Electronic devices");

		Product testProduct = new Product(82498411, "iPhone 6s", testCategory, "Apple", 1300000, 3, "This is a phone");
		testProduct.getSellerList().add(testSeller);
		minoristStore.addProduct(testProduct);

		RequestType requestType = RequestType.ADD;  

		minoristStore.addRequest(82498411, "iPhone 6s", testCategory, "Apple", 1300000, 3, "This is a phone", testSeller, requestType);

		List<Request> requestList = minoristStore.getRequestList();
		assertEquals(1, requestList.size());
	}

	@Test
	public void testAddRequest2() {
		setupScenary2();

		Seller testSeller = new Seller("official-LG", "2468", "LG");
		minoristStore.addSellerAccount("official-LG", "2468", "LG");

		Category testCategory = new Category("Home Appliances");
		minoristStore.addCategory("Home Appliances");

		Product testProduct = new Product(13468411, "Fridge", testCategory, "LG", 5250000, 4, "This is a fridge");
		testProduct.getSellerList().add(testSeller);
		minoristStore.addProduct(testProduct);

		RequestType requestType = RequestType.ADD;  

		minoristStore.addRequest(13468411, "Fridge", testCategory, "LG", 5250000, 4, "This is a fridge", testSeller, requestType);

		List<Request> requestList = minoristStore.getRequestList();
		assertEquals(1, requestList.size());
	}


	//Add account correctly
	@Test
	public void testAddAccount() {
		setupScenary1();

		String username = "Admin11";
		String password = "54321"; 
		String names = "Laura";
		String surnames = "Martinez";

		boolean added = minoristStore.addAdministratorAccount(username, password, names, surnames);

		assertTrue(added);
	}

	@Test
	public void testAddAccount2() {
		setupScenary2();

		String username = "Juan01";
		String password = "0000"; 
		String tradeName = "Alcatel";

		boolean added = minoristStore.addSellerAccount(username, password, tradeName);

		assertTrue(added);
	}
	

	//Can't add an account
	@Test
	public void testCantAddAccount() {
		setupScenary1();

		String username = "apple";
		String password = "54321"; 
		String tradeName = "Apple1.0";

		boolean added = minoristStore.addSellerAccount(username, password, tradeName);

		assertFalse(added);
	}

	@Test
	public void testCantAddAccount2() {
		setupScenary2();

		String username = "official-LG";
		String password = "0000"; 
		String tradeName = "LG11";

		boolean added = minoristStore.addSellerAccount(username, password, tradeName);

		assertFalse(added);
	}


	//Add category correctly
	@Test
	public void testAddCategory() {
		setupScenary1();

		String name = "Home products";
		boolean added = minoristStore.addCategory(name);

		assertTrue(added);
	}

	@Test
	public void testAddCategory2() {
		setupScenary2();

		String name = "Sports";
		boolean added = minoristStore.addCategory(name);

		assertTrue(added);
	}

	
	//Add payment method
	@Test
	public void testAddPaymentmethod() {
		setupScenary1();

		String name = "Paypal";
		PaymentType type = PaymentType.ONLINE_SYSTEM;
		boolean added = minoristStore.addPaymentMethod(name, type);
		assertTrue(added);
	}

	@Test
	public void testAddPaymentmethod2() {
		setupScenary2();

		String name = "Visa";
		PaymentType type = PaymentType.CARD;
		boolean added = minoristStore.addPaymentMethod(name, type);
		assertTrue(added);
	}

	
	//Add order correctly
	@Test
	public void testAddOrder() {
		setupScenary1();

		Consumer client = new Consumer("JoseP", "abcd", "Jose", "Perez", 234567547, "Cr 66 # 2-21");
		Category testCategory = new Category("Electronic devices");
		Product testProduct = new Product(82498429, "iPhone 11", testCategory, "Apple", 2300000, 10, "This is a phone");
		
		ArrayList<Product> productList = new ArrayList<Product>();
		productList.add(testProduct);
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		quantity.add(1);
		
		PaymentMethod paymentMethod = new PaymentMethod("PayPal", PaymentType.ONLINE_SYSTEM);
		PaymentInformation paymentInfo = new PaymentInformation(paymentMethod, 123456);

		minoristStore.addOrder(client, paymentInfo, productList, quantity);
		
		List<Order> orderList = minoristStore.getOrderList();
		assertEquals(1, orderList.size());
	}

	@Test
	public void testAddOrder2() {
		setupScenary2();
		
		Consumer client = new Consumer("LuisR", "abcd", "Luis", "Reyes", 234567547, "Cr 66 # 2-21");
		Category testCategory = new Category("Home Appliances");
		Product testProduct = new Product(56789012, "WashTowers", testCategory, "LG", 1890000, 4,  "This is a washin machine");
		
		ArrayList<Product> productList = new ArrayList<Product>();
		productList.add(testProduct);
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		quantity.add(2);
		
		PaymentMethod paymentMethod = new PaymentMethod("PayPal", PaymentType.ONLINE_SYSTEM);
		PaymentInformation paymentInfo = new PaymentInformation(paymentMethod, 123456);

		minoristStore.addOrder(client, paymentInfo, productList, quantity);
		
		List<Order> orderList = minoristStore.getOrderList();
		assertEquals(2, orderList.size());
	
	}


	//Search account
	@Test
	public void testSearchAccount() {
		setupScenary1();

		String username = "apple";
		Seller testSeller = new Seller("apple", "12345", "Apple Inc.");
		Account found = minoristStore.searchAccount(username);

		assertEquals(testSeller.getUsername(), found.getUsername());
	}

	@Test
	public void testSearchAccount2() {
		setupScenary2();

		String username = "official-LG";
		Seller testSeller = new Seller("official-LG", "2468", "LG");
		Account found = minoristStore.searchAccount(username);

		assertEquals(testSeller.getUsername(), found.getUsername());
	}


	//Search category
	@Test
	public void testSearchCategory() {
		setupScenary1();

		String name = "Electronic devices";
		Category testCategory = new Category("Electronic devices");
		Category found = minoristStore.searchCategory(name);

		assertEquals(testCategory.getName(), found.getName());
	}

	@Test
	public void testSearchCategory2() {
		setupScenary2();

		String name = "Home Appliances";
		Category testCategory = new Category("Home Appliances");
		Category found = minoristStore.searchCategory(name);

		assertEquals(testCategory.getName(), found.getName());
	}


	//Search product
	@Test
	public void testSearchProduct() {
		setupScenary1();

		long id = 82498429;
		Category testCategory = new Category("Electronic devices");
		Product testProduct = new Product(82498429, "iPhone 11", testCategory, "Apple", 2300000, 10, "This is a phone");

		Product found = minoristStore.searchProduct(id);

		assertEquals(testProduct.getID(), found.getID());
	}

	@Test
	public void testSearchProduct2() {
		setupScenary2();

		long id = 56789012;
		Category testCategory = new Category("Home Appliances");
		Product testProduct = new Product(56789012, "WashTowers", testCategory, "LG", 1890000, 4,  "This is a washin machine");

		Product found = minoristStore.searchProduct(id);

		assertEquals(testProduct.getID(), found.getID());
	}
	

	//Edit a category
	@Test
	public void testEditCategory() {
		setupScenary1();

		String name = "Cellphones";
		Category testCategory = new Category("Electronic devices");
		boolean edited = minoristStore.editCategory(testCategory, name);

		assertTrue(edited);
	}

	@Test
	public void testEditCategory2() {
		setupScenary2();

		String name = "Washing Machines";
		Category testCategory = new Category("Electronic devices");
		boolean edited = minoristStore.editCategory(testCategory, name);

		assertTrue(edited);
	}


	//Edit payment method
	@Test
	public void testEditPaymentMethod2() {
		setupScenary2();
		
		PaymentMethod paymentMethod = new PaymentMethod("BBVA", PaymentType.CARD);
		String newName = "Amazon Pay"; 
		PaymentType newPaymentType = PaymentType.ONLINE_SYSTEM; 

		boolean edited = minoristStore.editPaymentMethod(paymentMethod, newName, newPaymentType);

		assertTrue(edited);
	}


}
