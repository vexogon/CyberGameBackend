package dev.kanto.CyberGame;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class CyberGameApplication {
	public static void main(String[] args) {
		SpringApplication.run(CyberGameApplication.class, args);
	}

}
