package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import zerogaspi.model.Connexion;

public interface IConnexion extends JpaRepository<Connexion, Long> {


}
