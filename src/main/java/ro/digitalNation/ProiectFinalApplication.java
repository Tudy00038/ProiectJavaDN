package ro.digitalNation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "ro.digitalNation")
public class ProiectFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectFinalApplication.class, args);
	}

}
