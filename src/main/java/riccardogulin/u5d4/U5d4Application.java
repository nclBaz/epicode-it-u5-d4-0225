package riccardogulin.u5d4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j // E' un'annotazione di Lombok
public class U5d4Application {

	public static void main(String[] args) {
		SpringApplication.run(U5d4Application.class, args);
		log.info("CIAO");
	}

}
