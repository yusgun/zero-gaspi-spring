package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Particulier;

public interface IParticulier extends JpaRepository<Particulier, Long> {
	
	@Query("select p from Particulier p where p.id = :id")
	Particulier findByIdParticulier(@Param("id") Long id);
	
	@Query("select p from Particulier p where p.libelle = :libelle")
	Particulier findByLibelleParticulier(@Param("libelle") String libelle);
	
	
}
