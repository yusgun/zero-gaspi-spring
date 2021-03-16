package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Association;

public interface IAssociation extends JpaRepository<Association, Long> {

	@Query("select a from Association a where a.id = :id")
	 Association findByIdAssociation(@Param("id") Long id);
}
