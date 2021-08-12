package kilobyte.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employer_updates")
public class EmployerUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="update_id")
	private int updateId;
	
	@NotNull
	@NotBlank
	@Column(name="employer_id")
	private int employerId;
	
	@JsonIgnore
	@Column(name="employee_id")
	private Integer employeeId;
	
	@NotNull
	@NotBlank
	@Length(min=10, max=10)
	@Column(name="phone_number")
	private String phoneNumber;
	
	@NotNull
	@NotBlank
	@Email
	@Column(name="email")
	private String email;
	
	@NotNull
	@NotBlank
	@Length(min=5)
	@Column(name="company_name")
	private String companyName;
	
	@NotNull
	@NotBlank
	@Column(name="web_site")
	private String webSite;
	
	@JsonIgnore
	@Column(name="approve_status")
	private Boolean approveStatus = false;
	
	@JsonIgnore
	@Column(name="approve_date")
	private LocalDate approveDate = LocalDate.now();
	
	@JsonIgnore
	@OneToOne(mappedBy = "employerUpdate")
	private ConfirmingUpdate confirmingUpdate;
}
