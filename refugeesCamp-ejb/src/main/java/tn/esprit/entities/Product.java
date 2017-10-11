package tn.esprit.entities;
/*
* author: Salim Ben Hassine
*/
public class Product {
	
	private int id;
	private String productName;
	private String type;
	private String description;
	private String provider;
	private double unitPrice;
	
	public Product() {
		super();
	}

	public Product(int id, String productName, String type, String description, String provider, double unitPrice) {
		super();
		this.id = id;
		this.productName = productName;
		this.type = type;
		this.description = description;
		this.provider = provider;
		this.unitPrice = unitPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	

}

