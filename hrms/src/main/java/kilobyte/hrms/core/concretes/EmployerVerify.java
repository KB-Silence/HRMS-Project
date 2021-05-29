package kilobyte.hrms.core.concretes;


import org.springframework.stereotype.Service;

import kilobyte.hrms.core.abstracts.EmployerVerifyService;
import kilobyte.hrms.entities.concretes.Employer;

@Service
public class EmployerVerify implements EmployerVerifyService{

	@Override
	public void verifyEmployerByEmployee(Employer employer) {
		System.out.println("İşveren başarıyla onaylandı.");
	}

	@Override
	public void verifyByCode(String code, String email) {
		System.out.println("Doğrulama Kodu " + email + " adresine gönderildi.");
	}

}
