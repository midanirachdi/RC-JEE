package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String title;
	private String path;
	
	public Media(int id, String title, String path) {
		super();
		this.id = id;
		this.title = title;
		this.path = path;
	}

	public Media() {
		super();
	}

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
	
	
}
