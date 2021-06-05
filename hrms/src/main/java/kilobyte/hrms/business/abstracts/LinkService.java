package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Link;

public interface LinkService {
	Result addLink(Link link);
	DataResult<List<Link>> getAll();
	DataResult<Link> getByUnemployedId(int unemployedId);
}
