package kilobyte.hrms.core.abstracts;


public interface UnemployedVerifyService {
	void verifyByCode(String code, String email);
}
