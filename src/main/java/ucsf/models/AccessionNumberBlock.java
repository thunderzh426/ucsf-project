package ucsf.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents an User for this web application.
 */
@Entity
@Table(name = "t_accession_number_block")
public class AccessionNumberBlock {

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "accession_id")
	private long accessionId;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="dob")
	private String dob;

	@Column(name="zip_code")
	private String zipCode;

	@Column(name="gender")
	private String gender;

	@Column(name="cancer_type")
	private String cancerType;

	@Column(name="clinical_stage")
	private String clinicalStage;

	@Column(name="mutations")
	private String mutations;

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	public AccessionNumberBlock() {
	}

	public AccessionNumberBlock(long id) {
		this.accessionId = id;
	}

	public AccessionNumberBlock(String firstName, String lastName, String dob, String zipCode, String gender,
			String cancerType, String clinicalStage, String mutations) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDob(dob);
		this.setZipCode(zipCode);
		this.setGender(gender);
		this.setCancerType(cancerType);
		this.setClinicalStage(clinicalStage);
		this.setMutations(mutations);
	}

	public long getAccessionId() {
		return accessionId;
	}

	public void setAccessionId(long value) {
		this.accessionId = value;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getMutations() {
		return mutations;
	}

	public void setMutations(String mutations) {
		this.mutations = mutations;
	}

}
