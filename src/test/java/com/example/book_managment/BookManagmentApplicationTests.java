package com.example.book_managment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

import com.example.book_managment.controller.BookController;

@SpringBootTest
class BookManagmentApplicationTests {

	@Autowired
    // private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(new BookController()).build();
	}

	@Test
    void testAllBooks() throws Exception {
        mockMvc.perform(post("/graphql")
                .contentType("application/json")
                .content("{\"query\": \"{ allBooks { id title } }\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.allBooks").isArray());
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
