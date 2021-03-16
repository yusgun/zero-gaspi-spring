package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.Paiement;

public interface IPaiement extends JpaRepository<Paiement, Long>{

}
