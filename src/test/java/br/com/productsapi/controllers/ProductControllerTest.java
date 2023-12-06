package br.com.productsapi.controllers;

import br.com.productsapi.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product(1L, "Notebook", "DELL", 6000.0, 0.1, 5);
    }

    @Test
    void whenCreateOnePerson_ShouldReturnAProductObject() throws Exception {
        String requestBody = mapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(requestBody)
                        .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenListOfProducts_WhenFindAllProducts_ThenReturnProductsList() throws Exception {
        String requestBody = "{\"id\":1,\"description\":\"Notebook\",\"supplier\":\"DELL\",\"price\":6000.0,\"maxDiscount\":0.1,\"stockQuantity\":5}";

        String expectedResponseBody = "[{\"id\":1,\"description\":\"Notebook\",\"supplier\":\"DELL\",\"price\":6000.0,\"maxDiscount\":0.1,\"stockQuantity\":5}]";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .content(requestBody)
                .contentType("application/json"));


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/products"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    public void givenProductId_WhenFindById_ThenReturnProductObject() throws Exception {
        String requestBody = "{\"id\":1,\"description\":\"Notebook\",\"supplier\":\"DELL\",\"price\":6000.0,\"maxDiscount\":0.1,\"stockQuantity\":5}";

        String expectedResponseBody = "{\"id\":1,\"description\":\"Notebook\",\"supplier\":\"DELL\",\"price\":6000.0,\"maxDiscount\":0.1,\"stockQuantity\":5}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .content(requestBody)
                .contentType("application/json"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/1"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertEquals(expectedResponseBody, responseBody);
    }
}