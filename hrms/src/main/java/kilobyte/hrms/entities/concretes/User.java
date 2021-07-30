package kilobyte.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@JsonIgnore
	private int userId;
	
	@NotBlank
	@NotNull
	@Length(min=10, max = 10)
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@NotBlank
	@NotNull
	@Email
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@NotNull
	@Length(min=6, max=20)
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@Column(name="mail_is_verify")
	private Boolean mailIsVerify = false;
}
