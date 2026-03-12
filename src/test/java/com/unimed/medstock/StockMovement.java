package com.unimed.medstock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StockMovementControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void register_deveCriarMovimentoERetornar201() throws Exception {
    String produto = """
        {
            "description": "Seringa 10ml",
            "type": "MEDICATION",
            "supplierPrice": 2.50,
            "stockQuantity": 100
        }
        """;

    String response = mockMvc.perform(post("/api/produtos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(produto))
        .andReturn().getResponse().getContentAsString();

    Long productId = new ObjectMapper().readTree(response).get("id").asLong();

    String movimento = String.format("""
        {
            "productId": %d,
            "movementType": "OUTFLOW",
            "salePrice": 5.00,
            "quantity": 10
        }
        """, productId);

    mockMvc.perform(post("/api/gerenciamento-estoque")
        .contentType(MediaType.APPLICATION_JSON)
        .content(movimento))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.productId").value(productId));
  }
}