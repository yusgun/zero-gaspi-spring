package zerogaspi.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "identite")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_identite")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type_identite")
@JsonSubTypes({ @Type(value = Particulier.class, name = "particulier"), @Type(value = Association.class, name = "association"), @Type(value = Vendeur.class, name = "vendeur") })
public abstract class Identite {

	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@NotEmpty(message = "Ajouter un numéro de téléphone")
	@Pattern(regexp = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$", message = "numéro de téléphone invalide")
	@JsonView(IViews.IViewBasic.class)
	private String numeroTelephone;
	@NotEmpty(message = "Ajouter une adresse (sans CP et la ville)")
	@JsonView(IViews.IViewBasic.class)
	private String rue;
	@NotEmpty(message = "Ajouter un code postal")
	@JsonView(IViews.IViewBasic.class)
	@Pattern(regexp = "^(([1-95]{2}|2A|2B)[0-9]{3})$|^[971-974]$", message = "Code postal invalide")
	private String codePostal;
	@NotEmpty(message = "Ajouter un code postal")
	@JsonView(IViews.IViewBasic.class)
	private String ville;
	@NotEmpty(message = "Ajouter un nom")
	@JsonView(IViews.IViewBasic.class)
	private String nom;
	@NotEmpty(message = "Ajouter un prénom")
	@JsonView(IViews.IViewBasic.class)
	private String prenom;
	@OneToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name = "connexion_id")
	@JsonView(IViews.IViewIdentiteWithConnexion.class)
	private Connexion connexion;

	public Identite() {
		super();
	}

	public Identite(Long id, String numeroTelephone, String rue, String codePostal, String ville, String nom,
			String prenom, Connexion connexion) {
		super();
		this.id = id;
		this.numeroTelephone = numeroTelephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.nom = nom;
		this.prenom = prenom;
		this.connexion = connexion;
	}

	public Identite(String numeroTelephone, String rue, String codePostal, String ville, String nom, String prenom,
			Connexion connexion) {
		super();
		this.numeroTelephone = numeroTelephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.nom = nom;
		this.prenom = prenom;
		this.connexion = connexion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Connexion getConnexion() {
		return connexion;
	}

	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Connexion getConnexions() {
		return connexion;
	}

	public void setConnexions(Connexion connexion) {
		this.connexion = connexion;
	}

}
