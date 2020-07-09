package eu.ensup.gestionscolairespringboot;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionscolairespringbootApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GestionscolairespringbootApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/gestion"));
        app.run(args);
	}

}
