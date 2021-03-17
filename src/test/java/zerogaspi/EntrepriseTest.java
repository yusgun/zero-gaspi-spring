package zerogaspi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import zerogaspi.dao.IConnexion;
import zerogaspi.dao.IEntreprise;
import zerogaspi.dao.IVendeur;
import zerogaspi.model.Connexion;
import zerogaspi.model.Entreprise;
import zerogaspi.model.Vendeur;

@SpringBootTest
public class EntrepriseTest {
	
	@Autowired
	private IEntreprise entrepriseDao;
	
	@Autowired
	private IConnexion connexionDao;
	
	@Autowired
	private IVendeur vendeurDao;
	
	@Test
	/*
	 *Test des recherches d'un Client pour le nom, codepostal et la ville d'une entreprise
	 * Table : Entreprise avec jointure vendeur qui hérite d'identité
	 */
	public void entrepriseFindByVilleOrCPOrNom() throws ParseException {
		int sizeStart1 = entrepriseDao.findByNomOrCPOrVille("Boulangerie", "","").size();
		int sizeStart2 = entrepriseDao.findByNomOrCPOrVille("", "Lille","").size();
		int sizeStart3 = entrepriseDao.findByNomOrCPOrVille("", "","59000").size();
		
		Connexion connexion = new Connexion("toto@toto.com", "azertyui123");
		connexion = connexionDao.save(connexion);
		Vendeur vendeur = new Vendeur("0645993786","rue bidule","59000","Lille","Nom","Prenom", connexion,new Date());
		vendeur = vendeurDao.save(vendeur);
		Entreprise entreprise = new Entreprise("399 826 981 00017", "Boulangerie TOTO", "Boulangerie", vendeur);
		entreprise = entrepriseDao.save(entreprise);
		
		Vendeur vendeur1 = new Vendeur("0645993786","rue des machins","59000","Lille","NomDuVendeur","PrenomDuVendeur", connexion,new Date());
		vendeur1 = vendeurDao.save(vendeur1);
		Entreprise entreprise1 = new Entreprise("399 826 981 00017", "Boulangerie TOTO", "Boulangerie", vendeur1);
		entreprise1 = entrepriseDao.save(entreprise1);
		
		Vendeur vendeur2 = new Vendeur("0645993786","rue des machins","75000","Paris","NomDuVendeur","PrenomDuVendeur", connexion,new Date());
		vendeur2 = vendeurDao.save(vendeur2);
		Entreprise entreprise2 = new Entreprise("399 826 981 00017", "Boucherie TUTU", "Boucherie", vendeur2);
		entreprise2 = entrepriseDao.save(entreprise2);
		
		
		int sizeEnd1 = entrepriseDao.findByNomOrCPOrVille("Boulangerie", "","").size();
		int sizeEnd2 = entrepriseDao.findByNomOrCPOrVille("", "Lille","").size();
		int sizeEnd3 = entrepriseDao.findByNomOrCPOrVille("", "","59000").size();
		
		assertEquals(2, sizeEnd1 - sizeStart1);
		assertEquals(2, sizeEnd2 - sizeStart2);
		assertEquals(2, sizeEnd3 - sizeStart3);
	}
}
