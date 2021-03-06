package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Commande;
import zerogaspi.model.CommandeGratuite;

public interface ICommande extends JpaRepository<Commande, Long> {


}
