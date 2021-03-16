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

import zerogaspi.dao.IPaiement;
import zerogaspi.dao.IPaiement;
import zerogaspi.model.Paiement;

@RestController
@RequestMapping("/api/paiement")
public class PaiementApiRestController {
	@Autowired
	private IPaiement paiementDao;

	@GetMapping("")
	public List<Paiement> list() {
		List<Paiement> Paiements = paiementDao.findAll();

		return Paiements; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	public Paiement find(@PathVariable Long id) {
		Optional<Paiement> optPaiement = paiementDao.findById(id);

		if (optPaiement.isPresent()) {
			return optPaiement.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public Paiement create(Paiement Paiement) {	
		Paiement = paiementDao.save(Paiement);

		return Paiement;
	}

	@PutMapping("/{id}")
	public Paiement update(@RequestBody Paiement Paiement, @PathVariable Long id) {
		if (!paiementDao.existsById(id) || !id.equals(Paiement.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Paiement = paiementDao.save(Paiement);

		return Paiement;
	}

	@PatchMapping("/{id}")
	public Paiement partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!paiementDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Paiement PaiementFind = paiementDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Paiement.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, PaiementFind, value);
		});

		Paiement PaiementUpdate = paiementDao.save(PaiementFind);

		return PaiementUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!paiementDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		paiementDao.deleteById(id);

		if (paiementDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
