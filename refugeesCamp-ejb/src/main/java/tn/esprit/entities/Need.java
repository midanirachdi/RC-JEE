package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity

public class Need implements Serializable{

	@Id
	@GeneratedValue
	private int id;
	private String description;

	/* ******************* Added */
	@Enumerated(EnumType.STRING)/* added */ 
	private Stock.stockNeedsEnum type;
	private int quantity;
	private int status=0; /* status=-1 refused, status=1 Accepted, status=0 pending */
	
	/* ******************** */
	
	
	
	private static final long serialVersionUID = 1L;

	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public Stock.stockNeedsEnum getType() {
		return type;
	}
	public void setType(Stock.stockNeedsEnum type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private Date date;
	@Override
	public String toString() {
		return "Need [id=" + id + ", type=" + type + ", description=" + description + ", date=" + date + "]";
	}
	public Need() {
		super();
	}
	public Need(Stock.stockNeedsEnum type, String description, Date date) {
		super();
		this.type = type;
		this.description = description;
		this.date = date;
	}
	
	/* **************Added */
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/* *******************/
	
}
