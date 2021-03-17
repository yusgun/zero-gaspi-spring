package zerogaspi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import zerogaspi.dao.IAssociation;

@SpringBootTest
public class AssociationTest {
	
	@Autowired
	IAssociation associationDao;
	
	

}
