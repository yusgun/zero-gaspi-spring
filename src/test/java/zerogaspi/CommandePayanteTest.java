package zerogaspi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerogaspi.dao.ICommandePayante;
import zerogaspi.model.CommandePayante;

@SpringBootTest
public class CommandePayanteTest {
	
	@Autowired
	private ICommandePayante commandePayanteDao;

	@Test
	public void findCpByDateEnvoieDesc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandePayante> commandes2 = commandePayanteDao.findCpByDateEnvoieDesc();
		
		
		CommandePayante commande = new CommandePayante(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande = commandePayanteDao.save(commande);

		CommandePayante commande1 = new CommandePayante(sdf.parse("29/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandePayanteDao.save(commande1);

		CommandePayante commande2 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande2 = commandePayanteDao.save(commande2);

		List<CommandePayante> commandes = commandePayanteDao.findCpByDateEnvoieDesc();
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findCpByDateEnvoieAsc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandePayante> commandes2 = commandePayanteDao.findCpByDateEnvoieAsc();
		
		CommandePayante commande = new CommandePayante(sdf.parse("28/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande = commandePayanteDao.save(commande);

		CommandePayante commande1 = new CommandePayante(sdf.parse("29/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandePayanteDao.save(commande1);

		CommandePayante commande2 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande2 = commandePayanteDao.save(commande2);

		List<CommandePayante> commandes = commandePayanteDao.findCpByDateEnvoieAsc();
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findCpByDatePaiementDesc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandePayante> commandes2 = commandePayanteDao.findCpByDatePaiementDesc();
		
		CommandePayante commande = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande = commandePayanteDao.save(commande);

		CommandePayante commande1 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandePayanteDao.save(commande1);

		CommandePayante commande2 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande2 = commandePayanteDao.save(commande2);

		List<CommandePayante> commandes = commandePayanteDao.findCpByDatePaiementDesc();
		assertEquals(3, commandes.size() - commandes2.size());

	}
	
	@Test
	public void findCpByDatePaiementAsc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandePayante> commandes2 = commandePayanteDao.findCpByDatePaiementAsc();
		
		CommandePayante commande = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande = commandePayanteDao.save(commande);

		CommandePayante commande1 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandePayanteDao.save(commande1);

		CommandePayante commande2 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande2 = commandePayanteDao.save(commande2);

		List<CommandePayante> commandes = commandePayanteDao.findCpByDatePaiementAsc();
		assertEquals(3, commandes.size() - commandes2.size());

	}

	@Test
	public void findCpByDateArriveeDesc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandePayante> commandes2 = commandePayanteDao.findCpByDateArriveeDesc();
		
		CommandePayante commande = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande = commandePayanteDao.save(commande);

		CommandePayante commande1 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandePayanteDao.save(commande1);

		CommandePayante commande2 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande2 = commandePayanteDao.save(commande2);

		List<CommandePayante> commandes = commandePayanteDao.findCpByDateArriveeDesc();
		assertEquals(3, commandes.size() - commandes2.size());

	}

	@Test
	public void findCpByDateArriveeAsc() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		List<CommandePayante> commandes2 = commandePayanteDao.findCpByDateArriveeAsc();
		
		CommandePayante commande = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande = commandePayanteDao.save(commande);

		CommandePayante commande1 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande1 = commandePayanteDao.save(commande1);

		CommandePayante commande2 = new CommandePayante(sdf.parse("30/03/2021"), sdf.parse("28/03/2021"), sdf.parse("29/03/2021"),
				25.0, null);
		commande2 = commandePayanteDao.save(commande2);

		List<CommandePayante> commandes = commandePayanteDao.findCpByDateArriveeAsc();
		assertEquals(3, commandes.size() - commandes2.size());

	}

}
