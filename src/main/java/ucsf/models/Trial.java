package ucsf.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_trial")
public class Trial {
	// ------------------------
		// PRIVATE FIELDS
		// ------------------------

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;

		@Column(name = "profile_id")
		private long profileId;

		@Column(name = "trial_id")
		private String trialId;

		@Column(name = "zip_code")
		private String zipCode;

		@Column(name = "favourite")
		private String favourite;

		@Column(name = "va")
		private String VA;

		@Column(name = "dist2closestsite")
		private String dist2closestsite;

		@Column(name = "site_name")
		private String sitename;

		public Trial() {
		}

		public Trial(long id) {
			this.id = id;
		}
		
		public String gettrialId() {
			return trialId;
		}

		public void settrialId(String trialId) {
			this.trialId = trialId;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getFavourite() {
			return favourite;
		}

		public void setFavourite(String favourite) {
			this.favourite = favourite;
		}

		public String getVA() {
			return VA;
		}

		public void setVA(String vA) {
			VA = vA;
		}

		public String getDist2closestsite() {
			return dist2closestsite;
		}

		public void setDist2closestsite(String dist2closestsite) {
			this.dist2closestsite = dist2closestsite;
		}

		public String getSitename() {
			return sitename;
		}

		public void setSitename(String sitename) {
			this.sitename = sitename;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getProfileId() {
			return profileId;
		}

		public void setProfileId(long profileId) {
			this.profileId = profileId;
		}
}
