package ms.tiendagen15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Tiendagen15Application {

	public static void main(String[] args) {
		SpringApplication.run(Tiendagen15Application.class, args);
	}

}
