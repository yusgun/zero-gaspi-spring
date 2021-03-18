package zerogaspi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="paiement")
public class Paiement {
	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@Temporal(TemporalType.DATE)
	@JsonView(IViews.IViewBasic.class)
	private Date horodatage;
	@OneToOne
	@JoinColumn(name="commande_payante_id")
	@JsonView(IViews.IViewBasic.class)
	private CommandePayante commandePayante;
	
	public Paiement() {
		super();
	}

	public Paiement(Long id, Date horodatage, CommandePayante commandePayante) {
		super();
		this.id = id;
		this.horodatage = horodatage;
		this.commandePayante = commandePayante;
	}

	public Paiement(Date horodatage, CommandePayante commandePayante) {
		super();
		this.horodatage = horodatage;
		this.commandePayante = commandePayante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHorodatage() {
		return horodatage;
	}

	public void setHorodatage(Date horodatage) {
		this.horodatage = horodatage;
	}

	public CommandePayante getCommandePayante() {
		return commandePayante;
	}

	public void setCommandePayante(CommandePayante commandePayante) {
		this.commandePayante = commandePayante;
	}
}
