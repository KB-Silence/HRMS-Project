package kilobyte.hrms.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "confirmation_by_employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ConfirmationByEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "confirm_id")
	private int confirmId;
	
	@Column(name = "verified_status")
	private boolean verifiedStatus;
	
	@JsonIgnore
	@Column(name = "approval_date")
	private LocalDateTime approvalDate = LocalDateTime.now();
	
	@ManyToOne()
	@JoinColumn(name="employee_id", referencedColumnName = "user_id")
	private Employee employee;
}
