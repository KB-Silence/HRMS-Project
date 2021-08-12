package kilobyte.hrms.entities.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
	@Nullable
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
	
	@NotNull
	@NotBlank
	@Length(min=6, max=20)
	private String password;
	
}
