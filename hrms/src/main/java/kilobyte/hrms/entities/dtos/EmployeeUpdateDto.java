package kilobyte.hrms.entities.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateDto {

	@NotNull
	@NotBlank
	private int employeeId;
	
	private int positionId;
	
	@NotNull
	@NotBlank
	@Length(min=2)
	private String firstName;
	
	@NotNull
	@NotBlank
	@Length(min=2)
	private String lastName;
	
	@NotNull
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	@Length(min=10, max=10)
	private String phoneNumber;
	
}
