package zerogaspi.api;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import zerogaspi.model.Association;
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


import zerogaspi.dao.IAssociation;

public class AssociationApiRestController {
	
	@Autowired
	private IAssociation associationDao;

	@GetMapping("")
	public List<Association> list() {
		List<Association> Associations = associationDao.findAll();

		return Associations; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	public Association find(@PathVariable Long id) {
		Optional<Association> optAssociation = associationDao.findById(id);

		if (optAssociation.isPresent()) {
			return optAssociation.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public Association create(Association Association) {	
		Association = associationDao.save(Association);

		return Association;
	}

	@PutMapping("/{id}")
	public Association update(@RequestBody Association Association, @PathVariable Long id) {
		if (!associationDao.existsById(id) || !id.equals(Association.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Association = associationDao.save(Association);

		return Association;
	}

	@PatchMapping("/{id}")
	public Association partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!associationDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Association AssociationFind = associationDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Association.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, AssociationFind, value);
		});

		Association AssociationUpdate = associationDao.save(AssociationFind);

		return AssociationUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!associationDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		associationDao.deleteById(id);

		if (associationDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
