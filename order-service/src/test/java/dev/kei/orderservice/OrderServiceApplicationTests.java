package dev.kei.orderservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import dev.kei.orderservice.dto.OrderItemsListDto;
import dev.kei.orderservice.dto.OrderRequest;
import dev.kei.orderservice.dto.OrderSuccessResponse;
import dev.kei.orderservice.repository.OrderRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
class OrderServiceApplicationTests {

	@Container
	static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql");

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	WebTestClient webTestClient;

	static {
		mySQLContainer.start();
	}

	@DynamicPropertySource
	static void registerDynamicProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mySQLContainer::getUsername);
		registry.add("spring.datasource.password", mySQLContainer::getPassword);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
	}

	@AfterEach
	public void tearDown() {
		orderRepository.deleteAll();
	}

	@Test
	void createOrder() {
		OrderRequest orderRequest = new OrderRequest();
		OrderItemsListDto orderItemsListDto = new OrderItemsListDto();
		orderItemsListDto.setPrice(10.10f);
		orderItemsListDto.setQuantity(1);
		orderItemsListDto.setSkuCode("Product-1");
		orderItemsListDto.setId(1212l);

		List<OrderItemsListDto> orderItemsList = new ArrayList<>();
		orderItemsList.add(orderItemsListDto);

		orderRequest.setOrderItemsListsDto(orderItemsList);

		webTestClient.post().uri("/api/v1/order").bodyValue(orderItemsList).exchange().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectStatus().isCreated()
				.expectBody(OrderSuccessResponse.class);
	}

}
