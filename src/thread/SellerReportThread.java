package thread;

import java.io.FileNotFoundException;

import model.MinoristStore;
import model.Seller;

public class SellerReportThread extends Thread {

	private MinoristStore minoristStore;
	private String fileName;
	private String separator;
	private Seller seller;

	public SellerReportThread(MinoristStore minoristStore) {
		this.minoristStore = minoristStore;
	}

	public void run() {
		try {

			minoristStore.generateSellerProductReport(fileName, separator, seller);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
