package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.Facture;

public interface IFacture extends JpaRepository<Facture, Long> {
	
	

}
