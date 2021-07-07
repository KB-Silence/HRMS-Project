package kilobyte.hrms.entities.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDto {
	
	private int unemployedId;
	private String technologyName;
	
	@Min(1)
	@Max(5)
	private int technologyLevel;

}
