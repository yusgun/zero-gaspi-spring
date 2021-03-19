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

import zerogaspi.dao.ICommandePayante;
import zerogaspi.model.Commande;
import zerogaspi.model.CommandePayante;
import zerogaspi.model.IViews;

@RestController
@RequestMapping("/api/commandepayante")
public class CommandePayanteApiRestController {
	@Autowired
	private ICommandePayante CommandePayanteDao;

	@GetMapping("")
	@JsonView(IViews.IViewCommandePayante.class)
	public List<CommandePayante> list() {
		List<CommandePayante> Commandes = CommandePayanteDao.findAll();

		return Commandes; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewCommandePayanteWithLot.class)
	public CommandePayante find(@PathVariable Long id) {
		Optional<CommandePayante> optCommande = CommandePayanteDao.findById(id);

		if (optCommande.isPresent()) {
			return optCommande.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	@JsonView(IViews.IViewCommandePayanteWithLot.class)
	public CommandePayante create(CommandePayante CommandePayante) {	
		CommandePayante = CommandePayanteDao.save(CommandePayante);

		return CommandePayante;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewCommandePayanteWithLot.class)
	public CommandePayante update(@RequestBody CommandePayante CommandePayante, @PathVariable Long id) {
		if (!CommandePayanteDao.existsById(id) || !id.equals(CommandePayante.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		CommandePayante = CommandePayanteDao.save(CommandePayante);

		return CommandePayante;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewCommandePayanteWithLot.class)
	public CommandePayante partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!CommandePayanteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final CommandePayante CommandeFind = CommandePayanteDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Commande.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, CommandeFind, value);
		});

		CommandePayante CommandeUpdate = CommandePayanteDao.save(CommandeFind);

		return CommandeUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!CommandePayanteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		CommandePayanteDao.deleteById(id);

		if (CommandePayanteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
