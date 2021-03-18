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

import com.fasterxml.jackson.annotation.JsonView;

import zerogaspi.dao.IIdentite;
import zerogaspi.dao.IIdentite;
import zerogaspi.model.IViews;
import zerogaspi.model.Identite;

@RestController
@RequestMapping("/api/identite")
public class IdentiteApiRestController {

	@Autowired
	private IIdentite identiteDao;

	@GetMapping("")
	public List<Identite> list() {
		List<Identite> Identites = identiteDao.findAll();
		return Identites;

	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewIdentite.class)
	public Identite find(@PathVariable Long id) {
		Optional<Identite> optIdentite = identiteDao.findById(id);

		if (optIdentite.isPresent()) {
			return optIdentite.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	@JsonView(IViews.IViewIdentite.class)
	public Identite create(Identite Identite) {	
		Identite = identiteDao.save(Identite);

		return Identite;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewIdentite.class)
	public Identite update(@RequestBody Identite Identite, @PathVariable Long id) {
		if (!identiteDao.existsById(id) || !id.equals(Identite.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Identite = identiteDao.save(Identite);

		return Identite;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewIdentite.class)
	public Identite partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!identiteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Identite IdentiteFind = identiteDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Identite.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, IdentiteFind, value);
		});

		Identite IdentiteUpdate = identiteDao.save(IdentiteFind);

		return IdentiteUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!identiteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		identiteDao.deleteById(id);

		if (identiteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
