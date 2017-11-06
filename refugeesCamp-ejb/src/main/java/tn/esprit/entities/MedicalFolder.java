package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MedicalFolder implements Serializable {
	
	private int id;
	private String apparences;
	private String mentalstate;
	private float weight;
	private float height;
	private float bloodpressure;
	private String bloodtype;
	private String description;
	private String doctorname;

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApparences() {
		return apparences;
	}
	public void setApparences(String apparences) {
		this.apparences = apparences;
	}
	public String getMentalstate() {
		return mentalstate;
	}
	public void setMentalstate(String mentalstate) {
		this.mentalstate = mentalstate;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getBloodpressure() {
		return bloodpressure;
	}
	public void setBloodpressure(float bloodpressure) {
		this.bloodpressure = bloodpressure;
	}
	public String getBloodtype() {
		return bloodtype;
	}
	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apparences == null) ? 0 : apparences.hashCode());
		result = prime * result + Float.floatToIntBits(bloodpressure);
		result = prime * result + ((bloodtype == null) ? 0 : bloodtype.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((doctorname == null) ? 0 : doctorname.hashCode());
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + id;
		result = prime * result + ((mentalstate == null) ? 0 : mentalstate.hashCode());
		result = prime * result + Float.floatToIntBits(weight);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalFolder other = (MedicalFolder) obj;
		if (apparences == null) {
			if (other.apparences != null)
				return false;
		} else if (!apparences.equals(other.apparences))
			return false;
		if (Float.floatToIntBits(bloodpressure) != Float.floatToIntBits(other.bloodpressure))
			return false;
		if (bloodtype == null) {
			if (other.bloodtype != null)
				return false;
		} else if (!bloodtype.equals(other.bloodtype))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (doctorname == null) {
			if (other.doctorname != null)
				return false;
		} else if (!doctorname.equals(other.doctorname))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (id != other.id)
			return false;
		if (mentalstate == null) {
			if (other.mentalstate != null)
				return false;
		} else if (!mentalstate.equals(other.mentalstate))
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}
	public MedicalFolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
