package kilobyte.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {

	private int unemployedId;
	private String githubLink;
	private String linkedinLink;
}
