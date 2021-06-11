package exceptions;

public class CantAddAccountException extends Exception{
	
	private static final long serialVersionUID = 1;
	
	public CantAddAccountException(String username) {
		super("The account with username: "+ username +" can't be added.");
	}
}