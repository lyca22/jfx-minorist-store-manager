package model;

public class Administrator extends Account {

	private String names;
	private String surnames;
	
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
