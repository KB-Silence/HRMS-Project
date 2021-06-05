package kilobyte.hrms.business.abstracts;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.dtos.CvDto;

public interface CvDtoService {
	DataResult<CvDto> createCv(int unemployedId);
}
