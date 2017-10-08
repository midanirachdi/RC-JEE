package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: JobOffer
 *
 */
@Entity

public class JobOffer implements Serializable {

	   
	
	private int id;
	private String description;
	private Date begindate;
	private Date enddate;
	private int contactnumber;
	private static final long serialVersionUID = 1L;
	//TODO : link user to this

	public JobOffer() {
		super();
	}   
	/*
	 * GenerationType.sequence :   sequence table managed by sgbd
	 * GenerationType.table :   sequence table managed by ORM
	 * GenerationType.AUTO : depends on ORM
	 * GenerationType.IDENTITY : doesn't work with oracle
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}   
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}   
	public int getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(int contactnumber) {
		this.contactnumber = contactnumber;
	}
   
}
