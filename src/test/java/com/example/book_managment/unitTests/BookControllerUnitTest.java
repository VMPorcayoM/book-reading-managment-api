package com.example.book_managment.unitTests;

import com.example.book_managment.controller.BookController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import com.example.book_managment.model.Book;
// import com.example.book_managment.repository.BookRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import java.util.ArrayList;
// import java.util.List;
// import static org.mockito.BDDMockito.given;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerUnitTest {

    // @Autowired
    // private MockMvc mockMvc;

    // @MockBean
    // private BookRepository bookRepository;

    // private Book book;

    // @BeforeEach
    // public void setup(){
    //     MockitoAnnotations.openMocks(this);
    //     book = new Book();
    //     book.setId(1L);
    //     book.setTitle("John");
    // }

    // @Test
    // @Order(1)
    // public void getBooksTest() throws Exception{
    //     // precondition
    //     List<Book> booksList = new ArrayList<>();
    //     booksList.add(book);
    //     given(bookRepository.findAll()).willReturn(booksList);

    //     // action
    //     ResultActions response = mockMvc.perform(post("/graphql")
    //             .contentType("application/json")
    //             .content("{ \"query\": \"{ allBooks { id title } }\" }"));

    //     // verify the output
    //     response.andExpect(status().isOk())
    //             .andDo(print())
    //             .andExpect(jsonPath("$.data.allBooks").isArray());
                
    // }

    // @Test
    // @Order(2)
    // public void getBookByIdTest() throws Exception{
    //     // precondition
    //     given(bookRepository.findById(1L)).willReturn(java.util.Optional.of(book));

    //     // action
    //     ResultActions response = mockMvc.perform(post("/graphql")
    //             .contentType("application/json")
    //             .content("{ \"query\": \"{ bookById(id: 1) { id title } }\" }"));

    //     // verify the output
    //     response.andExpect(status().isOk())
    //             .andDo(print())
    //             .andExpect(jsonPath("$.data.allBooks[0].title").value(  "John"))
    //             .andExpect(jsonPath("$.data.allBooks[0].id").value(book.getId()));
    // }
}
