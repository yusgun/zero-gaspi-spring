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
import zerogaspi.dao.IVendeur;
import zerogaspi.model.IViews;
import zerogaspi.model.Vendeur;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/vendeur")
public class VendeurApiRestController {
	@Autowired
	private IVendeur vendeurDao;

	@GetMapping("")
	@JsonView(IViews.IViewVendeur.class)
	public List<Vendeur> list() {
		List<Vendeur> Vendeurs = vendeurDao.findAll();

		return Vendeurs; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewVendeur.class)
	public Vendeur find(@PathVariable Long id) {
		Optional<Vendeur> optVendeur = vendeurDao.findById(id);

		if (optVendeur.isPresent()) {
			return optVendeur.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	@JsonView(IViews.IViewVendeur.class)
	public Vendeur create(@Valid @RequestBody Vendeur Vendeur, BindingResult result) {	
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Vendeur = vendeurDao.save(Vendeur);

		return Vendeur;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewVendeur.class)
	public Vendeur update(@Valid @RequestBody Vendeur Vendeur, @PathVariable Long id, BindingResult result) {
		if (!vendeurDao.existsById(id) || !id.equals(Vendeur.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}

		Vendeur = vendeurDao.save(Vendeur);

		return Vendeur;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewVendeur.class)
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
