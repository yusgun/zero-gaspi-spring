package zerogaspi.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name="connexion")
public class Connexion {
	
	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@Column(unique=true)
	@JsonView(IViews.IViewBasic.class)
	private String mail;
	@JsonView(IViews.IViewBasic.class)
	private String motDePasse;
	@OneToMany(mappedBy = "connexion")
	@JsonView(IViews.IViewBasic.class)
	private Set<ConnexionRole> roles;

	
	public Connexion() {
		super();
	}

	public Connexion(Long id, String mail, String motDePasse) {
		super();
		this.id = id;
		this.mail = mail;
		this.motDePasse = motDePasse;
	
	}

	public Connexion(String mail, String motDePasse) {
		super();
		this.mail = mail;
		this.motDePasse = motDePasse;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	
	
	
	

}
