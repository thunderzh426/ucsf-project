package ucsf.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_profile")
public class Profile {
	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "accession_block_id")
	private long anbId;

	@Column(name="age")
	private String age;

	@Column(name="zip_code")
	private String zipCode;

	@Column(name="gender")
	private String gender;

	@Column(name="cancer_type")
	private String cancerType;

	@Column(name="clinical_stage")
	private String clinicalStage;

	@Column(name="cancer_sub_type")
	private String cancerSubType;
	
	@Column(name="alk")
	private String ALK;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
	private Date createdAt;
	
	@Column(name="status")
	private String status;
	
	@PrePersist
	protected void onCreate() {
		setCreatedAt(new Date());
	}

	// ------------------------
	// PUBLIC METHODS
	// ------------------------
	
	
	public Profile() {
	}

	public Profile(long id) {
		this.id = id;
	}

	public Profile(Long anbId, String firstName, String lastName, String age, String zipCode, String gender,
			String cancerType, String clinicalStage, String mutations) {
		this.setAnbId(anbId);
		this.setAge(age);
		this.setZipCode(zipCode);
		this.setGender(gender);
		this.setCancerType(cancerType);
		this.setClinicalStage(clinicalStage);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String dob) {
		this.age = dob;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCancerType() {
		return cancerType;
	}

	public void setCancerType(String cancerType) {
		this.cancerType = cancerType;
	}

	public String getClinicalStage() {
		return clinicalStage;
	}

	public void setClinicalStage(String clinicalStage) {
		this.clinicalStage = clinicalStage;
	}


	public long getAnbId() {
		return anbId;
	}

	public void setAnbId(long anbId) {
		this.anbId = anbId;
	}

	public String getCancerSubType() {
		return cancerSubType;
	}

	public void setCancerSubType(String cancerSubType) {
		this.cancerSubType = cancerSubType;
	}

	public String getALK() {
		return ALK;
	}

	public void setALK(String aLK) {
		ALK = aLK;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
