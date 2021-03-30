package zerogaspi.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.CommandeGratuite;

public interface ICommandeGratuite extends JpaRepository<CommandeGratuite, Long> {
	
	@Query("select c from CommandeGratuite c order by c.dateEnvoie desc ")
	List<CommandeGratuite> findCgByDateEnvoieDesc();
	
	@Query("select c from CommandeGratuite c order by c.dateEnvoie Asc ")
	List<CommandeGratuite> findCgByDateEnvoieAsc();
	
	@Query("select c from CommandeGratuite c order by c.datePaiement Desc ")
	List<CommandeGratuite> findCgByDatePaiementDesc();
	
	@Query("select c from CommandeGratuite c order by c.datePaiement Asc ")
	List<CommandeGratuite> findCgByDatePaiementAsc();
	
	@Query("select c from CommandeGratuite c order by c.dateArrivee Desc ")
	List<CommandeGratuite> findCgByDateArriveeDesc();
	
	@Query("select c from CommandeGratuite c order by c.dateArrivee Asc ")
	List<CommandeGratuite> findCgByDateArriveeAsc();
	
	@Query("select c from CommandeGratuite c order by c.client_id = :client_id")
	List<CommandeGratuite> findCgByClient(@Param("client_id") Long id);

}
