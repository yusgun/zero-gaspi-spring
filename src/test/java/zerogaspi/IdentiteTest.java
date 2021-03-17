package zerogaspi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import zerogaspi.dao.IConnexion;
import zerogaspi.dao.IIdentite;
import zerogaspi.model.Association;
import zerogaspi.model.Client;
import zerogaspi.model.Connexion;
import zerogaspi.model.Entreprise;
import zerogaspi.model.Identite;
import zerogaspi.model.Vendeur;

@SpringBootTest
public class IdentiteTest {
	
	@Autowired
	private IIdentite identiteDao;
	@Autowired
	private IConnexion connexionDao;
	
	@Test
	/**
	 * Test des identifiants de connexion d'un utilisateur
	 * Table : Ientite avec jointure sur la table Connexion
	 */
	public void identifiantConnexion() {
		// Création de la connexion et de l'identite
		Connexion connexion1 = new Connexion("alphonse.denis@email.com", "0123456");
		Identite identite1 = new Client("0606060606", "5 rue Jules Ferry", "59000", "Lille", "DENIS", "Alphonse", connexion1, 20);
		Connexion connexion2 = new Connexion("AntoinePatenaude@armyspy.com", "Ahgho2aeph9");
		Identite identite2 = new Association("0108132116", "11 rue du 25 décembre", "57000", "Metz", "Patenaude", "Antoine", connexion2, 15);
		// Ajout à la base
		connexionDao.save(connexion1);
		identiteDao.save(identite1);
		connexionDao.save(connexion2);
		identiteDao.save(identite2);
		// Recherche de la connexion de l'utilisateur
		Connexion findConnexion1 = identiteDao.findCredentialsById(identite1.getId());
		Connexion findConnexion2 = identiteDao.findCredentialsById(identite2.getId());
		// Lancement des tests...
		assertEquals("alphonse.denis@email.com", findConnexion1.getMail());
		assertEquals("0123456", findConnexion1.getMotDePasse());
		assertEquals("AntoinePatenaude@armyspy.com", findConnexion2.getMail());
		assertEquals("Ahgho2aeph9", findConnexion2.getMotDePasse());
	}
}
