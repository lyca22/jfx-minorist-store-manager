package model;

public class Category {

	private String name;
	private Category next;
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getNext() {
		return next;
	}

	public void setNext(Category next) {
		this.next = next;
	}
	
}
