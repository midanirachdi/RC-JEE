package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	private List<News> news;
	
	public Media(int id, String title, String path) {
		super();
		this.id = id;
		this.title = title;
		this.path = path;
	}

	public Media() {
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
	
	@ManyToMany
	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}
	
	
}
