package zerogaspi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="entreprise")
public class Entreprise {

	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@JsonView(IViews.IViewBasic.class)
	private String numeroSiret;
	@JsonView(IViews.IViewBasic.class)
	private String typeRestauration;
	@JsonView(IViews.IViewBasic.class)
	private String nomEntreprise;
	@OneToOne
	@JoinColumn(name="vendeur_id")
	@JsonView(IViews.IViewEntrepriseWithVendeur.class)
	private Vendeur vendeur;
	
	public Entreprise() {
		super();
	}

	public Entreprise(Long id, String numeroSiret, String typeRestauration, String nomEntreprise,
			Vendeur vendeur) {
		super();
		this.id = id;
		this.numeroSiret = numeroSiret;
		this.typeRestauration = typeRestauration;
		this.nomEntreprise = nomEntreprise;
		this.vendeur = vendeur;
	}

	public Entreprise(String numeroSiret, String typeRestauration, String nomEntreprise, Vendeur vendeur) {
		super();
		this.numeroSiret = numeroSiret;
		this.typeRestauration = typeRestauration;
		this.nomEntreprise = nomEntreprise;
		this.vendeur = vendeur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroSiret() {
		return numeroSiret;
	}

	public void setNumeroSiret(String numeroSiret) {
		this.numeroSiret = numeroSiret;
	}

	public String getTypeRestauration() {
		return typeRestauration;
	}

	public void setTypeRestauration(String typeRestauration) {
		this.typeRestauration = typeRestauration;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public Vendeur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}

	
	
	
	
	
	
}
