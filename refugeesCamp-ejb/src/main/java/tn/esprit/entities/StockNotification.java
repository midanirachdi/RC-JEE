package tn.esprit.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
* author: Salim Ben Hassine
*/

@Entity
public class StockNotification {

	private int id;
	private String message;
	private Date dateOfNotification;
	private int Status;
	private Stock stock;
	
	
	public StockNotification() {
		super();
	}


	public StockNotification(int id, String message, Date dateOfNotification,int status,Stock stock) {
		super();
		this.id = id;
		this.message = message;
		this.Status=status;
		this.dateOfNotification = dateOfNotification;
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


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateOfNotification() {
		return dateOfNotification;
	}


	public void setDateOfNotification(Date dateOfNotification) {
		this.dateOfNotification = dateOfNotification;
	}

	@ManyToOne
	@JoinColumn(name="StockId")
	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public int getStatus() {
		return Status;
	}


	public void setStatus(int status) {
		Status = status;
	}

	
	
}

