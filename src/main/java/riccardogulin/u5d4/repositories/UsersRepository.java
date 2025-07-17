package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

import java.util.List;

@Repository // Specializzazione di @Component, però dedicata al mondo dei DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Estendendo JpaRepository ottengo già un sacco di metodi CRUD già pronti tipo save(), findById(), findAll() ecc ecc
	// Eventualmente posso aggiungere ulteriori metodi per fare ulteriori query/operazioni
	// Nelle parentesi angolari dovrò inserire <Classe dell'Entità, Tipo dell'id di quell'entità>

	// ************************************** DERIVED QUERIES ******************************************************
	List<User> findBySurname(String surname); // SELECT * FROM users WHERE surname = :surname

	List<User> findByNameAndSurname(String name, String surname); // SELECT * FROM users WHERE name = :name AND surname = :surname

	List<User> findByNameStartingWithIgnoreCase(String partialName); // SELECT * FROM users WHERE name ILIKE CONCAT(:partialName, "%")

	List<User> findByAgeLessThan(int age);

	List<User> findByNameIn(List<String> names);

	List<User> findByEmailIsNull();

	boolean existsByEmail(String email);

	// Link alla documentazione ufficiale sulle Derived Queries
	// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

	// *************************************** JPQL QUERIES **********************************
	@Query("SELECT u FROM User u WHERE u.age >= 18")
	List<User> filterSoloMaggiorenni();

	@Query("SELECT u FROM User u WHERE u.age >= :age")
	List<User> filterByAgeGreaterThan(int age);

	// ************************************ NATIVE QUERIES *************************************
	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE age >= 18")
	List<User> filterSoloMaggiorenniNative();
}
