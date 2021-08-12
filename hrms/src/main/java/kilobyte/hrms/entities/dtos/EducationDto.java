package kilobyte.hrms.entities.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
	
	private int unemployedId;
	
	@NotNull
	@NotBlank
	private String schoolName;
	
	@NotNull
	@NotBlank
	private String department;
	
	@NotNull
	@NotBlank
	private LocalDate startDate;
	
	@Nullable
	private LocalDate graduatedDate;

}
