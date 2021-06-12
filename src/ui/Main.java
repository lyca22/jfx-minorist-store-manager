package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MinoristStore;

public class Main extends Application {

	private MinoristStore minoristStore;
	private MinoristStoreGUI minoristStoreGUI;
	
	public Main() {
		super();
		minoristStore = new MinoristStore();
		minoristStoreGUI = new MinoristStoreGUI(minoristStore);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		fxmlLoader.setController(minoristStoreGUI);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MinSMa");
		primaryStage.show();
		minoristStoreGUI.loginScreen(null);
		minoristStore.loadAll();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
