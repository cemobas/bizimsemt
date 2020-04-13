package com.krakus.bizimsemt;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.domain.Order;
import com.krakus.bizimsemt.repository.BuyerRepository;
import com.krakus.bizimsemt.repository.OrderRepository;
import com.krakus.bizimsemt.repository.SellerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BizimsemtApplication implements CommandLineRunner {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	BuyerRepository buyerRepository;

	@Autowired
	SellerRepository sellerRepository;

	public static void main(String[] args) {
		SpringApplication.run(BizimsemtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		orderRepository.findAll().forEach(current -> {
			current.setLastModified(Calendar.getInstance().getTime());
			orderRepository.save(current);

			Buyer buyer = current.getBuyer();
			buyer.setBirthDate(LocalDate.of(1984,11,9).atStartOfDay());
			buyerRepository.save(buyer);
		});
		orderRepository.findAll().forEach(order -> {
			System.out.println("Order " + order.getId() + " created at: " + new ObjectId(order.getId()).getDate() + " last modified at: " + order.getLastModified());
		});
	}
}
