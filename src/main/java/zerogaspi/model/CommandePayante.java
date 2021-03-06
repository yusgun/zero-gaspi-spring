package zerogaspi.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("achat")
public class CommandePayante extends Commande {
	@JsonView(IViews.IViewBasic.class)
	private double montant;
	
	public CommandePayante() {
		super();
	}

	public CommandePayante(Long id, Date datePaiement, Date dateEnvoie, Date dateArrivee, double montant, Client client, Lot lot) {
		super(id, datePaiement, dateEnvoie, dateArrivee, client, lot);
		this.montant = montant;
	}

	public CommandePayante( Date datePaiement, Date dateEnvoie, Date dateArrivee, double montant, Client client, Lot lot) {
		super(datePaiement, dateEnvoie, dateArrivee, client, lot);
		this.montant = montant;
	}
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
