package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Connexion;
import zerogaspi.model.Identite;

public interface IIdentite extends JpaRepository<Identite,Long> {

	@Query("select i.connexion from Identite i where i.id = :id")
	public Connexion findCredentialsById(@Param("id") Long id);
	
	public Identite findByConnexion(Connexion connexion);
}
