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

import zerogaspi.dao.ICommandeGratuite;
import zerogaspi.model.Commande;
import zerogaspi.model.CommandeGratuite;
import zerogaspi.model.IViews;

@RestController
@RequestMapping("/api/commandegratuite")
public class CommandeGratuiteApiRestController {
	@Autowired
	private ICommandeGratuite commandeGratuiteDao;

	@GetMapping("")
	@JsonView(IViews.IViewCommandeGratuite.class)
	public List<CommandeGratuite> list() {
		List<CommandeGratuite> Commandes = commandeGratuiteDao.findAll();

		return Commandes; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewCommandeGratuite.class)
	public CommandeGratuite find(@PathVariable Long id) {
		Optional<CommandeGratuite> optCommande = commandeGratuiteDao.findById(id);

		if (optCommande.isPresent()) {
			return optCommande.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("")
	@JsonView(IViews.IViewCommandeGratuite.class)
	public CommandeGratuite create(CommandeGratuite Commandegratuite) {	
		Commandegratuite = commandeGratuiteDao.save(Commandegratuite);

		return Commandegratuite;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewCommandeGratuite.class)
	public CommandeGratuite update(@RequestBody CommandeGratuite Commandegratuite, @PathVariable Long id) {
		if (!commandeGratuiteDao.existsById(id) || !id.equals(Commandegratuite.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Commandegratuite = commandeGratuiteDao.save(Commandegratuite);

		return Commandegratuite;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewCommandeGratuite.class)
	public CommandeGratuite partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!commandeGratuiteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final CommandeGratuite CommandeFind = commandeGratuiteDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Commande.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, CommandeFind, value);
		});

		CommandeGratuite CommandeUpdate = commandeGratuiteDao.save(CommandeFind);

		return CommandeUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!commandeGratuiteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		commandeGratuiteDao.deleteById(id);

		if (commandeGratuiteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
