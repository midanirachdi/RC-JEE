package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

public class Need implements Serializable{

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
		return "Need [id=" + id + ", type=" + type + ", description=" + description + ", date=" + date + "]";
	}
	public Need() {
		super();
	}
	public Need(String type, String description, Date date) {
		super();
		this.type = type;
		this.description = description;
		this.date = date;
	}
	
	
}
