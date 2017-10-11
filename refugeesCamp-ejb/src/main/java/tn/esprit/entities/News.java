package tn.esprit.entities;
/*
 * author Salim Ben Hassine
 *
 * 
 */

import java.util.Date;

public class News {

	private int id;
	private String author;
	private String content;
	public Date dateOfCreation;
	
	
	
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
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	
	
	
}
