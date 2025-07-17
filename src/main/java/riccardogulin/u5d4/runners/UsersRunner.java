package riccardogulin.u5d4.runners;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.exceptions.NotFoundException;
import riccardogulin.u5d4.services.BlogsService;
import riccardogulin.u5d4.services.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class UsersRunner implements CommandLineRunner {
	/*
	@Autowired
	private UsersRepository usersRepository;
	MAI UTILIZZARE DIRETTAMENTE LE REPOSITORIES!
	Devo invece utilizzare i SERVICE che effettuano dei controlli prima di salvare ad es.
*/
	@Autowired
	private UsersService usersService;

	@Autowired
	private BlogsService blogsService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(Locale.ITALY);

		User newUser = new User(faker.lordOfTheRings().character(), faker.name().lastName(), "ajeje@gmail.com", faker.random().nextInt(100));
		usersService.saveUser(newUser);

		usersService.findAll().forEach(System.out::println);


		// usersService.findByIdAndDelete(3);

		usersService.findByIdAndUpdate(1, new User("Ajeje", "Brazorf", "ajeje@gmail.com", 20));

		List<User> newUsers = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			User newUser2 = new User(faker.lordOfTheRings().character(), faker.name().lastName(), faker.internet().emailAddress(), faker.random().nextInt(100));
			newUsers.add(newUser2);
		}

		// usersService.saveMany(newUsers);
		try {
			System.out.println(usersService.findById(11));
		} catch (NotFoundException ex) {
			log.error(ex.getMessage());
		}

		try {
			// blogsService.saveNewBlog("Spring", "Ha i Beans", 1);
		} catch (NotFoundException ex) {
			log.error(ex.getMessage());
		}

		System.out.println(" FIND BY SURNAME ");
		usersService.filterBySurname("Brazorf").forEach(System.out::println);

		System.out.println(" FIND BY NAME AND SURNAME ");
		usersService.filterByNameAndSurname("Sauron", "Brazorf").forEach(System.out::println);

		System.out.println(" FIND BY PARTIAL NAME ");
		usersService.filterByNameStartsWith("a").forEach(System.out::println);

		System.out.println(" FIND BY LIST OF NAMES ");
		List<String> names = new ArrayList<>(List.of("Ajeje", "Sauron", "Aragorn"));
		usersService.filterByNamesList(names).forEach(System.out::println);
	}
}
