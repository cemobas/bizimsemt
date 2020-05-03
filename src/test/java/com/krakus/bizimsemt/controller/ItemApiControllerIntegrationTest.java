package com.krakus.bizimsemt.controller;

import com.krakus.bizimsemt.controller.business.ItemApiController;
import com.krakus.bizimsemt.domain.Item;
import com.krakus.bizimsemt.model.ServiceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemApiControllerIntegrationTest {

	@Autowired
	private ItemApiController itemApiController;
	
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
