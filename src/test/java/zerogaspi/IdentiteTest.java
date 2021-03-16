package zerogaspi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import zerogaspi.dao.IConnexion;
import zerogaspi.dao.IIdentite;
import zerogaspi.model.Client;
import zerogaspi.model.Connexion;
import zerogaspi.model.Identite;

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
		Connexion connexion = new Connexion("alphonse.denis@email.com", "0123456");
		Identite identite = new Client("0606060606", "5 rue Jules Ferry", "59000", "Lille", "DENIS", "Alphonse", connexion, 20);
		// Ajout à la base
		connexionDao.save(connexion);
		identiteDao.save(identite);
		// Recherche de la connexion de l'utilisateur
		Connexion findConnexion = identiteDao.findCredentialsById(identite.getId());
		// Lancement des tests...
		assertEquals("alphonse.denis@email.com", findConnexion.getMail());
		assertEquals("0123456", findConnexion.getMotDePasse());
	}
}
