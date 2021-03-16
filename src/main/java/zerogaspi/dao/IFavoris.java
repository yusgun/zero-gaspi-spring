package zerogaspi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zerogaspi.model.ListeFavori;

public interface IFavoris extends JpaRepository<ListeFavori, Long> {

}
