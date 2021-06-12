package model;

public class Card extends PaymentInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardOwner;
	private int expirationMonth;
	private int expirationYear;
	private long cardNumber;

	/**
	 *Constructor method for Card <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates a card. <br>
	 *@param paymentMethod 
	 *@param zipCode 
	 *@param cardOwner 
	 *@param expirationMonth 
	 *@param expirationYear 
	 *@param cardNumber 
	 */
	
	public Card(PaymentMethod paymentMethod, int zipCode, String cardOwner, int expirationMonth, int expirationYear,
			long cardNumber) {
		super(paymentMethod, zipCode);
		this.cardOwner = cardOwner;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cardNumber = cardNumber;
	}
	public String getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	public int getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public int getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

}
