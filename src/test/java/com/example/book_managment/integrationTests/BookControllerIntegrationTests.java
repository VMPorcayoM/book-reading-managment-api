package com.example.book_managment.integrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class BookControllerIntegrationTests {

	@Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
    void testAllBooks() throws Exception {
        mockMvc.perform(post("/graphql")
                .contentType("application/json")
                .content("{\"query\": \"{ allBooks { id title author { id name } } }\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.allBooks").isArray())
                .andExpect(jsonPath("$.data.allBooks[0].author.id").exists());
    }
	@Test
    void testBookById() throws Exception {
        Integer bookId = 1; // Cambia esto al ID que estás utilizando en tus datos de prueba
        mockMvc.perform(post("/graphql")
                .contentType("application/json")
                .content(String.format("{\"query\": \"{ bookById(id: %d) { id title } }\"}", bookId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.bookById.id").value(bookId));
    }
}
