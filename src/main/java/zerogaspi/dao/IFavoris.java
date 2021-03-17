package zerogaspi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Entreprise;
import zerogaspi.model.ListeFavori;

public interface IFavoris extends JpaRepository<ListeFavori, Long> {

	
	@Query("select l.nomEntreprise from ListeFavoris l join Client  where c.id = :id")
	
	 find(@Param("id") Long id);
	
}
