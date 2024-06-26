package dev.kei.productservice;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
class ProductServiceApplicationTests {

	// @Container
	// static final MongoDBContainer mongoDBContainer = new
	// MongoDBContainer("mongo");

	// @Autowired
	// WebTestClient webTestClient;

	// @Autowired
	// ProductRepository productRepository;

	// static {
	// mongoDBContainer.start();
	// }

	// @DynamicPropertySource
	// static void setProperties(DynamicPropertyRegistry registry) {
	// registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	// }

	// @BeforeEach
	// public void setUp() {
	// productRepository.deleteAll();

	// Product product1 = new Product();
	// product1.setName("Product 1");
	// product1.setDescription("Product 1 description");
	// product1.setPrice(10.10);

	// Product product2 = new Product();
	// product1.setName("Product 2");
	// product1.setDescription("Product 2 description");
	// product1.setPrice(20.10);

	// Product product3 = new Product();
	// product1.setName("Product 3");
	// product1.setDescription("Product 3 description");
	// product1.setPrice(30.10);

	// productRepository.save(product1);
	// productRepository.save(product2);
	// productRepository.save(product3);
	// }

	// @AfterEach
	// public void tearDown() {
	// productRepository.deleteAll();
	// }

	// @Test
	// @Order(1)
	// void getAllProducts() {
	// webTestClient.get().uri("/api/v1/products").exchange().expectStatus().isOk().expectHeader()
	// .contentType(MediaType.APPLICATION_JSON).expectBodyList(ProductResponse.class).hasSize(3);
	// }

	// @Test
	// @Order(2)
	// void createProduct() {
	// ProductRequest productRequest = new ProductRequest(
	// "Test Product",
	// "This is a test product",
	// 10.0);
	// webTestClient.post().uri("/api/v1/products").bodyValue(productRequest).exchange().expectHeader()
	// .contentType(MediaType.APPLICATION_JSON).expectStatus().isCreated().expectBody(ProductResponse.class)
	// .consumeWith(res -> Assertions.assertNotNull(res.getResponseBody().getId()))
	// .consumeWith(res -> Assertions.assertEquals(res.getResponseBody().getName(),
	// productRequest.getName()));
	// }

}
