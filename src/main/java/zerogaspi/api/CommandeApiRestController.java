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

import zerogaspi.dao.ICommande;
import zerogaspi.dao.ICommandeGratuite;
import zerogaspi.dao.ICommandePayante;
import zerogaspi.model.Commande;
import zerogaspi.model.CommandeGratuite;
import zerogaspi.model.CommandePayante;
import zerogaspi.model.IViews;
import zerogaspi.model.IViews.IViewCommandeGratuiteWithLotAndFacture;

@RestController
@RequestMapping("/commande")
public class CommandeApiRestController {
	@Autowired
	private ICommande commandeDao;
	@Autowired
	private ICommandePayante commandePayanteDao;
	@Autowired
	private ICommandeGratuite commandeGratuiteDao;

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
	public Commande create(@Valid @RequestBody Commande Commande, BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Commande = commandeDao.save(Commande);
		return Commande;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewCommande.class)
	public Commande update(@Valid @RequestBody Commande Commande, @PathVariable Long id, BindingResult result) {
		if (!commandeDao.existsById(id) || !id.equals(Commande.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
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
	@JsonView(IViews.IViewCommandePayanteWithLotAndFacture.class)
	public List<CommandePayante> findCpByDateEnvoieDesc() {
		List<CommandePayante> commandes = commandePayanteDao.findCpByDateEnvoieDesc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/payante/findby/dateEnvoie/asc")
	@JsonView(IViews.IViewCommandePayanteWithLotAndFacture.class)
	public List<CommandePayante> findCpByDateEnvoieAsc() {
		List<CommandePayante> commandes = commandePayanteDao.findCpByDateEnvoieAsc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/payante/findby/datePaiement/desc")
	@JsonView(IViews.IViewCommandePayanteWithLotAndFacture.class)
	public List<CommandePayante> findCpByDatePaiementDesc() {
		List<CommandePayante> commandes = commandePayanteDao.findCpByDatePaiementDesc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/payante/findby/datePaiement/asc")
	@JsonView(IViews.IViewCommandePayanteWithLotAndFacture.class)
	public List<CommandePayante> findCpByDatePaiementAsc() {
		List<CommandePayante> commandes = commandePayanteDao.findCpByDatePaiementAsc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/payante/findby/dateArrivee/desc")
	@JsonView(IViews.IViewCommandePayanteWithLotAndFacture.class)
	public List<CommandePayante> findCpByDateArriveeDesc() {
		List<CommandePayante> commandes = commandePayanteDao.findCpByDateArriveeDesc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/payante/findby/dateArrivee/asc")
	@JsonView(IViews.IViewCommandePayanteWithLotAndFacture.class)
	public List<CommandePayante> findCpByDateArriveeAsc() {
		List<CommandePayante> commandes = commandePayanteDao.findCpByDateEnvoieAsc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/gratuite/findby/dateEnvoie/desc")
	@JsonView(IViewCommandeGratuiteWithLotAndFacture.class)
	public List<CommandeGratuite> findCgByDateEnvoieDesc() {
		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateEnvoieDesc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/gratuite/findby/dateEnvoie/asc")
	@JsonView(IViewCommandeGratuiteWithLotAndFacture.class)
	public List<CommandeGratuite> findCgByDateEnvoieAsc() {
		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateEnvoieAsc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/gratuite/findby/datePaiement/desc")
	@JsonView(IViewCommandeGratuiteWithLotAndFacture.class)
	public List<CommandeGratuite> findCgByDatePaiementDesc() {
		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDatePaiementDesc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/gratuite/findby/datePaiement/asc")
	@JsonView(IViewCommandeGratuiteWithLotAndFacture.class)
	public List<CommandeGratuite> findCgByDatePaiementAsc() {
		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDatePaiementAsc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/gratuite/findby/dateArrivee/desc")
	@JsonView(IViewCommandeGratuiteWithLotAndFacture.class)
	public List<CommandeGratuite> findCgByDateArriveeDesc() {
		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateArriveeDesc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

	@GetMapping("/gratuite/findby/dateArrivee/asc")
	@JsonView(IViewCommandeGratuiteWithLotAndFacture.class)
	public List<CommandeGratuite> findCgByDateArriveeAsc() {
		List<CommandeGratuite> commandes = commandeGratuiteDao.findCgByDateArriveeAsc();
		if (commandes.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
		return commandes;
	}

}
