package kilobyte.hrms.core.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kilobyte.hrms.core.abstracts.EmailService;
import kilobyte.hrms.entities.concretes.User;

@Service
public class EmailManager implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendVerifyEmail(User user, String code) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("HRMS Mail Doğrulama");
		message.setText(
				"Kaydınızı tamamlamak için lütfen aşağıdaki bağlantıya tıklayın.\nhttp://localhost:8080/api/verification/approve/"
						+ code);
		message.setTo(user.getEmail());
		message.setFrom("hesapbirdenemedir@gmail.com");
		this.mailSender.send(message);
	}

}
