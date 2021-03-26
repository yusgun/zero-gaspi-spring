package zerogaspi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("association")
public class Association extends Client{
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@Column(name= "libelle", length = 255)
	@NotEmpty(message="Entrer un libelle")
	@JsonView(IViews.IViewBasic.class)
	private String libelle;
	@Column(name="numero_rna", length = 45)
	@NotEmpty(message="Entrer le numero RNA de l'assocation")
	@JsonView(IViews.IViewBasic.class)
	private String numeroRNA;
	public Association() {
		super();
	}

	public Association(Long id, String libelle, String numeroRNA) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.numeroRNA = numeroRNA;
	}


	public Association(Long id, String numeroTelephone, String rue, String codePostal, String adresse, String nom,
			String prenom, Connexion connexion, int perimetre) {
		super(id, numeroTelephone, rue, codePostal, adresse, nom, prenom, connexion, perimetre);
		
	}


	public Association(String numeroTelephone, String rue, String codePostal, String adresse, String nom, String prenom,
			Connexion connexion, int perimetre) {
		super(numeroTelephone, rue, codePostal, adresse, nom, prenom, connexion, perimetre);
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getNumero() {
		return numeroRNA;
	}

	public void setNumero(String numero) {
		this.numeroRNA = numero;
	}

	public Association(String libelle, String numero) {
		super();
		this.libelle = libelle;
		this.numeroRNA = numero;
	}

	
}
