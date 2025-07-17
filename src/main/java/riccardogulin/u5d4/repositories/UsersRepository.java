package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

@Repository // Specializzazione di @Component, però dedicata al mondo dei DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Estendendo JpaRepository ottengo già un sacco di metodi CRUD già pronti tipo save(), findById(), findAll() ecc ecc
	// Eventualmente posso aggiungere ulteriori metodi per fare ulteriori query/operazioni
	// Nelle parentesi angolari dovrò inserire <Classe dell'Entità, Tipo dell'id di quell'entità>
}
