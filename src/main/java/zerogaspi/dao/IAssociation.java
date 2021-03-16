package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.Association;

public interface IAssociation extends JpaRepository<Association, Long> {

}
