package kilobyte.hrms.entities.concretes.verifications;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "verification_by_employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationByEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "verify_id")
	private int verifyId;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "employer_id")
	private int employerId;
	
	@Column(name = "is_verified")
	private boolean isVerified;
}
