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
@Table(name="employment_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentType {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="type_id")
	private int typeId;
	
	@Column(name = "type_name")
	private String typeName;
	
	@JsonIgnore
	@OneToMany(mappedBy="employmentType")
	private List<JobAdvertisement> jobAdvertisements;
}
