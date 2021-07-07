package kilobyte.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
	
	private int unemployedId;
	
	private String schoolName;
	private String department;
	private LocalDate startDate;
	private LocalDate graduatedDate;

}
