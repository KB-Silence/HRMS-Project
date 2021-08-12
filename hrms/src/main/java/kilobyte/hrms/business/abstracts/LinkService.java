package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Link;
import kilobyte.hrms.entities.dtos.LinkDto;

public interface LinkService {
	Result addLink(LinkDto linkDto);
	Result updateLink(LinkDto linkDto);
	DataResult<List<Link>> getAll();
	DataResult<Link> getByUnemployedId(int unemployedId);
}
