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
import org.springframework.web.server.ResponseStatusException;

import zerogaspi.dao.IClient;
import zerogaspi.model.Client;

public class ClientApiRestController {
	@Autowired
	private IClient clientDao;

	@GetMapping("")
	public List<Client> list() {
		List<Client> Clients = clientDao.findAll();

		return Clients; // transforme en JSON via jackson
	}

	@GetMapping("/{id}")
	public Client find(@PathVariable Long id) {
		Optional<Client> optClient = clientDao.findById(id);

		if (optClient.isPresent()) {
			return optClient.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public Client create(Client Client) {
		Client = clientDao.save(Client);

		return Client;
	}

	@PutMapping("/{id}")
	public Client update(@RequestBody Client Client, @PathVariable Long id) {
		if (!clientDao.existsById(id) || !id.equals(Client.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Client = clientDao.save(Client);

		return Client;
	}

	@PatchMapping("/{id}")
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
