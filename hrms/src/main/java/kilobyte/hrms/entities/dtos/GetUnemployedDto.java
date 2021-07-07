package kilobyte.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUnemployedDto {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;

}
