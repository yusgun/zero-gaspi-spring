package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import zerogaspi.model.Entreprise;
import zerogaspi.model.Vendeur;

public interface IVendeur extends JpaRepository<Vendeur, Long> {

}
