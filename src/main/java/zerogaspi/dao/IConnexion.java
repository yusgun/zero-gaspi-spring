package zerogaspi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Connexion;

public interface IConnexion extends JpaRepository<Connexion, Long> {
	
	@Query("select u from Connexion u left join fetch u.roles where u.mail=:mail")
	public Optional<Connexion> findByIdWithRoles(@Param("mail") String mail);


}
