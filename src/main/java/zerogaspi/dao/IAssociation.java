 package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Association;

public interface IAssociation extends JpaRepository<Association, Long> {

	@Query("select a from Association a where a.id = :id")
	 Association findByIdAssociation(@Param("id") Long id);
	
	@Query("select a from Association a where a.libelle = :libelle")
	 Association findByLibelleAssociation(@Param("libelle") String libelle);
	
	@Query("select a from Association a where a.numero = :numero")
	 Association findByNumeroAssociation(@Param("numero") int numero);

	//(select a from Association a)
	List<Association> findAll();
}
