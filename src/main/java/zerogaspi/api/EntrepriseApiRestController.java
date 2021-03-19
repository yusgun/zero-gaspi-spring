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

import zerogaspi.dao.IEntreprise;
import zerogaspi.model.Entreprise;
import zerogaspi.model.IViews;

@RestController
@RequestMapping("/entreprise")
public class EntrepriseApiRestController {
	
	@Autowired
	private IEntreprise entrepriseDao;

	@GetMapping("")
	@JsonView(IViews.IViewEntreprise.class)
	public List<Entreprise> list() {
		List<Entreprise> Entreprises = entrepriseDao.findAll();

		return Entreprises; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewEntreprise.class)
	public Entreprise find(@PathVariable Long id) {
		Optional<Entreprise> optEntreprise = entrepriseDao.findById(id);

		if (optEntreprise.isPresent()) {
			return optEntreprise.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findby/{search}")
	@JsonView(IViews.IViewEntrepriseWithVendeur.class)
	public List<Object[]> findByOptionnal(@PathVariable String search) {
		if (search.equals("") || search.equals(null)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		List<Object[]> optEntreprise = entrepriseDao.findByNomOrCPOrVille(search);
		return optEntreprise;

	}

	@PostMapping("")
	@JsonView(IViews.IViewEntreprise.class)
	public Entreprise create(@Valid @RequestBody Entreprise Entreprise, BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Entreprise = entrepriseDao.save(Entreprise);
		return Entreprise;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewEntreprise.class)
	public Entreprise update(@Valid @RequestBody Entreprise Entreprise, @PathVariable Long id, BindingResult result) {
		if (!entrepriseDao.existsById(id) || !id.equals(Entreprise.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Entreprise = entrepriseDao.save(Entreprise);
		return Entreprise;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewEntreprise.class)
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
