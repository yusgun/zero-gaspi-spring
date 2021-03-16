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

import zerogaspi.dao.IVendeur;
import zerogaspi.model.Vendeur;

public class VendeurApiRestControlleur {
	
	@Autowired
	private IVendeur vendeurDao;

	@GetMapping("")
	public List<Vendeur> list() {
		List<Vendeur> Vendeurs = vendeurDao.findAll();

		return Vendeurs;
	}

	@GetMapping("/{id}")
	public Vendeur find(@PathVariable Long id) {
		Optional<Vendeur> optVendeur = vendeurDao.findById(id);

		if (optVendeur.isPresent()) {
			return optVendeur.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public Vendeur create(Vendeur Vendeur) {	
		Vendeur = vendeurDao.save(Vendeur);

		return Vendeur;
	}

	@PutMapping("/{id}")
	public Vendeur update(@RequestBody Vendeur Vendeur, @PathVariable Long id) {
		if (!vendeurDao.existsById(id) || !id.equals(Vendeur.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Vendeur = vendeurDao.save(Vendeur);

		return Vendeur;
	}

	@PatchMapping("/{id}")
	public Vendeur partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!vendeurDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Vendeur VendeurFind = vendeurDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Vendeur.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, VendeurFind, value);
		});

		Vendeur VendeurUpdate = vendeurDao.save(VendeurFind);

		return VendeurUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!vendeurDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		vendeurDao.deleteById(id);

		if (vendeurDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}

}
