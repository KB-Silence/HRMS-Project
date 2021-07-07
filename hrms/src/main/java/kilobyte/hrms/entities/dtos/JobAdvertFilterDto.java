package kilobyte.hrms.entities.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertFilterDto {
	
	private List<Integer> cityId;
	private List<Integer> positionId;
	private List<Integer> timeId;
	private List<Integer> typeId;
}
