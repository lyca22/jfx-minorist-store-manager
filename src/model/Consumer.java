package model;

import java.util.ArrayList;
import java.util.List;

public class Consumer extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;
	private String surnames;
	private long phoneNumber;
	private String address;
	private List<Order> personalOrderList;

	/**
	 *Constructor method for Consumer. <br>
	 *<b>Pre: </b>  <br>
	 *<b>Post: </b> Creates an account for a consumer. <br>
	 *@param username It is the username used in the account. <br>
	 *@param password It is the password of the account. <br>
	 *@param names It is the name(s) of the consumer. <br>
	 *@param surnames It is the surname(s) of the consumer. <br>
	 *@param phoneNumber It is the phone number of the consumer. <br>
	 *@param address It is the address of the consumer. <br>
	 */
	
	public Consumer(String username, String password, String names, String surnames, long phoneNumber, String address) {
		super(username, password);
		this.names = names;
		this.surnames = surnames;
		this.phoneNumber = phoneNumber;
		this.address = address;
		personalOrderList = new ArrayList<Order>();
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

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getPersonalOrderList() {
		return personalOrderList;
	}

	public void setPersonalOrderList(List<Order> personalOrderList) {
		this.personalOrderList = personalOrderList;
	}

}
