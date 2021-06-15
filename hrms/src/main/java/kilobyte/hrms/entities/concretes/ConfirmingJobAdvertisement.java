package kilobyte.hrms.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisement"})
public class ConfirmingJobAdvertisement extends ConfirmationByEmployee {
	
	@OneToOne()
	@JoinColumn(name="advert_id")
	private JobAdvertisement jobAdvertisement;
}
