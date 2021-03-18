package zerogaspi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerogaspi.dao.ICommande;
import zerogaspi.model.Commande;

@SpringBootTest
public class CommandeTest {

	@Autowired
	private ICommande commandeDao;

	@Test
	public void findByDateEnvoieDesc() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Commande> commandes2 = commandeDao.findByDateEnvoieDesc(sdf.parse("28/03/2021"));
		
		
		Commande commande = new Commande(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande = commandeDao.save(commande);

		Commande commande1 = new Commande(sdf.parse("29/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande1 = commandeDao.save(commande1);

		Commande commande2 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande2 = commandeDao.save(commande2);

		List<Commande> commandes = commandeDao.findByDateEnvoieDesc(sdf.parse("28/03/2021"));
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findByDateEnvoieAsc() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Commande> commandes2 = commandeDao.findByDateEnvoieAsc(sdf.parse("28/03/2021"));
		
		
		Commande commande = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande = commandeDao.save(commande);

		Commande commande1 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande1 = commandeDao.save(commande1);

		Commande commande2 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande2 = commandeDao.save(commande2);

		List<Commande> commandes = commandeDao.findByDateEnvoieAsc(sdf.parse("28/03/2021"));
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findByDatePaiementDesc() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Commande> commandes2 = commandeDao.findByDatePaiementDesc(sdf.parse("30/03/2021"));
		
		
		Commande commande = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande = commandeDao.save(commande);

		Commande commande1 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande1 = commandeDao.save(commande1);

		Commande commande2 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande2 = commandeDao.save(commande2);

		List<Commande> commandes = commandeDao.findByDatePaiementDesc(sdf.parse("30/03/2021"));
		System.out.println(commandes);
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findByDatePaiementAsc() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Commande> commandes2 = commandeDao.findByDatePaiementAsc(sdf.parse("30/03/2021"));
		
		
		Commande commande = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande = commandeDao.save(commande);

		Commande commande1 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande1 = commandeDao.save(commande1);

		Commande commande2 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande2 = commandeDao.save(commande2);

		List<Commande> commandes = commandeDao.findByDatePaiementAsc(sdf.parse("30/03/2021"));
		System.out.println(commandes);
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findByDateArriveeDesc() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Commande> commandes2 = commandeDao.findByDateArriveeDesc(sdf.parse("29/03/2021"));
		
		
		Commande commande = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande = commandeDao.save(commande);

		Commande commande1 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande1 = commandeDao.save(commande1);

		Commande commande2 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande2 = commandeDao.save(commande2);

		List<Commande> commandes = commandeDao.findByDateArriveeDesc(sdf.parse("29/03/2021"));
		System.out.println(commandes);
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findByDateArriveeAsc() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<Commande> commandes2 = commandeDao.findByDateArriveeAsc(sdf.parse("29/03/2021"));
		
		
		Commande commande = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande = commandeDao.save(commande);

		Commande commande1 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande1 = commandeDao.save(commande1);

		Commande commande2 = new Commande(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				null);
		commande2 = commandeDao.save(commande2);

		List<Commande> commandes = commandeDao.findByDateArriveeAsc(sdf.parse("29/03/2021"));
		System.out.println(commandes);
		assertEquals(3, commandes.size() - commandes2.size());

	}



}
