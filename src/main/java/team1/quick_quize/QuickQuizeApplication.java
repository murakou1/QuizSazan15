package team1.quick_quize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class QuickQuizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickQuizeApplication.class, args);
	}

}
