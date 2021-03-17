package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Entreprise;
import zerogaspi.model.Lot;

public interface ILot extends JpaRepository<Lot, Long> {
	
	List<Lot> findByEntreprise(Entreprise entreprise);
}
