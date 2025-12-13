package com.revature.library;

import com.revature.library.model.Book;
import com.revature.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * BookControllerTest - TODO: Complete the integration tests
 */
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        bookRepository.save(new Book("Test Book", "Test Author", "123-456"));
    }

    @Test
    void getAllBooks_ShouldReturnBooks() throws Exception {
        // TODO: Complete this test
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getBookById_WhenExists_ShouldReturnBook() throws Exception {
        // TODO: Complete this test
    }

    @Test
    void getBookById_WhenNotExists_ShouldReturn404() throws Exception {
        // TODO: Complete this test
        mockMvc.perform(get("/api/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBook_WithValidData_ShouldReturn201() throws Exception {
        // TODO: Complete this test
        String bookJson = "{\"title\":\"New Book\",\"author\":\"New Author\",\"isbn\":\"789-012\"}";

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isCreated());
    }

    @Test
    void createBook_WithInvalidData_ShouldReturn400() throws Exception {
        // TODO: Complete this test
    }

    @Test
    void checkoutBook_WhenAvailable_ShouldSucceed() throws Exception {
        // TODO: Complete this test
    }

    @Test
    void checkoutBook_WhenNotAvailable_ShouldReturn409() throws Exception {
        // TODO: Complete this test
    }
}
