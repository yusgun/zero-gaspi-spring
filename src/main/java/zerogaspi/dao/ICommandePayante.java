package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.CommandePayante;

public interface ICommandePayante extends JpaRepository<CommandePayante, Long> {

}
