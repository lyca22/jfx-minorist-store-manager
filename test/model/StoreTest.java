package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class StoreTest {
	private MinoristStore minoristStore;

	public void setupScenary1() {
		minoristStore = new MinoristStore();

		minoristStore.addSellerAccount("apple", "12345", "Apple Inc.");

		Category testCategory = new Category("Electronic devices");
		Product testProduct = new Product(82498429, "iPhone 11", testCategory, "Apple", 2300000, 10, "This is a phone");

		minoristStore.addProduct(testProduct);
	}


	public void setupScenary2() {
		minoristStore = new MinoristStore();

		minoristStore.addSellerAccount("apple", "12345", "Apple Inc.");
		minoristStore.addSellerAccount("official-LG", "2468", "LG");

		Category testCategory = new Category("Electronic devices");
		Category testCategory2 = new Category("Home Appliances");

		Product testProduct = new Product(82498429, "iPhone 11", testCategory, "Apple", 2300000, 10, "This is a phone");
		Product testProduct2 = new Product(56789012, "WashTowers", testCategory2, "LG", 1890000, 4,  "This is a washin machine");

		minoristStore.addProduct(testProduct);
		minoristStore.addProduct(testProduct2);

		List<Product> productList = new ArrayList<Product>();
		productList.add(testProduct2);
		List<Integer> quantity = new ArrayList<Integer>();
		quantity.add(1);

		Consumer testClient = new Consumer("ArielP", "abcd", "Ariel", "Pabon", 234567547, "Cr 78 # 2-21");
		PaymentMethod paymentMethod = new PaymentMethod("ArielP", PaymentType.CARD);
		PaymentInformation paymentInfo = new PaymentInformation(paymentMethod, 123456);
		LocalDateTime date = LocalDateTime.now();

		Order testOrder = new Order(1234567, date, testClient, productList, quantity, 1890000, OrderState.REQUESTED, paymentInfo);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(testOrder);
	}


	//Add product correctly
	@Test
	public void testAddProduct() {
		setupScenary1();

		Seller testSeller = new Seller("apple", "12345", "Apple Inc.");
		Category testCategory = new Category("Electronic devices");
		Product product = new Product(234576, "iMac", testCategory, "Apple", 7300000, 5, "This is a desktop computer");

		minoristStore.addProduct(product);

		//boolean added = minoristStore.addProduct(product);
		//assertTrue(added);

		List<Product> productList = testSeller.getProductList(); 
		assertEquals(2,  productList);
	}

	@Test
	public void testAddProduct2() {
		setupScenary2();
	}



	//Add account correctly
	@Test
	public void testAddAccount() {
		setupScenary1();
	}

	@Test
	public void testAddAccount2() {
		setupScenary2();
	}


	//Can´t add an account
	@Test
	public void testCantAddAccount() {
		setupScenary1();
	}

	public void testCantAddAccount2() {
		setupScenary2();
	}

	//Add order correctly
	@Test
	public void testAddOrder() {
		setupScenary1();
	}

	@Test
	public void testAddOrder2() {
		setupScenary2();
	}

	//Add category correctly
	@Test
	public void testAddCategory() {
		setupScenary1();
	}

	@Test
	public void testAddCategory2() {
		setupScenary2();
	}


	//Remove product
	@Test
	public void testRemoveProduct() {
		setupScenary1();
	}

	@Test
	public void testRemoveProduct2() {
		setupScenary2();
	}

	//Can´t remove an account
	@Test
	public void testCantRemoveAccount() {
		setupScenary1();
	}

	@Test
	public void testCantRemoveAccount2() {
		setupScenary2();
	}

	//Can´t remove a category
	@Test
	public void testCantRemoveCategory() {
		setupScenary1();
	}

	@Test
	public void testCantRemoveCategory2() {
		setupScenary2();
	}


	//Update a product
	@Test
	public void testUpdateProduct() {
		setupScenary1();
	}

	@Test
	public void testUpdateProduct2() {
		setupScenary2();
	}

	//Update an account
	@Test
	public void testUpdateAccount() {
		setupScenary1();
	}

	@Test
	public void testUpdateAccount2() {
		setupScenary2();
	}

	//Update an order
	@Test
	public void testUpdateOrder() {
		setupScenary1();
	}

	@Test
	public void testUpdateOrder2() {
		setupScenary2();
	}

	//Update a category
	@Test
	public void testUpdateCategory() {
		setupScenary1();
	}

	@Test
	public void testUpdateCategory2() {
		setupScenary2();
	}

	//Find product
	@Test
	public void testFindProduct() {
		setupScenary1();
	}

	@Test
	public void testFindProduct2() {
		setupScenary2();
	}

	//Find account
	@Test
	public void testFindAccount() {
		setupScenary1();
	}

	@Test
	public void testFindAccount2() {
		setupScenary2();
	}
}
