package kilobyte.hrms.entities.dtos;

import java.time.LocalDate;

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
	
	private String description;
	private LocalDate lastApplication;
	private Boolean advertStatus;
	
	private int cityId;
	private int typeId;
	private int timeId;
	private int positionId;
}
