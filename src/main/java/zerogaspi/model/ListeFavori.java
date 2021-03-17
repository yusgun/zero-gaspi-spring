package zerogaspi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="liste_favoris")
public class ListeFavori {
	@Id
	@JsonView(IViews.IViewBasic.class)
	@OneToOne
	@JoinColumn(name= "client_id")
	private Client client;
	@Id
	@OneToOne
	@JoinColumn(name="entreprise_id")
	@JsonView(IViews.IViewListeFavoriDetail.class)
	private Entreprise entreprise;
	public ListeFavori() {
		super();
	}
	public ListeFavori(Client client, Entreprise entreprise) {
		super();
		this.client = client;
		this.entreprise = entreprise;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	

}
