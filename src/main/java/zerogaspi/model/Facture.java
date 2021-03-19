package zerogaspi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "facture")
public class Facture {
	
	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "commande_id")
	@JsonView(IViews.IViewFactureWithCommande.class)
	private Commande commande;

	

	public Facture() {
		super();
	}


	public Facture(Commande commande) {
		super();
		this.commande = commande;
	}


	public Facture(Long id, Commande commande) {
		super();
		this.id = id;
		this.commande = commande;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	
	
}
