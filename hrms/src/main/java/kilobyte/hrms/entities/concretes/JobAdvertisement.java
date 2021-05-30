package kilobyte.hrms.entities.concretes;

import java.time.LocalDateTime;

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
@Table(name = "job_advertisements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "advert_id")
	private int advertId;

	@Column(name = "min_salary")
	private int minSalary;

	@Column(name = "max_salary")
	private int maxSalary;

	@Column(name = "quota")
	private int quota;

	@Column(name = "last_application")
	private String lastApplication;

	@Column(name = "created_date")
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "advert_status")
	private Boolean advertStatus;

	@Column(name = "job_description")
	private String jobDescription;

	@ManyToOne()
	@JoinColumn(name = "employer_id", referencedColumnName = "user_id")
	private Employer employer;
	
	@ManyToOne()
	@JoinColumn(name = "position_id")
	private Position position;
	
	@ManyToOne()
	@JoinColumn(name = "city_id")
	private City city;

}
