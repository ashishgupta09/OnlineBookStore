package Store;

public class Book {

	private int Id;
	private String BName;
	private String AutName;
	private double Price;

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getBName() {
		return BName;
	}
	public void setBName(String bName) {
		BName = bName;
	}
	public String getAutName() {
		return AutName;
	}
	public void setAutName(String autName) {
		AutName = autName;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
}
