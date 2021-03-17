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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import zerogaspi.dao.IEntreprise;
import zerogaspi.dao.IEntreprise;
import zerogaspi.model.Entreprise;


@RestController
@RequestMapping("/api/entreprise")
public class EntrepriseApiRestController {
	@Autowired
	private IEntreprise entrepriseDao;

	@GetMapping("")
	public List<Entreprise> list() {
		List<Entreprise> Entreprises = entrepriseDao.findAll();

		return Entreprises; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	public Entreprise find(@PathVariable Long id) {
		Optional<Entreprise> optEntreprise = entrepriseDao.findById(id);

		if (optEntreprise.isPresent()) {
			return optEntreprise.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public Entreprise create(Entreprise Entreprise) {	
		Entreprise = entrepriseDao.save(Entreprise);

		return Entreprise;
	}

	@PutMapping("/{id}")
	public Entreprise update(@RequestBody Entreprise Entreprise, @PathVariable Long id) {
		if (!entrepriseDao.existsById(id) || !id.equals(Entreprise.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Entreprise = entrepriseDao.save(Entreprise);

		return Entreprise;
	}

	@PatchMapping("/{id}")
	public Entreprise partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!entrepriseDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Entreprise EntrepriseFind = entrepriseDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Entreprise.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, EntrepriseFind, value);
		});

		Entreprise EntrepriseUpdate = entrepriseDao.save(EntrepriseFind);

		return EntrepriseUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!entrepriseDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		entrepriseDao.deleteById(id);

		if (entrepriseDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}

}