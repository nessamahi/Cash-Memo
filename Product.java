package codes;
public class Product {

	private String title;
	private double price;
	private double quantity;
	
	public Product(String title, double price, double quantity) {
	
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public double getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", price=" + price + ", quantity="
				+ quantity + "]";
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
}
