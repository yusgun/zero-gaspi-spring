package zerogaspi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerogaspi.dao.ICommandeGratuite;
import zerogaspi.model.CommandeGratuite;

@SpringBootTest
public class CommandeGratuiteTest {

	@Autowired
	private ICommandeGratuite commandeGratuiteDao;
	
	@Test
	public void findCgByDateEnvoieDesc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandeGratuite> commandes2 = commandeGratuiteDao.findCgByDateEnvoieDesc();
		
		
		CommandeGratuite commande = new CommandeGratuite(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("28/03/2021"),
				25.0, null);
		commande = commandeGratuiteDao.save(commande);

		CommandeGratuite commande1 = new CommandeGratuite(sdf.parse("29/03/2021"), sdf.parse("29/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandeGratuiteDao.save(commande1);

		CommandeGratuite commande2 = new CommandeGratuite(sdf.parse("30/03/2021"), sdf.parse("30/03/2021"), sdf.parse("30/03/2021"),
				25.0, null);
		commande2 = commandeGratuiteDao.save(commande2);

		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateEnvoieDesc();
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findCpByDateEnvoieAsc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandeGratuite> commandes2 = commandeGratuiteDao.findCgByDateEnvoieAsc();
		
		CommandeGratuite commande = new CommandeGratuite(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("28/03/2021"),
				25.0, null);
		commande = commandeGratuiteDao.save(commande);

		CommandeGratuite commande1 = new CommandeGratuite(sdf.parse("29/03/2021"), sdf.parse("29/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandeGratuiteDao.save(commande1);

		CommandeGratuite commande2 = new CommandeGratuite(sdf.parse("30/03/2021"), sdf.parse("30/03/2021"), sdf.parse("30/03/2021"),
				25.0, null);
		commande2 = commandeGratuiteDao.save(commande2);

		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateEnvoieAsc();
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findCpByDatePaiementDesc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandeGratuite> commandes2 = commandeGratuiteDao.findCgByDatePaiementDesc();
		
		CommandeGratuite commande = new CommandeGratuite(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("28/03/2021"),
				25.0, null);
		commande = commandeGratuiteDao.save(commande);

		CommandeGratuite commande1 = new CommandeGratuite(sdf.parse("29/03/2021"), sdf.parse("29/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandeGratuiteDao.save(commande1);

		CommandeGratuite commande2 = new CommandeGratuite(sdf.parse("30/03/2021"), sdf.parse("30/03/2021"), sdf.parse("30/03/2021"),
				25.0, null);
		commande2 = commandeGratuiteDao.save(commande2);

		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDatePaiementDesc();
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findCpByDatePaiementAsc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandeGratuite> commandes2 = commandeGratuiteDao.findCgByDatePaiementAsc();
		
		CommandeGratuite commande = new CommandeGratuite(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("28/03/2021"),
				25.0, null);
		commande = commandeGratuiteDao.save(commande);

		CommandeGratuite commande1 = new CommandeGratuite(sdf.parse("29/03/2021"), sdf.parse("29/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandeGratuiteDao.save(commande1);

		CommandeGratuite commande2 = new CommandeGratuite(sdf.parse("30/03/2021"), sdf.parse("30/03/2021"), sdf.parse("30/03/2021"),
				25.0, null);
		commande2 = commandeGratuiteDao.save(commande2);

		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDatePaiementAsc();
		assertEquals(3, commandes.size() - commandes2.size());

	}

	@Test
	public void findCpByDateArriveeDesc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandeGratuite> commandes2 = commandeGratuiteDao.findCgByDateArriveeDesc();
		
		CommandeGratuite commande = new CommandeGratuite(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("28/03/2021"),
				25.0, null);
		commande = commandeGratuiteDao.save(commande);

		CommandeGratuite commande1 = new CommandeGratuite(sdf.parse("29/03/2021"), sdf.parse("29/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandeGratuiteDao.save(commande1);

		CommandeGratuite commande2 = new CommandeGratuite(sdf.parse("30/03/2021"), sdf.parse("30/03/2021"), sdf.parse("30/03/2021"),
				25.0, null);
		commande2 = commandeGratuiteDao.save(commande2);

		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateArriveeDesc();
		assertEquals(3, commandes.size() - commandes2.size());

	}

	@Test
	public void findCpByDateArriveeAsc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandeGratuite> commandes2 = commandeGratuiteDao.findCgByDateArriveeAsc();
		
		CommandeGratuite commande = new CommandeGratuite(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("28/03/2021"),
				25.0, null);
		commande = commandeGratuiteDao.save(commande);

		CommandeGratuite commande1 = new CommandeGratuite(sdf.parse("29/03/2021"), sdf.parse("29/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandeGratuiteDao.save(commande1);

		CommandeGratuite commande2 = new CommandeGratuite(sdf.parse("30/03/2021"), sdf.parse("30/03/2021"), sdf.parse("30/03/2021"),
				25.0, null);
		commande2 = commandeGratuiteDao.save(commande2);

		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateArriveeAsc();
		assertEquals(3, commandes.size() - commandes2.size());

	}

}
