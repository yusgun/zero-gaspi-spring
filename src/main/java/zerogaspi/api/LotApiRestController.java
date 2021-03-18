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

import zerogaspi.dao.IEntreprise;
import zerogaspi.dao.ILot;
import zerogaspi.model.Entreprise;
import zerogaspi.model.IViews;
import zerogaspi.model.Lot;

@RestController
@RequestMapping("/api/lot")
public class LotApiRestController {

	@Autowired
	private ILot lotDao;
	
	@Autowired
	private IEntreprise entrepriseDao;

	@GetMapping("")
	@JsonView(IViews.IViewLot.class)
	public List<Lot> list() {
		List<Lot> lots = lotDao.findAll();
		return lots;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewLot.class)
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
	public Lot create(Lot lot) {	
		lot = lotDao.save(lot);

		return lot;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewLot.class)
	public Lot update(@RequestBody Lot lot, @PathVariable Long id) {
		if (!lotDao.existsById(id) || !id.equals(lot.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
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
	@JsonView(IViews.IViewLot.class)
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
