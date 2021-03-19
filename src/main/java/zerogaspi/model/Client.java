package zerogaspi.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("client")
public class Client extends Identite {

	@JsonView(IViews.IViewBasic.class)
	@Column(name = "perimetre")
	private int perimetre;

	public Client() {
		super();
	}

	public Client(Long id, String numeroTelephone, String rue, String codePostal, String adresse, String nom,
			String prenom, Connexion connexion, int perimetre) {
		super(id, numeroTelephone, rue, codePostal, adresse, nom, prenom, connexion);
		this.perimetre = 0;
	}

	public Client(String numeroTelephone, String rue, String codePostal, String adresse, String nom, String prenom,
			Connexion connexion, int perimetre) {
		super(numeroTelephone, rue, codePostal, adresse, nom, prenom, connexion);
		this.perimetre = 0;
	}

	public int getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(int perimetre) {
		this.perimetre = perimetre;
	}

}
