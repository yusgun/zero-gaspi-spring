package zerogaspi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "lot")
public class Lot {
	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@Column(length = 255)
	@JsonView(IViews.IViewBasic.class)
	private String libelle;
	@Column(length = 255)
	@JsonView(IViews.IViewBasic.class)
	private String description;
	@OneToOne
	@JoinColumn(name="entreprise_id")
	@JsonView(IViews.IViewBasic.class)
	private Entreprise entreprise;

	public Lot() {
		super();
	}

	public Lot(Long id, String libelle, String description, Entreprise entreprise) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.description = description;
		this.entreprise = entreprise;
	}

	public Lot(String libelle, String description, Entreprise entreprise) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.entreprise = entreprise;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	
	
}
