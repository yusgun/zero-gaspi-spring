package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.Lot;

public interface ILot extends JpaRepository<Lot, Long> {

}
