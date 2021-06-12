package model;

public class Account {

	private String username;
	private String password;
	private Account next;

	/**
	 *Constructor method for Account. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates an account. <br>
	 *@param username It is the username used in the account. <br>
	 *@param password It is the password of the account. <br>
	 */

	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getNext() {
		return next;
	}

	public void setNext(Account next) {
		this.next = next;
	}

}
