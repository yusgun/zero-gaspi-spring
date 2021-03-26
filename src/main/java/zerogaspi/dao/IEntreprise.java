package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Entreprise;

public interface IEntreprise extends JpaRepository<Entreprise, Long> {

	
	@Query("select e from Entreprise e where e.nomEntreprise = :nomEntreprise")
	Entreprise findByNomEntreprise(@Param("nomEntreprise") String nomEntreprise);
	
	@Query("select e from Entreprise e where e.typeRestauration = :typeRestauration")
	Entreprise findByTypeRestauration(@Param("typeRestauration") String typeRestauration);
	
	@Query("select e.nomEntreprise,e.vendeur.ville,e.vendeur.codePostal,e.vendeur.numeroTelephone,e.vendeur.rue, e.id from Entreprise e where e.nomEntreprise = :search or e.vendeur.ville = :search or e.vendeur.codePostal = :search")
	List<Object[]> findByNomOrCPOrVille(@Param("search")String search);
}
