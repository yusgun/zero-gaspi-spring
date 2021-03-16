package zerogaspi.api;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import zerogaspi.dao.IConnexion;
import zerogaspi.model.Commande;
import zerogaspi.model.Connexion;

public class ConnexionApiRestController {
	@Autowired
	private IConnexion connexionDao;

	@GetMapping("")
	public List<Connexion> list() {
		List<Connexion> Commandes = connexionDao.findAll();

		return Commandes; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	public Connexion find(@PathVariable Long id) {
		Optional<Connexion> optCommande = connexionDao.findById(id);

		if (optCommande.isPresent()) {
			return optCommande.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public Connexion create(Connexion Connexion) {	
		Connexion = connexionDao.save(Connexion);

		return Connexion;
	}

	@PutMapping("/{id}")
	public Connexion update(@RequestBody Connexion Connexion, @PathVariable Long id) {
		if (!connexionDao.existsById(id) || !id.equals(Connexion.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Connexion = connexionDao.save(Connexion);

		return Connexion;
	}

	@PatchMapping("/{id}")
	public Connexion partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!connexionDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Connexion CommandeFind = connexionDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Commande.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, CommandeFind, value);
		});

		Connexion CommandeUpdate = connexionDao.save(CommandeFind);

		return CommandeUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!connexionDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		connexionDao.deleteById(id);

		if (connexionDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
