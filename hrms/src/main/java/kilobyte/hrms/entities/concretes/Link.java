package kilobyte.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "links")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "link_id")
	private int linkId;
	
	@NotNull
	@NotBlank
	@Column(name = "github_link")
	private String githubLink;
	
	@NotNull
	@NotBlank
	@Column(name = "linkedin_link")
	private String linkedinLink;

	@ManyToOne
	@JoinColumn(name = "unemployed_id", referencedColumnName = "user_id")
	private Unemployed unemployed;
}
