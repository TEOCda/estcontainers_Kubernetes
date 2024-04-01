package ru.netology.containerstask1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContainersTask1ApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @Container
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void testDevApp() {
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity(
                "http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
        assertEquals("Current profile is dev", forEntityDev.getBody());
    }

    @Test
    void testProdApp() {
        ResponseEntity<String> forEntityProd = restTemplate.getForEntity(
                "http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Current profile is prod", forEntityProd.getBody());
    }

}
