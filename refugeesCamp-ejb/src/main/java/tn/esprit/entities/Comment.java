package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String body;
	
	private Date datePublish;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Topic_ID")
	private Topic topic;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_ID")
	private User user;
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDatePublish() {
		return datePublish;
	}

	public void setDatePublish(Date datePublish) {
		this.datePublish = datePublish;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Comment() {
		super();
	}

}
