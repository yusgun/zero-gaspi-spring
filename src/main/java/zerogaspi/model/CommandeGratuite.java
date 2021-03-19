package zerogaspi.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "commande_gratuite")
@DiscriminatorValue("don")
public class CommandeGratuite extends Commande {
	@JsonView(IViews.IViewBasic.class)
	private double montant;
	
	public CommandeGratuite() {
		super();
	}
	
	public CommandeGratuite(Long id, Date datePaiement, Date dateEnvoie, Date dateArrivee, Lot lot) {
		super(id, datePaiement, dateEnvoie, dateArrivee, lot);
		this.montant = 0;
	}

	public CommandeGratuite(Date datePaiement, Date dateEnvoie, Date dateArrivee, double montant, Lot lot) {
		super(datePaiement, dateEnvoie, dateArrivee, lot);
		this.montant = 0;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
