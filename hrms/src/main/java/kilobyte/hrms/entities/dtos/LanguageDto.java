package kilobyte.hrms.entities.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {

	private int unemployedId;
	private String languageName;
	@Min(1)
	@Max(5)
	private int languageLevel;
}
