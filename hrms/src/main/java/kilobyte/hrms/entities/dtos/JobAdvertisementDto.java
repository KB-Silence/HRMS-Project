package kilobyte.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

	private int employerId;	
	private int minSalary;
	private int maxSalary;
	private int quota;
	
	private int cityId;
	private int typeId;
	private int timeId;
	private int positionId;
	
	private String description;
	private String lastApplication;
}
