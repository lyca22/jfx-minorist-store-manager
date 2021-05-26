package model;

import java.util.List;

public class Consumer extends Account {

	private String names;
	private String surnames;
	private long phoneNumber;
	private String address;
	private List<Order> personalOrderList;
	
	public Consumer(String username, String password, String names, String surnames, long phoneNumber, String address,
			List<Order> personalOrderList) {
		super(username, password);
		this.names = names;
		this.surnames = surnames;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.personalOrderList = personalOrderList;
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
