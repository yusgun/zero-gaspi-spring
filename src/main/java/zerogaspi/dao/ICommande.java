package zerogaspi.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Commande;

public interface ICommande extends JpaRepository<Commande, Long> {

	@Query("select c from Commande c where c.dateEnvoie = :dateEnvoie order by c.dateEnvoie desc ")
	List<Commande> findByDateEnvoieDesc(@Param("dateEnvoie") Date dateEnvoie);
	
	@Query("select c from Commande c where c.dateEnvoie = :dateEnvoie order by c.dateEnvoie Asc ")
	List<Commande> findByDateEnvoieAsc(@Param("dateEnvoie") Date dateEnvoie);
	
	@Query("select c from Commande c where c.datePaiement = :datePaiement order by c.datePaiement Desc ")
	List<Commande> findByDatePaiementDesc(@Param("datePaiement") Date datePaiement);
	
	@Query("select c from Commande c where c.datePaiement = :datePaiement order by c.datePaiement Asc ")
	List<Commande> findByDatePaiementAsc(@Param("datePaiement") Date datePaiement);
	
	@Query("select c from Commande c where c.dateArrivee = :dateArrivee order by c.dateArrivee Desc ")
	List<Commande> findByDateArriveeDesc(@Param("dateArrivee") Date dateArrivee);
	
	@Query("select c from Commande c where c.dateArrivee = :dateArrivee order by c.dateArrivee Asc ")
	List<Commande> findByDateArriveeAsc(@Param("dateArrivee") Date dateArrivee);


}
