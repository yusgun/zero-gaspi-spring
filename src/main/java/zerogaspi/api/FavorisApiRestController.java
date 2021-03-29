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

import zerogaspi.dao.IClient;
import zerogaspi.dao.IEntreprise;
import zerogaspi.dao.IFavoris;
import zerogaspi.model.Client;
import zerogaspi.model.Entreprise;
import zerogaspi.model.IViews;
import zerogaspi.model.ListeFavori;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/favoris")
public class FavorisApiRestController {

	@Autowired
	private IFavoris favorisDao;
	@Autowired
	private IClient clientDao;
	@Autowired
	private IEntreprise entrepriseDao;


	@GetMapping("")
	@JsonView(IViews.IViewListeFavoriWithClientAndEntreprise.class)
	public List<ListeFavori> list() {
		List<ListeFavori> Favoris = favorisDao.findAll();

		return Favoris;
	}

	@GetMapping("/{id}")
	//@JsonView(IViews.IViewListeFavoriWithClientAndEntreprise.class)
	public ListeFavori find(@PathVariable Long id) {
		Optional<ListeFavori> optFavori = favorisDao.findById(id);

		if (optFavori.isPresent()) {
			return optFavori.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	//@JsonView(IViews.IViewListeFavoriWithClientAndEntreprise.class)
	public ListeFavori create(@Valid @RequestBody ListeFavori ListeFavori, BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		ListeFavori.setClient(clientDao.findById(11L).get());
		ListeFavori = favorisDao.save(ListeFavori);

		return ListeFavori;
	}

	@PutMapping("/{id}")
	//@JsonView(IViews.IViewListeFavori.class)
	public ListeFavori update(@Valid @RequestBody ListeFavori ListeFavori, @PathVariable Long id, BindingResult result) {
		if (!favorisDao.existsById(id) || !id.equals(ListeFavori.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		ListeFavori = favorisDao.save(ListeFavori);
		return ListeFavori;
	}
	
	@PatchMapping("/{id}")
	//@JsonView(IViews.IViewListeFavori.class)
	public ListeFavori partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!favorisDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final ListeFavori FavoriFind = favorisDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(ListeFavori.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, FavoriFind, value);
		});

		ListeFavori FavoriUpdate = favorisDao.save(FavoriFind);

		return FavoriUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!favorisDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		favorisDao.deleteById(id);

		if (favorisDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
	
	@DeleteMapping("/client/{idClient}/entreprise/{idEntreprise}")
	public void deleteByClientEntreprise(@PathVariable Long idClient, @PathVariable Long idEntreprise) {
		if (!clientDao.existsById(idClient)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (!entrepriseDao.existsById(idEntreprise)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		List<ListeFavori> list = favorisDao.findAll();
		for (ListeFavori fav : list) {
			if(fav.getClient().getId() == idClient && fav.getEntreprise().getId() == idEntreprise) {
				favorisDao.deleteById(fav.getId());
			}
		}
	}

	@GetMapping("/findby/client/{id}")
	//@JsonView(IViews.IViewListeFavoriWithClientAndEntreprise.class)
	public List<Object[]> findByClient(@PathVariable Long id) {
		if (!clientDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		List<Object[]> entreprises = favorisDao.findByClient(id);
		if (entreprises.size() == 0){
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return entreprises;
	}
}
