package zerogaspi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerogaspi.dao.ICommande;
import zerogaspi.model.Commande;

@SpringBootTest
public class CommandeTest {
	
	@Autowired
	private ICommande commandeDao;
	
//	@Test
//	public void findByListeProduits() {
//		int sizeStart = commandeDao.findByListeProduits("fruits, légumes").size();
//		
//		Commande commande = new Commande("fruits, légumes",new Date(),new Date(),new Date());
//		commande = commandeDao.save(commande);
//		
//		Commande commande1 = new Commande("fruits, légumes",new Date(),new Date(),new Date());
//		commande1 = commandeDao.save(commande1);
//		
//		Commande commande2 = new Commande("fruits, viennoiserie",new Date(),new Date(),new Date());
//		commande2 = commandeDao.save(commande2);
//		
//		int sizeEnd = commandeDao.findByListeProduits("fruits, légumes").size();
//
//		assertEquals(2, sizeEnd - sizeStart);
//
//	}

}
