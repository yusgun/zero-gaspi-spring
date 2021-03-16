package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Connexion;

public interface IConnexion extends JpaRepository<Connexion, Long> {

}
