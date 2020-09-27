package com.udacity.pricing;

import com.udacity.pricing.api.PricingController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
public class PricingControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInvalidVehicleId() throws Exception {
        mockMvc.perform(get("/services/price")
                .param("vehicleId", String.valueOf(31)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testValidVehicleId() throws Exception {
        mockMvc.perform(get("/services/price")
                .param("vehicleId", String.valueOf(1)))
                .andExpect(status().is2xxSuccessful());
    }
}
