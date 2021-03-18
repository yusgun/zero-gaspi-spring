package zerogaspi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import zerogaspi.dao.IConnexion;
import zerogaspi.dao.IEntreprise;
import zerogaspi.dao.ILot;
import zerogaspi.dao.IVendeur;
import zerogaspi.model.Connexion;
import zerogaspi.model.Entreprise;
import zerogaspi.model.Lot;
import zerogaspi.model.Vendeur;

@SpringBootTest
public class LotTest {
	@Autowired
	private ILot lotDao;
	@Autowired
	private IEntreprise entrepriseDao;
	@Autowired
	private IVendeur vendeurDao;
	@Autowired
	private IConnexion connexionDao;
	
	@Test
	/**
	 * Test d'affichage des lots par entreprise (destiné pour l'affichage des lots vendus par entreprise)
	 * Table : Lot
	 */
	public void affichageLotParEntreprise() {
		Connexion connexion = new Connexion("test@email.com", "gah7Pheizu");
		Vendeur vendeur1 = new Vendeur("0645993786", "rue des machins", "59000", "Lille", "NomDuVendeur", "PrenomDuVendeur", connexion, new Date());
		Entreprise entreprise1 = new Entreprise("399 826 981 00017", "Boulangerie TOTO", "Boulangerie", vendeur1);
		connexionDao.save(connexion);
		vendeurDao.save(vendeur1);
		entrepriseDao.save(entreprise1);
		List<Lot> listDepart = lotDao.findByEntreprise(entreprise1.getId());		
		Lot lot1 = new Lot("Patisserie #1", "Lot de patisserie 1", entreprise1);
		Lot lot2 = new Lot("Patisserie #2", "Lot de patisserie 2", entreprise1);
		lotDao.save(lot1);
		lotDao.save(lot2);
		List<Lot> listFin = lotDao.findByEntreprise(entreprise1.getId());		
		assertEquals(2, listFin.size() - listDepart.size());
	}
}
