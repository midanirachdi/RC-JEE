package tn.esprit.entities;
/*
 * author Salim Ben Hassine
 *
 * 
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class News implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360304435449051556L;
	private int id;
	private String author;
	private String title;
	private String content;
	private String location;
	private Date dateOfCreation;
	private String mainPhoto;
	private Admin admin;
	

	public News(int id, String author, String content, Date dateOfCreation,String location,Admin admin) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.dateOfCreation = dateOfCreation;
		this.location=location;
		this.admin=admin;
	
	}
	
	
	public News() {
		super();
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
	@Column(length = 65535,columnDefinition="Text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AdminId")
	@JsonBackReference
	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public String getMainPhoto() {
		return mainPhoto;
	}


	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}
	
	
	
	
}
