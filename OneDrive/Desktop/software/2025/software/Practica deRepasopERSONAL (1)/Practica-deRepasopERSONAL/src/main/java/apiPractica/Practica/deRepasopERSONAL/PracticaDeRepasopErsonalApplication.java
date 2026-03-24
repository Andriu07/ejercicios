package apiPractica.Practica.deRepasopERSONAL;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticaDeRepasopErsonalApplication {

	public static void main(String[] args) {
		//Cargar variables .env al sistema
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		dotenv.entries().forEach(entry -> System.setProperty(
				entry.getKey(), entry.getValue()));

		SpringApplication.run(PracticaDeRepasopErsonalApplication.class, args);
	}

}
