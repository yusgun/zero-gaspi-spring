package zerogaspi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "connexion_role")
public class ConnexionRole {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "connexion_id")
	private Connexion connexion;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public ConnexionRole() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Connexion getConnexion() {
		return connexion;
	}

	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
