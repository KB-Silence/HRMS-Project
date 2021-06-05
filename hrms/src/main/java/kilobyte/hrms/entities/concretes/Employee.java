package kilobyte.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employee extends User{
	
	@NotBlank
	@NotNull
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank
	@NotNull
	@Column(name = "position_id")
	private int positionId;
	
}
