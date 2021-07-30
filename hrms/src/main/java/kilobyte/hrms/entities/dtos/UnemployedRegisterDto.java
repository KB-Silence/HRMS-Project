package kilobyte.hrms.entities.dtos;

import java.time.LocalDate;

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
public class UnemployedRegisterDto {
	
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
	@Length(min=2)
	private String firstName;
	
	@NotNull
	@NotBlank
	@Length(min=2)
	private String lastName;
	
	@NotNull
	@NotBlank
	@Length(min=11, max = 11)
	private String nationalityId;
	
	@NotNull
	private LocalDate birthDate;
	
	@NotNull
	@NotBlank
	@Length(min=10, max=10)
	private String phoneNumber;
}
