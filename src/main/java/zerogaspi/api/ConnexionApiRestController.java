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

import zerogaspi.dao.IConnexion;
import zerogaspi.model.Connexion;
import zerogaspi.model.IViews;

@RestController
// Virer "/api"
@RequestMapping("/connexion")
public class ConnexionApiRestController {
	@Autowired
	private IConnexion connexionDao;

	@GetMapping("")
	@JsonView(IViews.IViewConnexion.class)
	public List<Connexion> list() {
		List<Connexion> Connexions = connexionDao.findAll();

		return Connexions; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewConnexion.class)
	public Connexion find(@PathVariable Long id) {
		Optional<Connexion> optConnexion = connexionDao.findById(id);

		if (optConnexion.isPresent()) {
			return optConnexion.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewConnexion.class)
	// Ajouter @Valid @RequestBody sur le premier paramètre
	// Ajouter BindingResult result en 2e paramètre
	// Ajouter le if avant l'objet du modele
	public Connexion create(@Valid @RequestBody Connexion Connexion, BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Connexion = connexionDao.save(Connexion);

		return Connexion;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewConnexion.class)
	// Ajout @Valid en premier parametre
	// Ajouter BindingResult result en 2e paramètre
	public Connexion update(@Valid @RequestBody Connexion Connexion, @PathVariable Long id, BindingResult result) {
		if (!connexionDao.existsById(id) || !id.equals(Connexion.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Connexion = connexionDao.save(Connexion);

		return Connexion;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewConnexion.class)
	public Connexion partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!connexionDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Connexion ConnexionFind = connexionDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Connexion.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, ConnexionFind, value);
		});

		Connexion ConnexionUpdate = connexionDao.save(ConnexionFind);

		return ConnexionUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!connexionDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		connexionDao.deleteById(id);

		if (connexionDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
