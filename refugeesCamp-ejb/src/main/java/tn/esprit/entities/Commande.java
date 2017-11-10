package tn.esprit.entities;
/*
 * author Salim Ben Hassine
 *
 * 
 */

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Commande implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6487677190082181164L;
	private int id;
	private Date ordered;
	private Date shipped;
	private String address;
	private String country;
	private int qteOfProduct;
	private int status;
	
	private Provider provider;
	private Product product;
	private Stock stock;
	private Admin admin;
	private double totalPrice;
	
	public Commande() {
		
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOrdered() {
		return ordered;
	}

	public void setOrdered(Date ordered) {
		this.ordered = ordered;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getShipped() {
		return shipped;
	}

	public void setShipped(Date shipped) {
		this.shipped = shipped;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Stock")
	@JsonBackReference
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Admin")
	@JsonBackReference
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getQteOfProduct() {
		return qteOfProduct;
	}

	public void setQteOfProduct(int qteOfProduct) {
		this.qteOfProduct = qteOfProduct;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Product")
	@JsonBackReference
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Provider")
	@JsonBackReference
	public Provider getProvider() {
		return provider;
	}
	
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	
	
	
	
}
