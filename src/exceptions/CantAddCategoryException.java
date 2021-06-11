package exceptions;


public class CantAddCategoryException extends Exception{
	
	private static final long serialVersionUID = 1;
	
	public CantAddCategoryException(String name) {
		super("The category with name: "+ name +" can't be added.");		
	}

}