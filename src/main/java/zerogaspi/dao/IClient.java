package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.Client;

public interface IClient extends JpaRepository<Client, Long> {

}
