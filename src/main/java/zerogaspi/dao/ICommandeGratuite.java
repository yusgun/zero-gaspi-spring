package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.CommandeGratuite;

public interface ICommandeGratuite extends JpaRepository<CommandeGratuite, Long> {
	
}
