package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import zerogaspi.model.Commande;

public interface ICommande extends JpaRepository<Commande, Long> {


}
