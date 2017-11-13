package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* author: Salim Ben Hassine
*/
@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4567855392892677738L;
	private int id;
	private String productName;
	private String description;
	private double price;
	
	public Product() {
		super();
	}

	public Product(int id, String productName, String description, double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
	
	
}

