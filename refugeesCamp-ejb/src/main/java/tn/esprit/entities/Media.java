package tn.esprit.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.entities.News;

/*
 * author Salim Ben Hassine
 *
 * 
 */
@Entity
public class Media implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4690724320753206321L;
	private int id;
	private String title;
	private String path;
	private Admin admin;
	
	public Media(int id, String title, String path,Admin admin) {
		super();
		this.id = id;
		this.title = title;
		this.path = path;
		this.admin=admin;
	}

	public Media() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AdminId")
	@JsonIgnore
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	
}
