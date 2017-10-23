package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Donation implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private double amount;
	private Date createdAt;
	private Date validatedAt;
	private String currency;
	private String state;
	private Camp to_camp;
	
	
	
	public Donation(){
	}

	public Donation(String id,double amount, Date createdAt, String currency, Camp to_camp) {
		super();
		this.id=id;
		this.amount = amount;
		this.createdAt = createdAt;
		this.currency = currency;
		this.to_camp = to_camp;
		this.state="pending";
	}
	public Donation(String id,double amount, Date createdAt, String currency) {
		super();
		this.id=id;
		this.amount = amount;
		this.createdAt = createdAt;
		this.currency = currency;
		this.state="pending";
	}

	public double getAmount() {
		return amount;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedAt() {
		return createdAt;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getValidatedAt() {
		return validatedAt;
	}
	public String getCurrency() {
		return currency;
	}
	public String getState() {
		return state;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="camp_ID")	
	@JsonBackReference
	public Camp getTo_camp() {
		return to_camp;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setValidatedAt(Date validatedAt) {
		this.validatedAt = validatedAt;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTo_camp(Camp to_camp) {
		this.to_camp = to_camp;
	}
	
	
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", amount=" + amount + ", createdAt=" + createdAt + ", validatedAt=" + validatedAt
				+ ", currency=" + currency + ", state=" + state + ", to_camp=" + to_camp + "]";
	}
}
