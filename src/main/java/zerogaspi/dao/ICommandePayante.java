package zerogaspi.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zerogaspi.model.CommandePayante;

public interface ICommandePayante extends JpaRepository<CommandePayante, Long> {

	@Query("select c from CommandePayante c order by c.dateEnvoie desc ")
	List<CommandePayante> findCpByDateEnvoieDesc();
	
	@Query("select c from CommandePayante c order by c.dateEnvoie Asc ")
	List<CommandePayante> findCpByDateEnvoieAsc();
	
	@Query("select c from CommandePayante c order by c.datePaiement Desc ")
	List<CommandePayante> findCpByDatePaiementDesc();
	
	@Query("select c from CommandePayante c order by c.datePaiement Asc ")
	List<CommandePayante> findCpByDatePaiementAsc();
	
	@Query("select c from CommandePayante c order by c.dateArrivee Desc ")
	List<CommandePayante> findCpByDateArriveeDesc();
	
	@Query("select c from CommandePayante c order by c.dateArrivee Asc ")
	List<CommandePayante> findCpByDateArriveeAsc();
	
}
