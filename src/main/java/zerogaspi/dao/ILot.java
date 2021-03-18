package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Lot;

public interface ILot extends JpaRepository<Lot, Long> {
	
	@Query("Select l from Lot l where l.entreprise.id = :id")
	List<Lot> findByEntreprise(@Param("id") Long id);
}
