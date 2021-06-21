package kilobyte.hrms.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirming_employers")
@PrimaryKeyJoinColumn(name = "confirm_id")
@EqualsAndHashCode(callSuper = true)
public class ConfirmingEmployer extends ConfirmationByEmployee{
	
	@OneToOne
	@JoinColumn(name="employer_id", referencedColumnName = "user_id")
	private Employer employer;
}
