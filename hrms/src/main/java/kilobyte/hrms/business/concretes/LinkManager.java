package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.LinkService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.LinkDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.Link;
import kilobyte.hrms.entities.dtos.LinkDto;

@Service
public class LinkManager implements LinkService {

	private LinkDao linkDao;
	private UnemployedDao unemployedDao;

	@Autowired
	public LinkManager(LinkDao linkDao, UnemployedDao unemployedDao) {
		super();
		this.linkDao = linkDao;
		this.unemployedDao = unemployedDao;
	}

	@Override
	public Result addLink(LinkDto linkDto) {
		Link link = new Link();
		link.setGithubLink(linkDto.getGithubLink());
		link.setLinkedinLink(linkDto.getLinkedinLink());
		link.setUnemployed(this.unemployedDao.getOne(linkDto.getUnemployedId()));

		this.linkDao.save(link);
		return new SuccessResult("Link eklendi.");
	}

	@Override
	public DataResult<List<Link>> getAll() {
		return new SuccessDataResult<List<Link>>(this.linkDao.findAll());
	}

	@Override
	public DataResult<Link> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<Link>(this.linkDao.getByUnemployed_UserId(unemployedId));
	}

	@Override
	public Result updateLink(LinkDto linkDto) {
		if (this.linkDao.getByUnemployed_UserId(linkDto.getUnemployedId()) == null) {
			return new ErrorResult("Önce link eklemelisiniz.");
		}
		Link link = this.linkDao.getByUnemployed_UserId(linkDto.getUnemployedId());
		if (link.getGithubLink().equals(linkDto.getGithubLink()) && link.getLinkedinLink().equals(linkDto.getLinkedinLink())) {
			return new ErrorResult("Güncelleme yapmayı unuttun :)");
		}
		link.setGithubLink(linkDto.getGithubLink());
		link.setLinkedinLink(linkDto.getLinkedinLink());
		this.linkDao.save(link);
		return new SuccessResult("Bağlantılar güncellendi.");
	}

}
