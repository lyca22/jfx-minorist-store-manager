package model;

import java.io.Serializable;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Category next;
	private boolean disabled;
	
	/**
	 *Constructor method for Category. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates a category. <br>
	 *@param name It is the name of the category. <br>
	 */
	
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
