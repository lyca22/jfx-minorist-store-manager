package thread;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import model.Order;
import model.Product;

public class OrderReportThread extends Thread {
	
	private PrintWriter pw;
	private List<Order> orderList;
	private int min;
	private int max;
	private String separator;
	private LocalDateTime minDate;
	private LocalDateTime maxDate;
	
	public OrderReportThread(PrintWriter pw, List<Order> orderList, int min, int max, String separator, LocalDateTime minDate, LocalDateTime maxDate) {
		this.pw = pw;
		this.orderList = orderList;
		this.min = min;
		this.max = max;
		this.separator = separator;
		this.minDate = minDate;
		this.maxDate = maxDate;
	}
	
	public void run() {
		String text = "";
		for(int i = min; i <= max; i++) {
			for(int j = 0; j <= orderList.get(i).getProductList().size()-1; j++) {
				Product product = orderList.get(i).getProductList().get(j);
				int quantity = orderList.get(i).getProductQuantity().get(j);
				text += product.getName() + " " + quantity + " " + product.getPrice() + separator;
			}
			if(orderList.get(i).getDate().isAfter(minDate) && orderList.get(i).getDate().isBefore(maxDate)) {
				pw.println(orderList.get(i).getID() + separator + orderList.get(i).getDate().toString() + separator +
						orderList.get(i).getClient().getNames() + " " + orderList.get(i).getClient().getSurnames() +
						separator + orderList.get(i).getClient().getAddress() + separator + orderList.get(i).getClient().getPhoneNumber() +
						separator + text + orderList.get(i).getPrice() + separator + orderList.get(i).getOrderState().name() +
						separator + orderList.get(i).getPaymentInformation().getPaymentMethod().getName() + separator +
						orderList.get(i).getPaymentInformation().getZipCode());

			}
		}
	}
	
}
