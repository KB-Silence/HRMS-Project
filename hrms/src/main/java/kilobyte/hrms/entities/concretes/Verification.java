package kilobyte.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verifications")
@Inheritance(strategy = InheritanceType.JOINED)
public class Verification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "verification_id")
	private int verificationId;
	
	@JsonIgnore
	@Column(name = "is_verified")
	private boolean isVerified = false;
	
	@Column(name = "verification_code")
	private String verificationCode;
	
	@Column(name = "user_id")
	private int userId;

}
