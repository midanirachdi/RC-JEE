package tn.esprit.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/*
* author: Salim Ben Hassine
*/
@Entity
public class Stock implements Serializable {
	public static enum stockNeedsEnum{
		Clothes,
		Food,
		Tents,
		Water,
		Medicines,
		Covers
	}
	private static final long serialVersionUID = -1085949622344665790L;
	
	private int id;
	private stockNeedsEnum stockType;
	private int qteTotal;
	private int qteInStock;
	private double stockValue;
	private String notes;
	private Admin admin;
	
	
	public Stock() {
		super();
	}

	
	public Stock(int id, stockNeedsEnum stockType, int qteTotal, int qteInStock, double stockValue, String notes,
			Admin admin) {
		super();
		this.id = id;
		this.stockType = stockType;
		this.qteTotal = qteTotal;
		this.qteInStock = qteInStock;
		this.stockValue = stockValue;
		this.notes = notes;
		this.admin = admin;
	}


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "StockId")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.STRING)
	public stockNeedsEnum getStockType() {
		return stockType;
	}

	public void setStockType(stockNeedsEnum stockType) {
		this.stockType = stockType;
	}

	public int getQteTotal() {
		return qteTotal;
	}

	public void setQteTotal(int qteTotal) {
		this.qteTotal = qteTotal;
	}

	public int getQteInStock() {
		return qteInStock;
	}

	public void setQteInStock(int qteInStock) {
		this.qteInStock = qteInStock;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AdminId")
	@JsonBackReference
	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	

	

}

