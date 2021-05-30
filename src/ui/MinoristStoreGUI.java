package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import model.MinoristStore;

public class MinoristStoreGUI {

	private MinoristStore minoristStore;
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
		showAlert("Account Created!");
	}

	public void createSellerAccount() {
		showAlert("Account Created!");
	}

	public void createAdministratorAccount() {
		showAlert("Account Created!");
	}

	public void logIn() {
		loadScreen("main-menu.fxml");
		loadMainMenuScreen("show-products-pane.fxml");
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
		loadMainMenuScreen("admin-profile.fxml");
	}

	public void seeProducts() {
		
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
		
	}
	
	public void addProducts() {
		
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
