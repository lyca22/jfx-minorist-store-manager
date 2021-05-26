package model;

public class OnlineSystem extends PaymentInformation {

	private String username;

	public OnlineSystem(PaymentMethod paymentMethod, int zipCode, String username) {
		super(paymentMethod, zipCode);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
