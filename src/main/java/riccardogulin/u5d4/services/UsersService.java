package riccardogulin.u5d4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.exceptions.NotFoundException;
import riccardogulin.u5d4.exceptions.ValidationException;
import riccardogulin.u5d4.repositories.UsersRepository;

import java.util.List;

@Service // Specializzazione di @Component
// All'avvio dell'applicazione mi verrà creato un Bean di tipo UsersService
// Un Service è un classe di "servizio" che ci consente di aggiungere ulteriori logiche custom nelle varie interazioni con il db
// Ad esempio, il metodo save della Repository, salva e basta. Se io invece volessi (OBBLIGATORIO in realtà) fare dei controlli aggiuntivi, come
// ad es. verificare se i dati inseriti nel nuovo utente passano dei controlli di validità, oppure se l'indirizzo email non è già stato utilizzato,
// oppure se voglio aggiungere dei log di conferma di avvenuto salvataggio, oppure una gestione delle eccezioni utile ad esempio nella findById, allora
// mi serve creare qualche metodo custom qua nel SERVICE
@Slf4j
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	public void saveUser(User newUser) {
		// Nelle save solitamente è opportuno effettuare tutta una serie di controlli prima di salvare effettivamente
		// Durante una save è spesso anche necessario aggiungere ulteriori campi "Server-Generated"

		// 1. Controllo che l'email fornita non sia già usata da qualcuno
		// TODO: effettua controllo email
		if (usersRepository.existsByEmail(newUser.getEmail())) throw new ValidationException("Email " + newUser.getEmail() + " già in uso");

		// 2. Effettuo ulteriori controlli di validazione dei campi forniti
		if (newUser.getName().length() < 2) throw new ValidationException("Il nome non può essere più corto di 2 caratteri!");
		//....

		// 3. Aggiungo ulteriori dati "Server-Generated"
		newUser.setProfileImg("https://picsum.photos/200/300");

		// 4. Salvo l'utente tramite Repository
		usersRepository.save(newUser);

		// 5. Log di avvenuto salvataggio
		log.info("L'utente " + newUser.getName() + " è stato salvato correttamente!");
	}

	public List<User> findAll() {
		return usersRepository.findAll();
	}


	public User findById(long userId) {
		/*Optional<User> foundOrNot = usersRepository.findById(userId);
		if (foundOrNot.isPresent()) return foundOrNot.get();
		else throw new NotFoundException(userId);*/
		return usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId)); // Alternativa più compatta al codice di sopra
	}

	public void findByIdAndUpdate(long userId, User updatedUser) {
		// 1. Cerco l'utente tramite id
		User found = this.findById(userId);

		// 2. Aggiorno i campi di tale utente
		found.setName(updatedUser.getName());
		found.setSurname(updatedUser.getSurname());
		found.setEmail(updatedUser.getEmail());
		found.setAge(updatedUser.getAge());

		// 3. Risalvo l'utente modificato
		usersRepository.save(found);

		// 4. Log di avvenuta modifica
		log.info("L'utente con id " + userId + " è stato modificato con successo!");
	}

	public void findByIdAndDelete(long userId) {
		User found = this.findById(userId);
		usersRepository.delete(found);
		log.info("L'utente con id " + userId + " è stato cancellato correttamente!");
	}

	public void saveMany(List<User> newUsers) {
		for (User user : newUsers) {
			try {
				this.saveUser(user);
			} catch (ValidationException ex) {
				log.error(ex.getMessage());
			}
		}
	}

	public List<User> filterBySurname(String surname) {
		return this.usersRepository.findBySurname(surname);
	}

	public List<User> filterByNameAndSurname(String name, String surname) {
		return this.usersRepository.findByNameAndSurname(name, surname);
	}

	public List<User> filterByNameStartsWith(String partialName) {
		return this.usersRepository.findByNameStartingWithIgnoreCase(partialName);
	}

	public List<User> filterByNamesList(List<String> names) {
		return this.usersRepository.findByNameIn(names);
	}
}
