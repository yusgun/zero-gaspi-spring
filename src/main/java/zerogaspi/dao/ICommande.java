package zerogaspi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Commande;

public interface ICommande extends JpaRepository<Commande, Long> {
<<<<<<< Updated upstream
=======
//	List<Commande> findByListeProduits(String listeProduits);
	
	@Query("select c from Commande c where c.dateEnvoie = :dateEnvoie order by c.dateEnvoie desc ")
	List<Commande> findByDateEnvoie(@Param("dateEnvoie") Date dateEnvoie);
>>>>>>> Stashed changes

}
