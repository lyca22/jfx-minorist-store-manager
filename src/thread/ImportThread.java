package thread;

import java.io.IOException;

import model.MinoristStore;
import model.Seller;

public class ImportThread extends Thread {

	private MinoristStore minoristStore;
	private String fileName;
	private String separator;
	private Seller seller;

	public ImportThread(MinoristStore minoristStore) {
		this.minoristStore = minoristStore;
	}

	public void run() {
		try {

			minoristStore.importProducts(fileName, separator, seller);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


