package kilobyte.hrms.entities.concretes;

import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employer extends User {
	
	@NotBlank
	@NotNull
	@Column(name = "company_name")
	private String companyName;
	
	@NotBlank
	@NotNull
	@Column(name = "web_site")
	private String webSite;
	
	@JsonIgnore
	@Column(name="waiting_for_update")
	private Boolean waitingForUpdate = false;
	
	@JsonIgnore
	@Column(name="employer_is_confirmed")
	private Boolean employerIsConfirmed = false;
	
	@JsonIgnore
	@OneToOne(mappedBy="employer")
	private ConfirmingEmployer confirmingEmployer;
	
	@OneToMany(mappedBy="employer")
	@JsonIgnore
	private List<JobAdvertisement> advertisements;
}
