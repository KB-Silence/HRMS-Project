package kilobyte.hrms.entities.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnemployedRegisterDto {
	
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	
	@Length(max = 11)
	private String nationalityId;
	private LocalDate birthDate;
}
