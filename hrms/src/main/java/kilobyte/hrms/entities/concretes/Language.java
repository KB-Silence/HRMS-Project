package kilobyte.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "languages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "language_id")
	private int languageId;

	@NotNull
	@NotBlank
	@Column(name = "language_name")
	private String languageName;
	

	@Min(1)
	@Max(5)
	@Column(name = "language_level")
	private int languageLevel;

	@ManyToOne()
	@JoinColumn(name = "unemployed_id", referencedColumnName = "user_id")
	private Unemployed unemployed;
}
