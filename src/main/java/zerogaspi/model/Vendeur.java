package zerogaspi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("vendeur")
public class Vendeur extends Identite {

	@Temporal(TemporalType.DATE)
	@JsonView(IViews.IViewBasic.class)
	private Date horaire;
	
	

	public Vendeur() {
		super();
	}

	public Vendeur(Long id, String numeroTelephone, String rue, String codePostal, String adresse, String nom,
			String prenom, Connexion connexion, Date horaire) {
		super(id, numeroTelephone, rue, codePostal, adresse, nom, prenom, connexion);
		this.horaire = horaire;
	}

	public Vendeur(String numeroTelephone, String rue, String codePostal,String ville, String nom, String prenom,
			Connexion connexion, Date horaire) {
		super(numeroTelephone, rue, codePostal, ville, nom, prenom, connexion);
		this.horaire = horaire;
	}

	public Date getHoraire() {
		return horaire;
	}

	public void setHoraire(Date horaire) {
		this.horaire = horaire;
	}

	



}
