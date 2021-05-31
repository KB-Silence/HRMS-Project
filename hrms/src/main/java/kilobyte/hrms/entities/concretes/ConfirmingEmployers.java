package kilobyte.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirming_employers")
@PrimaryKeyJoinColumn(name = "confirm_id")
public class ConfirmingEmployers extends ConfirmationByEmployee{
	
	@Column(name = "employer_id")
	private int employerId;
}
