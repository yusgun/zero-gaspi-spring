package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Entreprise;

public interface IEntreprise extends JpaRepository<Entreprise, Long> {

	
	@Query("select e from Entreprise e where e.nomEntreprise = :nomEntreprise")
	Entreprise findByNomEntreprise(@Param("nomEntreprise") String nomEntreprise);
	
	@Query("select e from Entreprise e where e.typeRestauration = :typeRestauration")
	Entreprise findByTypeRestauration(@Param("typeRestauration") String typeRestauration);
}
