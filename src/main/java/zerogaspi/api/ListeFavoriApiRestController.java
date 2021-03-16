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

import zerogaspi.dao.IFavoris;
import zerogaspi.model.ListeFavori;

@RestController
@RequestMapping("/api/listefavori")
public class ListeFavoriApiRestController {
	@Autowired
	private IFavoris listeFavoriDao;

	@GetMapping("")
	public List<ListeFavori> list() {
		List<ListeFavori> ListeFavoris = listeFavoriDao.findAll();

		return ListeFavoris; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	public ListeFavori find(@PathVariable Long id) {
		Optional<ListeFavori> optListeFavori = listeFavoriDao.findById(id);

		if (optListeFavori.isPresent()) {
			return optListeFavori.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	public ListeFavori create(ListeFavori ListeFavori) {	
		ListeFavori = listeFavoriDao.save(ListeFavori);

		return ListeFavori;
	}

	@PutMapping("/{id}")
	public ListeFavori update(@RequestBody ListeFavori ListeFavori, @PathVariable Long id) {
		if (!listeFavoriDao.existsById(id) || !id.equals(ListeFavori.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		ListeFavori = listeFavoriDao.save(ListeFavori);

		return ListeFavori;
	}

	@PatchMapping("/{id}")
	public ListeFavori partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!listeFavoriDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final ListeFavori ListeFavoriFind = listeFavoriDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(ListeFavori.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, ListeFavoriFind, value);
		});

		ListeFavori ListeFavoriUpdate = listeFavoriDao.save(ListeFavoriFind);

		return ListeFavoriUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!listeFavoriDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		listeFavoriDao.deleteById(id);

		if (listeFavoriDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
