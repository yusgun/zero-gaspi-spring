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

import zerogaspi.dao.IClient;
import zerogaspi.model.Client;
import zerogaspi.model.IViews;

@RestController
@RequestMapping("/client")
public class ClientApiRestController {
	@Autowired
	private IClient clientDao;

	@GetMapping("")
	@JsonView(IViews.IViewClient.class)
	public List<Client> list() {
		List<Client> Clients = clientDao.findAll();

		return Clients; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewClient.class)
	public Client find(@PathVariable Long id) {
		Optional<Client> optClient = clientDao.findById(id);

		if (optClient.isPresent()) {
			return optClient.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewClient.class)
	public Client create(@Valid @RequestBody Client Client, BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Client = clientDao.save(Client);
		return Client;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewClient.class)
	public Client update(@Valid @RequestBody Client Client, @PathVariable Long id, BindingResult result) {
		if (!clientDao.existsById(id) || !id.equals(Client.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		if (result.hasErrors()) {
			StringJoiner errors = new StringJoiner("\n");
			for (ObjectError oe : result.getAllErrors()) {
				errors.add(oe.getDefaultMessage());
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		Client = clientDao.save(Client);
		return Client;
	}

	@PatchMapping("/{id}")
	@JsonView(IViews.IViewClient.class)
	public Client partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!clientDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		final Client ClientFind = clientDao.findById(id).get();

		updates.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Client.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, ClientFind, value);
		});

		Client ClientUpdate = clientDao.save(ClientFind);

		return ClientUpdate;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!clientDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		clientDao.deleteById(id);

		if (clientDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
