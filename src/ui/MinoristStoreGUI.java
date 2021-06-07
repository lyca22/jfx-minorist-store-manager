package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Account;
import model.Administrator;
import model.Category;
import model.Consumer;
import model.MinoristStore;
import model.Request;
import model.RequestType;
import model.Seller;

public class MinoristStoreGUI {

	private final static String PROFILE_PICTURE_DIRECTORY = "/data/profile_pictures/";
	private final static String PRODUCT_PICTURE_DIRECTORY = "/data/product_pictures/";

	private MinoristStore minoristStore;
	private Account actualAccount;
	private ButtonType acceptButtonType;
	private ButtonType cancelButtonType;
	private ButtonType deleteButtonType;
	private ButtonType disableButtonType;
	private ButtonType enableButtonType;
	private ButtonType rejectButtonType;

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

	@FXML
	private TextField txtPictureDirectory;

	@FXML
	private ImageView profilePicture;

	@FXML
	private ImageView adminPicture;

	@FXML
	private ImageView clientPicture;

	@FXML
	private ImageView sellerPicture;

	@FXML
	private TextField txtEditProfileInfo;

	@FXML
	private Label labelEditProfileInfo;

	@FXML
	private TextField txtRequestPhoto;

	@FXML
	private TextField txtRequestDescription;

	@FXML
	private TextField txtRequestPrice;

	@FXML
	private TextField txtRequestBrand;

	@FXML
	private TextField txtRequestName;

	@FXML
	private ChoiceBox<String> requestCategory;

	@FXML
	private GridPane categoryPane;

	@FXML
	private Hyperlink editProductButton;

	@FXML
	private Button addToCartButton;

	@FXML
	private Button sellThisProductButton;

    @FXML
    private TableView<Request> tvRequests;

    @FXML
    private TableColumn<Request, String> tcRequestID;

    @FXML
    private TableColumn<Request, String> tcRequestProduct;

    @FXML
    private TableColumn<Request, String> tcRequestCategory;

    @FXML
    private TableColumn<Request, String> tcRequestBrand;
    
    @FXML
    private Label txtCheckRequestName;

    @FXML
    private Label txtCheckRequestBrand;

    @FXML
    private TextArea checkRequestDescription;
	
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
		setRejectButtonType(new ButtonType("Reject", ButtonData.BACK_PREVIOUS));
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

	public ButtonType getRejectButtonType() {
		return rejectButtonType;
	}

	public void setRejectButtonType(ButtonType rejectButtonType) {
		this.rejectButtonType = rejectButtonType;
	}

	private void initializeRequestTableView() {
		ObservableList<Request> observableList;
		observableList = FXCollections.observableArrayList(minoristStore.getRequestList());
		tvRequests.setItems(observableList);
		tcRequestID.setCellValueFactory(new PropertyValueFactory<Request, String>("productID"));
		tcRequestProduct.setCellValueFactory(new PropertyValueFactory<Request, String>("productName"));
		tcRequestCategory.setCellValueFactory(new PropertyValueFactory<Request, String>("productCategoryAsString"));
		tcRequestBrand.setCellValueFactory(new PropertyValueFactory<Request, String>("productBrand"));
		
		tcRequestID.setOnEditStart(t -> checkRequest(t.getTableView().getItems().get(t.getTablePosition().getRow())));
		tcRequestProduct.setOnEditStart(t -> checkRequest(t.getTableView().getItems().get(t.getTablePosition().getRow())));
		tcRequestCategory.setOnEditStart(t -> checkRequest(t.getTableView().getItems().get(t.getTablePosition().getRow())));
		tcRequestBrand.setOnEditStart(t -> checkRequest(t.getTableView().getItems().get(t.getTablePosition().getRow())));
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
	
	public void openRequestWindow(String resource) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
		fxmlLoader.setController(this);
		dialog = new Dialog<String>();
		dialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType, rejectButtonType);
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

	public void loginScreen(ActionEvent event) {
		loadScreen("login.fxml");
	}

