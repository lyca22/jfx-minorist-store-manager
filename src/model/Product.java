package model;

public class Product {

	private long ID;
	private String name;
	private String brand;
	private int price;
	private int stock;
	private String description;
	private Seller sellerList;
	
	public Product(long iD, String name, String brand, int price, int stock, String description, Seller sellerList) {
		super();
		ID = iD;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.sellerList = sellerList;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Seller getSellerList() {
		return sellerList;
	}

	public void setSellerList(Seller sellerList) {
		this.sellerList = sellerList;
	}
	
}
