package zerogaspi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import zerogaspi.dao.IClient;
import zerogaspi.dao.IConnexion;
import zerogaspi.dao.IEntreprise;
import zerogaspi.dao.IFavoris;
import zerogaspi.dao.ILot;
import zerogaspi.dao.IVendeur;
import zerogaspi.model.Client;
import zerogaspi.model.Connexion;
import zerogaspi.model.Entreprise;
import zerogaspi.model.ListeFavori;
import zerogaspi.model.Lot;
import zerogaspi.model.Vendeur;

@SpringBootTest
public class FavorisTest {
	
	@Autowired
	private IFavoris favorisDao;
	
	@Autowired
	private IEntreprise entrepriseDao;
	
	@Autowired
	 private IClient clientDao;
	
	@Autowired
	private ILot lotDao;
	
	@Autowired
	private IConnexion connexionDao;
	
	@Autowired
	private IVendeur vendeurDao;
	
	@Test
	public void listerEntreprisesFavoris() {
		//Arrange 
		Connexion connexion2 = new Connexion("bilel@bilel.com", "dscvddf888");
		connexion2 = connexionDao.save(connexion2);
		Client client = new Client("0764585212", "rue des ca", "59200", "adresseouai", "Dupont", "Paul", connexion2 ,20  );
		client = clientDao.save(client);
		List<Entreprise> debut = favorisDao.findByClient(client);
		
		Connexion connexion = new Connexion("toto@toto.com", "azertyui123");
		connexion = connexionDao.save(connexion);
		Vendeur vendeur = new Vendeur("0645993786","rue bidule","59000","Lille","Nom","Prenom", connexion,new Date());
		vendeur = vendeurDao.save(vendeur);
		Entreprise entreprise = new Entreprise("399 826 981 00017", "Boulangerie TOTO", "Boulangerie", vendeur);
		entreprise = entrepriseDao.save(entreprise);
		Entreprise entreprise2 = new Entreprise("399 826 981 00017", "Boucherie TOTO", "Boucherie", vendeur);
		entreprise2 = entrepriseDao.save(entreprise2);
		favorisDao.save(new ListeFavori(client, entreprise));
		favorisDao.save(new ListeFavori(client, entreprise2));

		List<Entreprise> fin = favorisDao.findByClient(client);
		
		assertEquals(2, fin.size() - debut.size() );
	}
	
	
	
	
	
	

}
