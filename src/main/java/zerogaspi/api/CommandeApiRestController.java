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
import zerogaspi.dao.ICommande;
import zerogaspi.dao.ICommandePayante;
import zerogaspi.model.Commande;
import zerogaspi.model.IViews;
import zerogaspi.model.CommandePayante;

@RestController
@RequestMapping("/api/commande")
public class CommandeApiRestController {
	@Autowired
	private ICommande commandeDao;
	@Autowired
	private ICommandePayante commandePayanteDao;

	@GetMapping("")
	@JsonView(IViews.IViewCommande.class)
	public List<Commande> list() {
		List<Commande> Commandes = commandeDao.findAll();

		return Commandes; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewCommande.class)
	public Commande find(@PathVariable Long id) {
		Optional<Commande> optCommande = commandeDao.findById(id);

		if (optCommande.isPresent()) {
			return optCommande.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewCommande.class)
	public Commande create(Commande Commande) {	
		Commande = commandeDao.save(Commande);

		return Commande;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewCommande.class)
	public Commande update(@RequestBody Commande Commande, @PathVariable Long id) {
		if (!commandeDao.existsById(id) || !id.equals(Commande.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Commande = commandeDao.save(Commande);

		return Commande;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewCommande.class)
	public Commande partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!commandeDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Commande CommandeFind = commandeDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Commande.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, CommandeFind, value);
		});

		Commande CommandeUpdate = commandeDao.save(CommandeFind);

		return CommandeUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!commandeDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		commandeDao.deleteById(id);

		if (commandeDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}

	@GetMapping("/payante/findby/dateEnvoie/desc")
	public List<CommandePayante> findCpByDateEnvoieDesc() {
		List <CommandePayante> commandes = commandePayanteDao.findCpByDateEnvoieDesc();
		if(commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/payante/findby/dateEnvoie/asc")
	public List<CommandePayante> findCpByDateEnvoieAsc() {
		List <CommandePayante> commandes = commandePayanteDao.findCpByDateEnvoieAsc();
		if(commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}
	
	@GetMapping("/payante/findby/datePaiement/desc")
	public List<CommandePayante> findCpByDatePaiementDesc() {
		List <CommandePayante> commandes = commandePayanteDao.findCpByDatePaiementDesc();
		if(commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}
	
	@GetMapping("/payante/findby/datePaiement/asc")
	public List<CommandePayante> findCpByDatePaiementAsc() {
		List <CommandePayante> commandes = commandePayanteDao.findCpByDatePaiementAsc();
		if(commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/payante/findby/dateArrivee/desc")
	public List<CommandePayante> findCpByDateArriveeDesc() {
		List <CommandePayante> commandes = commandePayanteDao.findCpByDateArriveeDesc();
		if(commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}
	
	@GetMapping("/payante/findby/dateArrivee/asc")
	public List<CommandePayante> findCpByDateArriveeAsc() {
		List <CommandePayante> commandes = commandePayanteDao.findCpByDateArriveeAsc();
		if(commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	
}

