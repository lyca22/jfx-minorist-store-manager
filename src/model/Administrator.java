package model;

public class Administrator extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;
	private String surnames;
	
	/**
	 *Constructor method for Administrator. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates an account for an administrator. <br>
	 *@param username It is the username used in the account. <br>
	 *@param password It is the password of the account. <br>
	 *@param names It is the name(s) of the user. <br>
	 *@param surnames It is the surname(s) of the user. <br>
	 */
	public Administrator(String username, String password, String names, String surnames) {
		super(username, password);
		this.names = names;
		this.surnames = surnames;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	
}
