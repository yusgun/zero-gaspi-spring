package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.Particulier;

public interface IParticulier extends JpaRepository<Particulier, Long> {

}
