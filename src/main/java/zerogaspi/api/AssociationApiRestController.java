package zerogaspi.api;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.fasterxml.jackson.annotation.JsonView;

import zerogaspi.dao.IAssociation;
import zerogaspi.model.Association;
import zerogaspi.model.IViews;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/association")
public class AssociationApiRestController {
	
	@Autowired
	private IAssociation associationDao;

	@GetMapping("")
	@JsonView(IViews.IViewAssociation.class)
	public List<Association> list() {
		List<Association> Associations = associationDao.findAll();

		return Associations; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewAssociation.class)
	public Association find(@PathVariable Long id) {
		Optional<Association> optAssociation = associationDao.findById(id);

		if (optAssociation.isPresent()) {
			return optAssociation.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	@JsonView(IViews.IViewAssociation.class)
	public Association create(@Valid @RequestBody Association Association, BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
						errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Association = associationDao.save(Association);
		return Association;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewAssociation.class)
	public Association update(@Valid @RequestBody Association Association, @PathVariable Long id, BindingResult result) {
		if (!associationDao.existsById(id) || !id.equals(Association.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
						errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Association = associationDao.save(Association);
		return Association;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewAssociation.class)
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
