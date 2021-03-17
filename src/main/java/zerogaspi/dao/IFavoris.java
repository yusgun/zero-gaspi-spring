package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Client;
import zerogaspi.model.Entreprise;
import zerogaspi.model.ListeFavori;

public interface IFavoris extends JpaRepository<ListeFavori, Long> {

	
	@Query("select lf.entreprise from ListeFavori lf where lf.client = :client")
	List<Entreprise>findByClient(@Param("client") Client client);
	
	
	//@Query("select lf.entreprise from ListeFavori lf where lf.client = :client")
	//List<Entreprise>findByClient(@Param("client") Client client);
	
	//@Query("select lf.entreprise from ListeFavori lf where lf.client = :client")
	//List<Entreprise>findByClient(@Param("client") Client client);
	
}
