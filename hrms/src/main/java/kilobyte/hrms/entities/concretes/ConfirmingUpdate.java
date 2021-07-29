package kilobyte.hrms.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="confirming_updates")
@PrimaryKeyJoinColumn(name = "confirm_id")
@EqualsAndHashCode(callSuper = true)
public class ConfirmingUpdate extends ConfirmationByEmployee {

	@ManyToOne()
	@JoinColumn(name="employer_id", referencedColumnName = "user_id")
	private Employer employer;
	
	@OneToOne()
	@JoinColumn(name="update_id")
	private EmployerUpdate employerUpdate;
}
