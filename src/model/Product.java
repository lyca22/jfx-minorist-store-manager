package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Cloneable, Serializable, Cost{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long ID;
	private String name;
	private Category category;
	private String brand;
	private int price;
	private int stock;
	private String description;
	private ArrayList<Seller> sellerList;
	private boolean disabled;
	private int salesNumber;
	
	/**
	 *Constructor method for Product. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates a product. <br>
	 *@param iD It is the iD of the product. <br>
	 *@param name It is the name of the product. <br>
	 *@param category It is the category of the product. <br>
	 *@param brand It is the brand of the product. <br>
	 *@param price It is the price of the product. <br>
	 *@param stock It is the number of products available. <br>
	 *@param description It is the description of the product. <br>
	 */
	
	public Product(long iD, String name, Category category, String brand, int price, int stock, String description) {
		super();
		ID = iD;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.description = description;
		sellerList = new ArrayList<Seller>();
		disabled = false;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public ArrayList<Seller> getSellerList() {
		return sellerList;
	}

	public void setSellerList(ArrayList<Seller> sellerList) {
		this.sellerList = sellerList;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public int getSalesNumber() {
		return salesNumber;
	}

	public void setSalesNumber(int salesNumber) {
		this.salesNumber = salesNumber;
	}

	@Override
	public int calculatePrice() {
		return salesNumber*price;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}
