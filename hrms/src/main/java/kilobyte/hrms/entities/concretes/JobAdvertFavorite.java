package kilobyte.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="job_advert_favorites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertFavorite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="favorite_id")
	private int favoriteId;
	
	@ManyToOne
	@JoinColumn(name="unemployed_id", referencedColumnName = "user_id")
	private Unemployed unemployed;
	
	@ManyToOne
	@JoinColumn(name="advert_id")
	private JobAdvertisement jobAdvertisement;
}
