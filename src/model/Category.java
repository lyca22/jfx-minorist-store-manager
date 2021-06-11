package model;

public class Category {

	private String name;
	private Category next;
	private boolean disabled;
	
	public Category(String name) {
		super();
		this.name = name;
		disabled = false;
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
}
