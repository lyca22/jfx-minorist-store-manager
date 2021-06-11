package exceptions;

public class CantAddPaymentMethodException extends Exception{
	
	private static final long serialVersionUID = 1;
	
	public CantAddPaymentMethodException(String name) {
		super("The payment method with name: "+ name +" can't be added.");
	}
}