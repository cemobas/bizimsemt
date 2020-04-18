package com.krakus.bizimsemt;

import com.krakus.bizimsemt.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BizimsemtApplication implements CommandLineRunner {

	@Autowired
	OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(BizimsemtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		orderRepository.findAll().forEach(order -> {
			System.out.println("Order " + order.getId() + " created at: " + new ObjectId(order.getId()).getDate() + " last modified at: " + order.getLastModified());
		});
	}
}
