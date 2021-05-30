package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import model.Account;
import model.Administrator;
import model.Consumer;
import model.MinoristStore;
import model.Seller;

public class MinoristStoreGUI {

	private MinoristStore minoristStore;
	private Account actualAccount;
	private ButtonType acceptButtonType;
	private ButtonType cancelButtonType;
	private ButtonType deleteButtonType;
	private ButtonType disableButtonType;
	private ButtonType enableButtonType;
	
	@FXML
	private GridPane mainPane;
	
	@FXML
	private GridPane mainMenuPane;
	
	@FXML
	Dialog<String> dialog;
	
	@FXML
	Dialog<String> alertDialog;

	@FXML
	ButtonType okayButton;
	
	@FXML
	private TextField txtClientUsername;
	
	@FXML
	private PasswordField txtClientPassword;
	
	@FXML
	private TextField txtClientFirstNames;
	
	@FXML
	private TextField txtClientLastNames;
	
	@FXML
	private TextField txtClientPhone;
	
	@FXML
	private TextField txtClientAddress;
	
	@FXML
	private TextField txtAdminUsername;
	
	@FXML
	private PasswordField txtAdminPassword;
	
	@FXML
	private TextField txtAdminFirstNames;
	
	@FXML
	private TextField txtAdminLastNames;
	
	@FXML
	private TextField txtSellerUsername;
	
	@FXML
	private PasswordField txtSellerPassword;
	
	@FXML
	private TextField txtSellerTradeName;
	
	@FXML
	private TextField txtLoginUsername;
	
	@FXML
	private PasswordField txtLoginPassword;
	
	@FXML
	private Label txtClientUsernameProfile;
	
	@FXML
	private Label txtClientFirstNameProfile;
	
	@FXML
	private Label txtClientLastNameProfile;
	
	@FXML
	private Label txtClientPhoneProfile;
	
	@FXML
	private Label txtClientAddressProfile;
	
	@FXML
	private Label txtAdminUsernameProfile;
	
	@FXML
	private Label txtAdminFirstNameProfile;
	
	@FXML
	private Label txtAdminLastNameProfile;
	
	@FXML
	private Label txtSellerUsernameProfile;
	
	@FXML
	private Label txtSellerTradename;
	
