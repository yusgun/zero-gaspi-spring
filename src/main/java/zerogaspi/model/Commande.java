package zerogaspi.model;

import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="commande")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_commande")
public class Commande {
	
	@Id
	@GeneratedValue
	@JsonView(IViews.IViewCommande.class)
	private Long id;
	@Temporal(TemporalType.DATE)
	@JsonView(IViews.IViewCommande.class)
	private Date datePaiement;
	@Temporal(TemporalType.DATE)
	@JsonView(IViews.IViewCommande.class)
	private Date dateEnvoie;
	@Temporal(TemporalType.DATE)
	@JsonView(IViews.IViewCommande.class)
	private Date dateArrivee;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lot_id")
	@JsonView(IViews.IViewCommande.class)
	private Lot lot;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "facture_id")
	@JsonView(IViews.IViewCommande.class)
	private Facture facture;
	
	
	
public Commande() {
	super();
}

public Commande(Date datePaiement, Date dateEnvoie, Date dateArrivee, Lot lot) {
	super();
	this.datePaiement = datePaiement;
	this.dateEnvoie = dateEnvoie;
	this.dateArrivee = dateArrivee;
	this.lot = lot;
}

public Commande(Long id, Date datePaiement, Date dateEnvoie, Date dateArrivee, Lot lot) {
	super();
	this.id = id;
	this.datePaiement = datePaiement;
	this.dateEnvoie = dateEnvoie;
	this.dateArrivee = dateArrivee;
	this.lot = lot;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getDatePaiement() {
	return datePaiement;
}

public void setDatePaiement(Date datePaiement) {
	this.datePaiement = datePaiement;
}

public Date getDateEnvoie() {
	return dateEnvoie;
}

public void setDateEnvoie(Date dateEnvoie) {
	this.dateEnvoie = dateEnvoie;
}

public Date getDateArrivee() {
	return dateArrivee;
}

public void setDateArrivee(Date dateArrivee) {
	this.dateArrivee = dateArrivee;
}

public Lot getLot() {
	return lot;
}

public void setLot(Lot lot) {
	this.lot = lot;
}

	
}
