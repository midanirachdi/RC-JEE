package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private DistrictChef districtchef;
	private CampChef campchef;
	private static final long serialVersionUID = 1L;

	public JobOffer() {
		super();
	}   


	public JobOffer(String description, Date begindate, Date enddate, int contactnumber) {
		super();
		this.description = description;
		this.begindate = begindate;
		this.enddate = enddate;
		this.contactnumber = contactnumber;
	}


	public JobOffer(String description, Date begindate, Date enddate, int contactnumber, DistrictChef districtchef,
			CampChef campchef) {
		super();
		this.description = description;
		this.begindate = begindate;
		this.enddate = enddate;
		this.contactnumber = contactnumber;
		this.districtchef = districtchef;
		this.campchef = campchef;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}   
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
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
	@ManyToOne
	@JoinColumn(name="idDistrictchef",referencedColumnName="id",insertable=false,updatable=false)
	public DistrictChef getDistrictchef() {
		return districtchef;
	}
	public void setDistrictchef(DistrictChef districtchef) {
		this.districtchef = districtchef;
	}
	@ManyToOne
	@JoinColumn(name="idCampchef",referencedColumnName="id",insertable=false,updatable=false)
	public CampChef getCampchef() {
		return campchef;
	}
	public void setCampchef(CampChef campchef) {
		this.campchef = campchef;
	}



	@Override
	public String toString() {
		return "JobOffer [description=" + description + ", begindate=" + begindate + ", enddate=" + enddate
				+ ", contactnumber=" + contactnumber + "]";
	}
   
}
