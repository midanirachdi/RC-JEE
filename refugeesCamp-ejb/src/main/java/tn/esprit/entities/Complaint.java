package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

public class Complaint implements Serializable{

	private int id;
	private String type;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
		return "Complaint [id=" + id + ", type=" + type + ", description=" + description + ", date=" + date + "]";
	}
	public Complaint() {
		super();
	}
	public Complaint(String type, String description, Date date) {
		super();
		this.type = type;
		this.description = description;
		this.date = date;
	}
	
	
}
