package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Commande;

public interface ICommande extends JpaRepository<Commande, Long> {
	List<Commande> findByListeProduits(String listeProduits);
	
//	@Query("select c from Commande c where c.idCommande = :idCommande")
//	Commande findByIdCommande(@Param("idCOmmande") Long idCommande);

}
