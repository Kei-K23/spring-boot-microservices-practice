package dev.kei.inventoryservice;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
class InventoryServiceApplicationTests {

	// static final MySQLContainer mySQLContainer;

	// @Autowired
	// WebTestClient webTestClient;

	// @Autowired
	// private InventoryRepository inventoryRepository;

	// static {
	// mySQLContainer = new MySQLContainer<>("mysql:latest");
	// mySQLContainer.start();
	// }

	// @DynamicPropertySource
	// static void registerDynamicProperties(DynamicPropertyRegistry registry) {
	// registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
	// registry.add("spring.datasource.username", mySQLContainer::getUsername);
	// registry.add("spring.datasource.password", mySQLContainer::getPassword);
	// registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
	// }

	// @BeforeEach
	// public void beforeEach() {
	// Inventory inventory1 = new Inventory();
	// inventory1.setSkuCode("product-1");
	// inventory1.setQuantity(10);

	// Inventory inventory2 = new Inventory();
	// inventory2.setSkuCode("product-2");
	// inventory2.setQuantity(2);

	// Inventory inventory3 = new Inventory();
	// inventory3.setSkuCode("product-3");
	// inventory3.setQuantity(40);

	// inventoryRepository.save(inventory1);
	// inventoryRepository.save(inventory2);
	// inventoryRepository.save(inventory3);
	// }

	// @AfterEach
	// public void afterEach() {
	// inventoryRepository.deleteAll();
	// }

	// @Test
	// void testIsInStockController() {
	// webTestClient.get().uri("/api/v1/inventory?skuCode=product-1,product-2,product-3")
	// .exchange()
	// .expectStatus().isOk();
	// }

}
