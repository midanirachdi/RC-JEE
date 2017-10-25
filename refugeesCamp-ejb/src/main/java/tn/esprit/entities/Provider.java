package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
* author: Salim Ben Hassine
*/
@Entity
public class Provider implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3652474096808383171L;

	private int id;
	private String providerName	;
	private String Address;
	private String Country;
	private int Tel;
	
	private Stock stock;
	
	public Provider() {
		super();
	}

	
	
	public Provider(int id, String providerName, String address, String country, int tel, Stock stock) {
		super();
		this.id = id;
		this.providerName = providerName;
		Address = address;
		Country = country;
		Tel = tel;
		this.stock = stock;
	}



	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}



	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}



	public String getAddress() {
		return Address;
	}



	public void setAddress(String address) {
		Address = address;
	}



	public String getCountry() {
		return Country;
	}



	public void setCountry(String country) {
		Country = country;
	}



	public int getTel() {
		return Tel;
	}



	public void setTel(int tel) {
		Tel = tel;
	}



	@ManyToOne
	@JsonIgnore
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	

}

