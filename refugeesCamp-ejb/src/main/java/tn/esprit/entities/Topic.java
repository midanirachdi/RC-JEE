package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Topic implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String title;
	
	private String body;
	
	private Date datePublish;
	
	private boolean closed;
	
	@OneToMany(mappedBy = "topic")
	private Set<Comment> Comments;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_ID")
	private User user;
	
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Topic_Ref", joinColumns = @JoinColumn(name = "Topic_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Tag_id", referencedColumnName = "id"))
	private Set<Tag> tags;
	

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Comment> getComments() {
		return Comments;
	}

	public void setComments(Set<Comment> comments) {
		Comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Topic() {
		super();
	}

}
