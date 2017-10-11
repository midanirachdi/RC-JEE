package tn.esprit.entities;
/*
* author: Salim Ben Hassine
*/
public class Stock {
	
	private int id;
	private String stockType;
	private int qteTotal;
	private int qteInStock;
	private double stockValue;
	private String notes;
	
	public Stock() {
		super();
	}

	public Stock(int id, String stockType, int qteTotal, int qteInStock, double stockValue, String notes) {
		super();
		this.id = id;
		this.stockType = stockType;
		this.qteTotal = qteTotal;
		this.qteInStock = qteInStock;
		this.stockValue = stockValue;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
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
	
	
	

}

