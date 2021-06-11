package model;

public class PaymentMethod {

	private String name;
	private PaymentType type;
	private PaymentMethod left;
	private PaymentMethod right;
	private PaymentMethod parent;
	private boolean disabled;
	
	public PaymentMethod(String name, PaymentType type) {
		super();
		this.name = name;
		this.type = type;
		disabled = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public PaymentMethod getLeft() {
		return left;
	}

	public void setLeft(PaymentMethod left) {
		this.left = left;
	}

	public PaymentMethod getRight() {
		return right;
	}

	public void setRight(PaymentMethod right) {
		this.right = right;
	}

	public PaymentMethod getParent() {
		return parent;
	}

	public void setParent(PaymentMethod parent) {
		this.parent = parent;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
}
