package model;

public class OnlineSystem extends PaymentInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	
	/**
	 *Constructor method for OnlineSystem. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates an online system. <br>
	 *@param paymentMethod It is the payment method used in the account. <br>
	 *@param zipCode It is the zip code of the payment method. <br>
	 *@param username It is the username used in the account. <br>
	 */
	
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
