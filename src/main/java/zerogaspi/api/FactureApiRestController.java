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

import zerogaspi.dao.IFacture;
import zerogaspi.dao.IFacture;
import zerogaspi.model.Facture;

@RestController
@RequestMapping("/api/facture")
public class FactureApiRestController {
	@Autowired
	private IFacture factureDao;

	@GetMapping("")
	public List<Facture> list() {
		List<Facture> Factures = factureDao.findAll();

		return Factures; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	public Facture find(@PathVariable Long id) {
		Optional<Facture> optFacture = factureDao.findById(id);

		if (optFacture.isPresent()) {
			return optFacture.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public Facture create(Facture Facture) {	
		Facture = factureDao.save(Facture);

		return Facture;
	}

	@PutMapping("/{id}")
	public Facture update(@RequestBody Facture Facture, @PathVariable Long id) {
		if (!factureDao.existsById(id) || !id.equals(Facture.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Facture = factureDao.save(Facture);

		return Facture;
	}

	@PatchMapping("/{id}")
	public Facture partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!factureDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Facture FactureFind = factureDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Facture.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, FactureFind, value);
		});

		Facture FactureUpdate = factureDao.save(FactureFind);

		return FactureUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!factureDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		factureDao.deleteById(id);

		if (factureDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
