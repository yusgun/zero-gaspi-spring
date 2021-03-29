package zerogaspi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zerogaspi.model.Client;
import zerogaspi.model.Entreprise;
import zerogaspi.model.ListeFavori;

public interface IFavoris extends JpaRepository<ListeFavori, Long> {

	
	@Query("select distinct lf.entreprise.nomEntreprise, lf.entreprise.vendeur.numeroTelephone, lf.entreprise.vendeur.rue, lf.entreprise.vendeur.ville, lf.entreprise.vendeur.codePostal, lf.entreprise.vendeur.id  from ListeFavori lf where lf.client.id = :id")
	List<Object[]>findByClient(@Param("id") Long id);
	
	
}
