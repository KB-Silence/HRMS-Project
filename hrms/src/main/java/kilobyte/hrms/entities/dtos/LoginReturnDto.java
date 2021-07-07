package kilobyte.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReturnDto {
	
	private int id;
	private String name;
	private String email;
	private int userType;
}
