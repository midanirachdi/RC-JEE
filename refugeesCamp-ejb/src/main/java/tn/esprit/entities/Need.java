package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Need implements Serializable{

	@Id
	@GeneratedValue
	private int id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String description;
	@ManyToOne
	@JoinColumn(name="idcampchef")
	private CampChef campchef ;
	
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
	
	
	
	public CampChef getCampchef() {
		return campchef;
	}
	public void setCampchef(CampChef campchef) {
		this.campchef = campchef;
	}
	
	
	@Override
	public String toString() {
		return "Need [id=" + id + ", date=" + date + ", description=" + description + ", campchef=" + campchef
				+ ", type=" + type + ", quantity=" + quantity + ", status=" + status + "]";
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
