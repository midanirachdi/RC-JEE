package tn.esprit.entities;

import java.util.Date;

/*
 * author Salim Ben Hassine
 *
 * 
 */
public class Donation {
	private int id;
	private double amount;
	private Date dateAndTime;
	private boolean anonymous;
	private String donorName;
	private String message;
	
	public Donation(int id, double amount, Date dateAndTime, boolean anonymous, String donorName, String message) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateAndTime = dateAndTime;
		this.anonymous = anonymous;
		this.donorName = donorName;
		this.message = message;
	}

	public Donation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