	public MinoristStoreGUI(MinoristStore minoristStore) {
		super();
		this.setMinoristStore(minoristStore);
		alertDialog = new Dialog<>();
		okayButton = new ButtonType("OK", ButtonData.OK_DONE);
		alertDialog.getDialogPane().getButtonTypes().add(okayButton);
		setAcceptButtonType(new ButtonType("Accept", ButtonData.APPLY));
		setCancelButtonType(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
		setDeleteButtonType(new ButtonType("Delete", ButtonData.NO));
		setDisableButtonType(new ButtonType("Disable", ButtonData.OTHER));
		setEnableButtonType(new ButtonType("Enable", ButtonData.OK_DONE));
	}

	public MinoristStore getMinoristStore() {
		return minoristStore;
	}

	public void setMinoristStore(MinoristStore minoristStore) {
		this.minoristStore = minoristStore;
	}
	
	public Account getActualAccount() {
		return actualAccount;
	}

	public void setActualAccount(Account actualAccount) {
		this.actualAccount = actualAccount;
	}

	public ButtonType getAcceptButtonType() {
		return acceptButtonType;
	}

	public void setAcceptButtonType(ButtonType acceptButtonType) {
		this.acceptButtonType = acceptButtonType;
	}

	public ButtonType getCancelButtonType() {
		return cancelButtonType;
	}

	public void setCancelButtonType(ButtonType cancelButtonType) {
		this.cancelButtonType = cancelButtonType;
	}

	public ButtonType getDeleteButtonType() {
		return deleteButtonType;
	}

	public void setDeleteButtonType(ButtonType deleteButtonType) {
		this.deleteButtonType = deleteButtonType;
	}

	public ButtonType getDisableButtonType() {
		return disableButtonType;
	}

	public void setDisableButtonType(ButtonType disableButtonType) {
		this.disableButtonType = disableButtonType;
	}

	public ButtonType getEnableButtonType() {
		return enableButtonType;
	}

	public void setEnableButtonType(ButtonType enableButtonType) {
		this.enableButtonType = enableButtonType;
	}

	public void loadScreen(String resource) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
		fxmlLoader.setController(this);
		Parent loginPane;
		try {
			loginPane = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.add(loginPane, 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMainMenuScreen(String resource) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
		fxmlLoader.setController(this);
		Parent loginPane;
		try {
			loginPane = fxmlLoader.load();
			mainMenuPane.getChildren().clear();
			mainMenuPane.add(loginPane, 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openWindow(String resource) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
		fxmlLoader.setController(this);
		dialog = new Dialog<String>();
		dialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);
		Parent root;
		try {
			root = fxmlLoader.load();
			dialog.getDialogPane().setContent(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAlert(String text) {
		alertDialog.getDialogPane().setHeaderText(text);
		alertDialog.showAndWait();
	}
	
	public void loginScreen() {
		loadScreen("login.fxml");
	}
	
	public void signUp() {
		loadScreen("choose-user-type.fxml");
	}

	public void useAsClient() {
		loadScreen("create-client-account.fxml");
	}
	
	public void useAsSeller() {
		loadScreen("create-seller-account.fxml");
	}
	
	public void useAsAdministrator() {
		loadScreen("create-admin-account.fxml");
	}
	
	public void createClientAccount() {
		boolean wroteUsername = !txtClientUsername.getText().isEmpty();
		boolean wrotePassword = !txtClientPassword.getText().isEmpty();
		boolean wroteFirstNames = !txtClientFirstNames.getText().isEmpty();
		boolean wroteLastNames = !txtClientLastNames.getText().isEmpty();
		boolean wrotePhone = !txtClientPhone.getText().isEmpty();
		boolean wroteAddress = !txtClientAddress.getText().isEmpty();
		if(wroteUsername & wrotePassword & wroteFirstNames & wroteLastNames & wrotePhone & wroteAddress) {
			try {
				String username = txtClientUsername.getText();
				String password = txtClientPassword.getText();
				String firstNames = txtClientFirstNames.getText();
				String lastNames = txtClientLastNames.getText();
				long phone = Long.parseLong(txtClientPhone.getText());
				String address = txtClientAddress.getText();
				boolean created = minoristStore.addConsumerAccount(username, password, firstNames, lastNames, phone, address);
				if(created) {
					showAlert("Account Created!");
				}else {
					showAlert("This account already exists!");
				}
			}catch(NumberFormatException nfe) {
				showAlert("Please fill the fields with the correct information.");
			}
		}else {
			showAlert("Please fill all the fields.");
		}
	}

	public void createSellerAccount() {
		boolean wroteUsername = !txtSellerUsername.getText().isEmpty();
		boolean wrotePassword = !txtSellerPassword.getText().isEmpty();
		boolean wroteTradeName = !txtSellerTradeName.getText().isEmpty();
		if(wroteUsername & wrotePassword & wroteTradeName) {
				String username = txtSellerUsername.getText();
				String password = txtSellerPassword.getText();
				String tradeName = txtSellerTradeName.getText();
				boolean created = minoristStore.addSellerAccount(username, password, tradeName);
				if(created) {
					showAlert("Account Created!");
				}else {
					showAlert("This account already exists!");
				}
		}else {
			showAlert("Please fill all the fields.");
		}
	}

	public void createAdministratorAccount() {
		boolean wroteUsername = !txtAdminUsername.getText().isEmpty();
		boolean wrotePassword = !txtAdminPassword.getText().isEmpty();
		boolean wroteFirstNames = !txtAdminFirstNames.getText().isEmpty();
		boolean wroteLastNames = !txtAdminLastNames.getText().isEmpty();
		if(wroteUsername & wrotePassword & wroteFirstNames & wroteLastNames) {
				String username = txtAdminUsername.getText();
				String password = txtAdminPassword.getText();
				String firstNames = txtAdminFirstNames.getText();
				String lastNames = txtAdminLastNames.getText();
				boolean created = minoristStore.addAdministratorAccount(username, password, firstNames, lastNames);
				if(created) {
					showAlert("Account Created!");
				}else {
					showAlert("This account already exists!");
				}
		}else {
			showAlert("Please fill all the fields.");
		}
	}

	public void logIn() {
		boolean wroteUsername = !txtLoginUsername.getText().isEmpty();
		boolean wrotePassword = !txtLoginPassword.getText().isEmpty();
		if(wroteUsername & wrotePassword) {
			String username = txtLoginUsername.getText();
			String password = txtLoginPassword.getText();
			actualAccount = minoristStore.searchAccount(username);
			if(actualAccount != null) {
				if(actualAccount.getPassword().equals(password)) {
					loadScreen("main-menu.fxml");
					loadMainMenuScreen("show-products-pane.fxml");
				}else {
					showAlert("Wrong password.");
				}
			}else {
				showAlert("The account doesn't exist.");
			}
		}else {
			showAlert("Please enter the login information.");
		}
	}

	public void searchProduct() {
		
	}

	public void showProduct() {
		loadMainMenuScreen("products-pane.fxml");
	}

	public void editProduct() {
		openWindow("add-products.fxml");
		dialog.showAndWait();
	}

	public void addToCart() {
		showAlert("Added to the cart!");
	}

	public void sellThisProduct() {
		openWindow("add-products.fxml");
		dialog.showAndWait();
	}

	public void cart() {
		loadMainMenuScreen("cart.fxml");
	}

	public void selectPayment() {
		loadMainMenuScreen("payment-method.fxml");
	}

	public void buyCart() {
		showAlert("An order was created!");
		loadScreen("main-menu.fxml");
		loadMainMenuScreen("show-products-pane.fxml");
	}

	public void myAccount() {
		if(actualAccount instanceof Administrator) {
			loadMainMenuScreen("admin-profile.fxml");
			txtAdminUsernameProfile.setText(actualAccount.getUsername());
			txtAdminFirstNameProfile.setText(((Administrator) actualAccount).getNames());
			txtAdminLastNameProfile.setText(((Administrator) actualAccount).getSurnames());
		}else if(actualAccount instanceof Seller) {
			loadMainMenuScreen("seller-profile.fxml");
			txtSellerUsernameProfile.setText(actualAccount.getUsername());
			txtSellerTradename.setText(((Seller) actualAccount).getTradeName());
		}else if(actualAccount instanceof Consumer){
			loadMainMenuScreen("client-profile.fxml");
			txtClientUsernameProfile.setText(actualAccount.getUsername());
			txtClientFirstNameProfile.setText(((Consumer) actualAccount).getNames());
			txtClientLastNameProfile.setText(((Consumer) actualAccount).getSurnames());
			txtClientPhoneProfile.setText(Long.toString(((Consumer) actualAccount).getPhoneNumber()));
			txtClientAddressProfile.setText(((Consumer) actualAccount).getAddress());
		}
	}

	public void seeProducts() {
		loadScreen("main-menu.fxml");
		loadMainMenuScreen("show-products-pane.fxml");
	}
	
	public void manageCategories() {
		loadMainMenuScreen("manage-categories.fxml");
	}
	
	public void manageRequests() {
		loadMainMenuScreen("check-requests.fxml");
	}
	
	public void manageOrders() {
		loadMainMenuScreen("order-list.fxml");
	}
	
	public void exportData() {
		loadMainMenuScreen("export.fxml");
	}
	
	public void importData() {
		openWindow("import.fxml");
		dialog.showAndWait();
	}
	
	public void addProducts() {
		openWindow("add-products.fxml");
		dialog.showAndWait();
	}
	
	public void editProfileInfo() {
		openWindow("edit-profile-info.fxml");
		dialog.showAndWait();
	}
	
	public void editProfilePicture() {
		openWindow("edit-profile-picture.fxml");
		dialog.showAndWait();
	}
	
}
