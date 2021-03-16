package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.Identite;

public interface IIdentite extends JpaRepository<Identite,Long> {

}
