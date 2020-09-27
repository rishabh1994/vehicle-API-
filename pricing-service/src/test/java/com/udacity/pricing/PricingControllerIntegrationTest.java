package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testValidPrice() {
        ResponseEntity<Price> priceEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/services/price?vehicleId=1", Price.class);
        Assert.assertEquals("USD", priceEntity.getBody().getCurrency());
        Assert.assertEquals(Long.valueOf(1), priceEntity.getBody().getVehicleId());
    }

    @Test
    public void testInvalidPrice() {
        ResponseEntity<Price> priceEntity = this.testRestTemplate.getForEntity("http://localhost:" + port + "/services/price?vehicleId=31", Price.class);
        Assert.assertEquals(priceEntity.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotEquals(Long.valueOf(31), priceEntity.getBody().getVehicleId());
    }
}
