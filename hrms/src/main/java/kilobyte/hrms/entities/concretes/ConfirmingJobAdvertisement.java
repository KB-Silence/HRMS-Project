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
@Table(name="confirming_jobadvertisements")
@PrimaryKeyJoinColumn(name = "confirm_id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfirmingJobAdvertisement extends ConfirmationByEmployee {
	
	@OneToOne()
	@JoinColumn(name="advert_id")
	private JobAdvertisement jobAdvertisement;
}
