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
@Table(name = "employment_times")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "time_id")
	private int timeId;
	
	@Column(name = "time_name")
	private String timeName;
	
	@JsonIgnore
	@OneToMany(mappedBy="employmentTime")
	private List<JobAdvertisement> jobAdvertisement;
}
