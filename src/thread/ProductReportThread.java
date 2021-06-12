package thread;

import java.io.PrintWriter;
import java.util.List;
import model.Product;

public class ProductReportThread extends Thread {

	private PrintWriter pw;
	private List<Product> productList;
	private int min;
	private int max;
	private String separator;
	
	public ProductReportThread(PrintWriter pw, List<Product> productList, int min, int max, String separator) {
		this.pw = pw;
		this.productList = productList;
		this.min = min;
		this.max = max;
		this.separator = separator;
	}
	
	public void run() {
		for(int i = min; i <= max; i++) {
			Product product = productList.get(i);
			long ID = product.getID();
			String name = product.getName();
			String category = product.getCategory().getName();
			String brand = product.getBrand();
			int price = product.getPrice();
			int stock = product.getStock();
			int salesNumber = product.getSalesNumber();
			int earnings = product.calculatePrice();
			pw.println(ID + separator + name + separator + category + separator + brand + separator + price + separator + stock
					+ separator + salesNumber + separator + earnings);
		}
	}
	
}