	public void signUp(ActionEvent event) {
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

	public void createClientAccount(ActionEvent event) {
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

	public void createSellerAccount(ActionEvent event) {
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

	public void createAdministratorAccount(ActionEvent event) {
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

	public void logIn(ActionEvent event) {
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
					File file = new File(System.getProperty("user.dir") + PROFILE_PICTURE_DIRECTORY + username + ".png");
					Image image = new Image(file.toURI().toString());
					profilePicture.setImage(image);
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

	public void searchProduct(ActionEvent event) {

	}

	public void showProduct() {
		loadMainMenuScreen("products-pane.fxml");
		if(actualAccount instanceof Seller) {
			editProductButton.setVisible(true);
			addToCartButton.setVisible(false);
			sellThisProductButton.setVisible(true);
		}else if(actualAccount instanceof Consumer) {
			editProductButton.setVisible(false);
			addToCartButton.setVisible(true);
			sellThisProductButton.setVisible(false);
		}else if(actualAccount instanceof Administrator) {
			editProductButton.setVisible(false);
			addToCartButton.setVisible(false);
			sellThisProductButton.setVisible(false);
		}
	}

	public void editProduct(ActionEvent event) {
		openWindow("add-products.fxml");
		Category actualCategory = minoristStore.getCategoryList();
		ArrayList<String> categoryList = new ArrayList<String>();
		while(actualCategory != null) {
			categoryList.add(actualCategory.getName());
			actualCategory = actualCategory.getNext();
		}
		requestCategory.setItems(FXCollections.observableArrayList(categoryList));
		
//		TODO. Get current product.
		
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				try {
					boolean wroteProductName = !txtRequestName.getText().isEmpty();
					boolean wroteProductBrand = !txtRequestBrand.getText().isEmpty();
					boolean wroteProductPrice = !txtRequestPrice.getText().isEmpty();
					boolean wroteProductDescription = !txtRequestDescription.getText().isEmpty();
					boolean wroteProductPhoto = !txtRequestPhoto.getText().isEmpty();
					boolean selectedProductCategory = !requestCategory.getSelectionModel().getSelectedItem().equals(null); //NullPointerException, I have to fix this.
					if(wroteProductName && wroteProductBrand && wroteProductPrice && wroteProductDescription && wroteProductPhoto && selectedProductCategory) {
						String productName = txtRequestName.getText();
						String stringProductCategory = requestCategory.getSelectionModel().getSelectedItem();
						Category productCategory = minoristStore.searchCategory(stringProductCategory);
						String productBrand = txtRequestBrand.getText();
						int productPrice = Integer.valueOf(txtRequestPrice.getText());
						String productDescription = txtRequestDescription.getText();
						String productPhoto = txtRequestPhoto.getText();
						Image image = new Image(productPhoto);
						if(actualAccount instanceof Seller) {
							minoristStore.addRequest(0, productName, productCategory, productBrand, productPrice, 0, productDescription, (Seller)actualAccount, RequestType.EDIT);
						}
						savePicture(image, PRODUCT_PICTURE_DIRECTORY, productName);
					}
				}catch(NullPointerException npe) {

				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void addToCart(ActionEvent event) {
		showAlert("Added to the cart!");
	}

	public void sellThisProduct(ActionEvent event) {
		openWindow("edit-profile-info.fxml");
		dialog.showAndWait();
	}

	public void cart(ActionEvent event) {
		loadMainMenuScreen("cart.fxml");
	}

	public void selectPayment(ActionEvent event) {
		loadMainMenuScreen("payment-method.fxml");
	}

	public void buyCart(ActionEvent event) {
		showAlert("An order was created!");
		loadScreen("main-menu.fxml");
		loadMainMenuScreen("show-products-pane.fxml");
	}

	public void myAccount(ActionEvent event) {
		if(actualAccount instanceof Administrator) {
			loadMainMenuScreen("admin-profile.fxml");
			txtAdminUsernameProfile.setText(actualAccount.getUsername());
			txtAdminFirstNameProfile.setText(((Administrator) actualAccount).getNames());
			txtAdminLastNameProfile.setText(((Administrator) actualAccount).getSurnames());
			adminPicture.setImage(loadProfilePicture());
		}else if(actualAccount instanceof Seller) {
			loadMainMenuScreen("seller-profile.fxml");
			txtSellerUsernameProfile.setText(actualAccount.getUsername());
			txtSellerTradename.setText(((Seller) actualAccount).getTradeName());
			sellerPicture.setImage(loadProfilePicture());
		}else if(actualAccount instanceof Consumer){
			loadMainMenuScreen("client-profile.fxml");
			txtClientUsernameProfile.setText(actualAccount.getUsername());
			txtClientFirstNameProfile.setText(((Consumer) actualAccount).getNames());
			txtClientLastNameProfile.setText(((Consumer) actualAccount).getSurnames());
			txtClientPhoneProfile.setText(Long.toString(((Consumer) actualAccount).getPhoneNumber()));
			txtClientAddressProfile.setText(((Consumer) actualAccount).getAddress());
			clientPicture.setImage(loadProfilePicture());
		}
	}

	public void seeProducts(ActionEvent event) {
		loadScreen("main-menu.fxml");
		loadMainMenuScreen("show-products-pane.fxml");
	}

	public void manageCategories(ActionEvent event) {
		loadMainMenuScreen("manage-categories.fxml");
		Category actualCategory = minoristStore.getCategoryList();
		int count = 0;
		while (actualCategory != null) {
			Label label = new Label(actualCategory.getName());
			categoryPane.add(label, 0, count);
			Hyperlink edit = new Hyperlink("Edit");
			edit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					openWindow("edit-profile-info.fxml");
					dialog.setResultConverter(dialogButton ->{
						if(dialogButton == acceptButtonType) {
							boolean wroteCategoryName = !txtEditProfileInfo.getText().isEmpty();
							if(wroteCategoryName) {
								Category editedCategory = minoristStore.searchCategory(label.getText());
								if(editedCategory != null) {
									String categoryName = txtEditProfileInfo.getText();
									minoristStore.editCategory(editedCategory, categoryName);
									manageCategories(event);
								}
							}
						}
						return null;
					});
					dialog.showAndWait();
				}
			});
			categoryPane.add(edit, 1, count);
			actualCategory = actualCategory.getNext();
			count += 1;
		}
		Hyperlink add = new Hyperlink("Add");
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openWindow("edit-profile-info.fxml");
				dialog.setResultConverter(dialogButton ->{
					if(dialogButton == acceptButtonType) {
						boolean wroteCategoryName = !txtEditProfileInfo.getText().isEmpty();
						if(wroteCategoryName) {
							String categoryName = txtEditProfileInfo.getText();
							minoristStore.addCategory(categoryName);
							manageCategories(event);
						}
					}
					return null;
				});
				dialog.showAndWait();
			}
		});
		categoryPane.add(add, 1, count);
	}

	public void manageRequests(ActionEvent event) {
		loadMainMenuScreen("check-requests.fxml");
		initializeRequestTableView();
	}

	public void manageOrders(ActionEvent event) {
		loadMainMenuScreen("order-list.fxml");
	}

	public void exportData(ActionEvent event) {
		loadMainMenuScreen("export.fxml");
	}

	public void importData(ActionEvent event) {
		openWindow("import.fxml");
		dialog.showAndWait();
	}

	public void addProducts(ActionEvent event) {
		openWindow("add-products.fxml");
		Category actualCategory = minoristStore.getCategoryList();
		ArrayList<String> categoryList = new ArrayList<String>();
		while(actualCategory != null) {
			categoryList.add(actualCategory.getName());
			actualCategory = actualCategory.getNext();
		}
		requestCategory.setItems(FXCollections.observableArrayList(categoryList));
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				try {
					boolean wroteProductName = !txtRequestName.getText().isEmpty();
					boolean wroteProductBrand = !txtRequestBrand.getText().isEmpty();
					boolean wroteProductPrice = !txtRequestPrice.getText().isEmpty();
					boolean wroteProductDescription = !txtRequestDescription.getText().isEmpty();
					boolean wroteProductPhoto = !txtRequestPhoto.getText().isEmpty();
					boolean selectedProductCategory = !requestCategory.getSelectionModel().getSelectedItem().equals(null); //NullPointerException, I have to fix this.
					if(wroteProductName && wroteProductBrand && wroteProductPrice && wroteProductDescription && wroteProductPhoto && selectedProductCategory) {
						String productName = txtRequestName.getText();
						String stringProductCategory = requestCategory.getSelectionModel().getSelectedItem();
						Category productCategory = minoristStore.searchCategory(stringProductCategory);
						String productBrand = txtRequestBrand.getText();
						int productPrice = Integer.valueOf(txtRequestPrice.getText());
						String productDescription = txtRequestDescription.getText();
						String productPhoto = txtRequestPhoto.getText();
						Image image = new Image(productPhoto);
						if(actualAccount instanceof Seller) {
							minoristStore.addRequest(productName, productCategory, productBrand, productPrice, 0, productDescription, (Seller)actualAccount, RequestType.ADD);
						}
						savePicture(image, PRODUCT_PICTURE_DIRECTORY, productName);
					}
				}catch(NullPointerException npe) {

				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void editPassword(ActionEvent event) {
		openWindow("edit-profile-info.fxml");
		labelEditProfileInfo.setText("New password:");
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				boolean wrotePassword = !txtEditProfileInfo.getText().isEmpty();
				if(wrotePassword) {
					actualAccount.setPassword(txtEditProfileInfo.getText());
				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void editFirstNames(ActionEvent event) {
		openWindow("edit-profile-info.fxml");
		labelEditProfileInfo.setText("First Names:");
		if(actualAccount instanceof Administrator) {
			txtEditProfileInfo.setText(((Administrator) actualAccount).getNames());
		}else if(actualAccount instanceof Consumer) {
			txtEditProfileInfo.setText(((Consumer) actualAccount).getNames());
		}
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				boolean wroteFirstNames = !txtEditProfileInfo.getText().isEmpty();
				if(wroteFirstNames) {
					if(actualAccount instanceof Administrator) {
						((Administrator) actualAccount).setNames(txtEditProfileInfo.getText());
					}else if(actualAccount instanceof Consumer) {
						((Consumer) actualAccount).setNames(txtEditProfileInfo.getText());
					}
				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void editLastNames(ActionEvent event) {
		openWindow("edit-profile-info.fxml");
		if(actualAccount instanceof Administrator) {
			txtEditProfileInfo.setText(((Administrator) actualAccount).getSurnames());
		}else if(actualAccount instanceof Consumer) {
			txtEditProfileInfo.setText(((Consumer) actualAccount).getSurnames());
		}
		labelEditProfileInfo.setText("Last Names:");
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				boolean wroteLastNames = !txtEditProfileInfo.getText().isEmpty();
				if(wroteLastNames) {
					if(actualAccount instanceof Administrator) {
						((Administrator) actualAccount).setSurnames(txtEditProfileInfo.getText());
					}else if(actualAccount instanceof Consumer) {
						((Consumer) actualAccount).setSurnames(txtEditProfileInfo.getText());
					}
				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void editPhone(ActionEvent event) {
		openWindow("edit-profile-info.fxml");
		labelEditProfileInfo.setText("Phone:");
		txtEditProfileInfo.setText(String.valueOf(((Consumer) actualAccount).getPhoneNumber()));
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				boolean wrotePhone = !txtEditProfileInfo.getText().isEmpty();
				if(wrotePhone) {
					((Consumer) actualAccount).setPhoneNumber(Long.valueOf(txtEditProfileInfo.getText()));
				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void editAddress(ActionEvent event) {
		openWindow("edit-profile-info.fxml");
		labelEditProfileInfo.setText("Address:");
		txtEditProfileInfo.setText(((Consumer) actualAccount).getAddress());
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				boolean wroteAddress = !txtEditProfileInfo.getText().isEmpty();
				if(wroteAddress) {
					((Consumer) actualAccount).setAddress(txtEditProfileInfo.getText());
				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void editTradename(ActionEvent event) {
		openWindow("edit-profile-info.fxml");
		labelEditProfileInfo.setText("Tradename:");
		txtEditProfileInfo.setText(((Seller) actualAccount).getTradeName());
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				boolean wroteTradeName = !txtEditProfileInfo.getText().isEmpty();
				if(wroteTradeName) {
					((Seller) actualAccount).setTradeName(txtEditProfileInfo.getText());
				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void editProfilePicture(ActionEvent event) {
		openWindow("edit-profile-picture.fxml");
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				try {
					String pictureDirectory = txtPictureDirectory.getText();
					Image image = new Image(pictureDirectory);
					savePicture(image, PROFILE_PICTURE_DIRECTORY, actualAccount.getUsername());
					profilePicture.setImage(image);
					if(actualAccount instanceof Administrator) {
						adminPicture.setImage(image);
					}else if(actualAccount instanceof Consumer) {
						clientPicture.setImage(image);
					}else if(actualAccount instanceof Seller) {
						sellerPicture.setImage(image);
					}
				}catch(IllegalArgumentException iae) {

				}
			}
			return null;
		});
		dialog.showAndWait();
	}

	public void checkRequest(Request request) {
		openRequestWindow("request-prompt.fxml");
		txtCheckRequestName.setText(request.getProduct().getName());
		txtCheckRequestBrand.setText(request.getProduct().getBrand());
		checkRequestDescription.setText(request.getProduct().getDescription());
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == acceptButtonType) {
				if(request.getRequestType().equals(RequestType.ADD)) {
					minoristStore.addProduct(request.getProduct());
				}else {
//					TODO. First you have to implemented the edit function. Then you can modify the current product.
				}
			}
			minoristStore.deleteRequest(request);
			return null;
		});
		dialog.showAndWait();
	}
	
	public void browsePicture(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		File file = fileChooser.showOpenDialog(null);
		try {
			txtPictureDirectory.setText(file.toURI().toString());
		}catch(NullPointerException npe) {

		}
	}

	public void browseProductPicture(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		File file = fileChooser.showOpenDialog(null);
		try {
			txtRequestPhoto.setText(file.toURI().toString());
		}catch(NullPointerException npe) {

		}
	}

	public void savePicture(Image image, String directory, String fileName) {
		File file = new File(System.getProperty("user.dir") + directory + fileName + ".png");
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bImage, "png", file);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Image loadProfilePicture() {
		File file = new File(System.getProperty("user.dir") + PROFILE_PICTURE_DIRECTORY + actualAccount.getUsername() + ".png");
		Image image = new Image(file.toURI().toString());
		return image;
	}

}
