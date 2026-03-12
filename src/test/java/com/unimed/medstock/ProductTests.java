package com.unimed.medstock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void findAll_deveRetornar200() throws Exception {
    mockMvc.perform(get("/api/produtos"))
        .andExpect(status().isOk());
  }

  @Test
  void create_deveCriarProdutoERetornar201() throws Exception {
    String body = """
        {
            "description": "Seringa 10ml",
            "type": "MEDICATION",
            "supplierPrice": 2.50,
            "stockQuantity": 100
        }
        """;

    mockMvc.perform(post("/api/produtos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(body))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.description").value("Seringa 10ml"));
  }
}