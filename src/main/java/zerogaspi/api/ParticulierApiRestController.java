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

import zerogaspi.dao.IParticulier;
import zerogaspi.dao.IParticulier;
import zerogaspi.model.Particulier;

@RestController
@RequestMapping("/api/particulier")
public class ParticulierApiRestController {

	@Autowired
	private IParticulier particulierDao;

	@GetMapping("")
	public List<Particulier> list() {
		List<Particulier> Particuliers = particulierDao.findAll();
		return Particuliers;
	}

	@GetMapping("/{id}")
	public Particulier find(@PathVariable Long id) {
		Optional<Particulier> optParticulier = particulierDao.findById(id);

		if (optParticulier.isPresent()) {
			return optParticulier.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public Particulier create(Particulier Particulier) {	
		Particulier = particulierDao.save(Particulier);

		return Particulier;
	}

	@PutMapping("/{id}")
	public Particulier update(@RequestBody Particulier Particulier, @PathVariable Long id) {
		if (!particulierDao.existsById(id) || !id.equals(Particulier.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Particulier = particulierDao.save(Particulier);

		return Particulier;
	}

	@PatchMapping("/{id}")
	public Particulier partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!particulierDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Particulier ParticulierFind = particulierDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Particulier.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, ParticulierFind, value);
		});

		Particulier ParticulierUpdate = particulierDao.save(ParticulierFind);

		return ParticulierUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!particulierDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		particulierDao.deleteById(id);

		if (particulierDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
<<<<<<< HEAD
=======

>>>>>>> a9ca29f9b62f7b3fffee77c7b421eaebdf2fdb73
}
