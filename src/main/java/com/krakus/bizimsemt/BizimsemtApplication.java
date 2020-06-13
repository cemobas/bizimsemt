package com.krakus.bizimsemt;

import com.krakus.bizimsemt.config.BizimsemtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableCaching
public class BizimsemtApplication implements CommandLineRunner {

	@Autowired
	private BizimsemtConfig config;

	public static void main(String[] args) {
		SpringApplication.run(BizimsemtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Mongodb uri: " + config.getProperties().getMongoUri());
	}
}
