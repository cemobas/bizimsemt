package com.krakus.bizimsemt.controller;

import com.krakus.bizimsemt.controller.business.ItemApiController;
import com.krakus.bizimsemt.domain.Item;
import com.krakus.bizimsemt.model.ServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class ItemApiControllerIntegrationTest {

	@Autowired
	private ItemApiController itemApiController;

	@DisplayName("given a new item" +
			"when we add item to the list" +
			"then it is added to list")
	@Test
	public void testAddItemHappyPath() {
		// given
		Item item = new Item("Marlboro", "2", "paket");

		// when
		ResponseEntity<ServiceResponse<Item>> responseEntity = itemApiController.addItem(item);
		Item data = responseEntity.getBody().getData();
		
		// then
		assertThat(data, is(equalTo(item)));
		assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}

	@DisplayName("given a null item" +
			"when we add item to the list" +
			"then it is not added to list")
	@Test
	public void testAddItemNegativePath() {
		// given
		Item item = null;

		// when
		ResponseEntity<ServiceResponse<Item>> responseEntity = itemApiController.addItem(item);

		// then
		assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.BAD_REQUEST)));
		
	}
}
