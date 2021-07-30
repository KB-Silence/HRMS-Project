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
public class EmployerRegisterDto {

	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	@Length(min=6, max=20)
	private String password;
	
	@NotNull
	@NotBlank
	@Length(min=5)
	private String companyName;
	
	@NotNull
	@NotBlank
	@Length(min=10)
	private String webSite;
	
	@NotNull
	@NotBlank
	@Length(min=10, max = 10)
	private String phoneNumber;
}
