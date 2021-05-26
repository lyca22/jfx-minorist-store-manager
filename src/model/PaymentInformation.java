package model;

public class PaymentInformation {

	private PaymentMethod paymentMethod;
	private int zipCode;
	
	public PaymentInformation(PaymentMethod paymentMethod, int zipCode) {
		super();
		this.paymentMethod = paymentMethod;
		this.zipCode = zipCode;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
}
