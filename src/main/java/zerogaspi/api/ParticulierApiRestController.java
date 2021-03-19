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
import zerogaspi.dao.IParticulier;
import zerogaspi.model.IViews;
import zerogaspi.model.Particulier;

@RestController
@RequestMapping("/particulier")
public class ParticulierApiRestController {

	@Autowired
	private IParticulier particulierDao;

	@GetMapping("")
	@JsonView(IViews.IViewParticulier.class)
	public List<Particulier> list() {
		List<Particulier> Particuliers = particulierDao.findAll();
		return Particuliers;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewParticulier.class)
	public Particulier find(@PathVariable Long id) {
		Optional<Particulier> optParticulier = particulierDao.findById(id);

		if (optParticulier.isPresent()) {
			return optParticulier.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	@JsonView(IViews.IViewParticulier.class)
	public Particulier create(@Valid @RequestBody Particulier Particulier,BindingResult result) {	
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Particulier = particulierDao.save(Particulier);

		return Particulier;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewParticulier.class)
	public Particulier update(@Valid @RequestBody Particulier Particulier, BindingResult result, @PathVariable Long id) {
		if (!particulierDao.existsById(id) || !id.equals(Particulier.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Particulier = particulierDao.save(Particulier);

		return Particulier;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewParticulier.class)
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
}
