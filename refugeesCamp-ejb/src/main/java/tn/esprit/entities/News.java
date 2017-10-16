package tn.esprit.entities;
/*
 * author Salim Ben Hassine
 *
 * 
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class News implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360304435449051556L;
	private int id;
	private String author;
	private String content;
	private Date dateOfCreation;
	
	
	
	public News(int id, String author, String content, Date dateOfCreation) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.dateOfCreation = dateOfCreation;
	
	}
	
	
	public News() {
		super();
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	
	
	
}
