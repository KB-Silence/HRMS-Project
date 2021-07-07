package kilobyte.hrms.entities.dtos;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRegisterDto {

	private String email;
	private String password;
	private String companyName;
	private String webSite;
	@Length(max = 10)
	private String phoneNumber;
}
