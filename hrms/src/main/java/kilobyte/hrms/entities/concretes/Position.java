package kilobyte.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="position_id")
	private int id;
	
	@Column(name="position_name")
	private String positionName;
	
	@OneToMany(mappedBy="position")
	@JsonIgnore
	private List<JobAdvertisement> advertisements;
	
}
