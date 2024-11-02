package valentin.magneto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MagnetoApplication {

	public static void main(String[] args) {

		SpringApplication.run(MagnetoApplication.class, args);
		System.out.println("La aplicacion se inicio correctamente");
	}

}
