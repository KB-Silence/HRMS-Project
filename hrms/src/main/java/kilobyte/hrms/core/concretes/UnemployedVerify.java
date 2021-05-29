package kilobyte.hrms.core.concretes;

import org.springframework.stereotype.Service;

import kilobyte.hrms.core.abstracts.UnemployedVerifyService;

@Service
public class UnemployedVerify implements UnemployedVerifyService{

	@Override
	public void verifyByCode(String code, String email) {
		System.out.println("Doğrulama Kodu " + email + " adresine gönderildi.");
	}

}
