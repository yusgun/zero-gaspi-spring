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
import zerogaspi.dao.IPaiement;
import zerogaspi.model.IViews;
import zerogaspi.model.Paiement;

@RestController
@RequestMapping("/paiement")
public class PaiementApiRestController {

	@Autowired
	private IPaiement paiementDao;

	@GetMapping("")
	public List<Paiement> list() {
		List<Paiement> Paiements = paiementDao.findAll();

		return Paiements;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewPaiement.class)
	public Paiement find(@PathVariable Long id) {
		Optional<Paiement> optPaiement = paiementDao.findById(id);

		if (optPaiement.isPresent()) {
			return optPaiement.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewPaiement.class)
	public Paiement create(@Valid @RequestBody Paiement Paiement, BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Paiement = paiementDao.save(Paiement);

		return Paiement;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewPaiement.class)
	public Paiement update(@Valid @RequestBody Paiement Paiement, BindingResult result, @PathVariable Long id) {
		if (!paiementDao.existsById(id) || !id.equals(Paiement.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Paiement = paiementDao.save(Paiement);

		return Paiement;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewPaiement.class)
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
