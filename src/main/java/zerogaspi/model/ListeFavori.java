package zerogaspi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="liste_favoris")
public class ListeFavori {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name= "client_id")
	private Client client;
	@OneToOne
	@JoinColumn(name="entreprise_id")
	private Entreprise entreprise;
	public ListeFavori() {
		super();
	}
	public ListeFavori(Client client, Entreprise entreprise) {
		super();
		this.client = client;
		this.entreprise = entreprise;
	}
	
	public ListeFavori(Long id, Client client, Entreprise entreprise) {
		super();
		this.id = id;
		this.client = client;
		this.entreprise = entreprise;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
