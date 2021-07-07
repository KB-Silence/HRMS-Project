package kilobyte.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceDto {
	
	private int unemployedId;
	
	private String workplaceName;
	private String positionName;
	private LocalDate startDate;
	private LocalDate leaveDate;

}
