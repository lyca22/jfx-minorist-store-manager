package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StoreTest {
	private MinoristStore minoristStore;

	public void setupScenary1() {
		minoristStore = new MinoristStore();
		
		Seller testSeller = new Seller("apple", "12345", "Apple Inc.");
		Category testCategory = new Category("Electronic devices");
		Product testProduct = new Product(82498429, "iPhone 11", testCategory, "Apple", 2300000, 10, "This is a phone", testSeller);
		minoristStore.addProduct(testProduct);
	}

	public void setupScenary2() {
		minoristStore = new MinoristStore();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
