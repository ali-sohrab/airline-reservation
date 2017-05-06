package edu.sjsu.cmpe275.lab2;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Sohrab-Ali
 *
 */
@SpringBootApplication
public class Application {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
