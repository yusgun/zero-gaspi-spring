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
import zerogaspi.dao.IEntreprise;
import zerogaspi.dao.ILot;
import zerogaspi.model.IViews;
import zerogaspi.model.Lot;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/lot")
public class LotApiRestController {

	@Autowired
	private ILot lotDao;
	
	@Autowired
	private IEntreprise entrepriseDao;

	@GetMapping("")
	@JsonView(IViews.IViewLotWithEntreprise.class)
	public List<Lot> list() {
		List<Lot> lots = lotDao.findAll();
		return lots;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewLotWithEntreprise.class)
	public Lot find(@PathVariable Long id) {
		Optional<Lot> optLot = lotDao.findById(id);

		if (optLot.isPresent()) {
			return optLot.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	@JsonView(IViews.IViewLot.class)
	public Lot create(@Valid @RequestBody Lot lot, BindingResult result) {	
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		lot = lotDao.save(lot);

		return lot;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewLot.class)
	public Lot update(@Valid @RequestBody Lot lot, @PathVariable Long id, BindingResult result) {
		if (!lotDao.existsById(id) || !id.equals(lot.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		lot = lotDao.save(lot);

		return lot;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewLot.class)
	public Lot partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!lotDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Lot lotFind = lotDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Lot.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, Lot.class, value);
		});

		Lot lotUpdate = lotDao.save(lotFind);

		return lotUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!lotDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		lotDao.deleteById(id);

		if (lotDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
	
	@GetMapping("/entreprise/{id}")
	@JsonView(IViews.IViewLotWithEntreprise.class)
	public List<Lot> findByEntreprise(@PathVariable Long id) {
		if(entrepriseDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		List<Lot> lots = lotDao.findByEntreprise(id);
		if(lots.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return lots;
	}
}
