package com.revature.library;

import com.revature.library.model.Book;
import com.revature.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class BookControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        bookRepository.deleteAll();
        bookRepository.save(new Book("Test Book", "Test Author", "123-456"));
    }

    @Test
    void getAllBooks_ShouldReturnBooks() throws Exception {
        // TODO: Complete this test
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("Test Author"));
    }

    @Test
    void getBookById_WhenExists_ShouldReturnBook() throws Exception {
        // TODO: Complete this test
        Book saved = bookRepository.findAll().get(0);
        mockMvc.perform(get("/api/books/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.isbn").value("123-456"));
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
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("New Author"))
                .andExpect(jsonPath("$.isbn").value("789-012"))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    void createBook_WithInvalidData_ShouldReturn400() throws Exception {
        // TODO: Complete this test
        String invalidBookJson = "{\"author\":\"Author Only\",\"isbn\":\"999-888\"}";

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidBookJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void checkoutBook_WhenAvailable_ShouldSucceed() throws Exception {
        // TODO: Complete this test
        Book saved = bookRepository.findAll().get(0);
        mockMvc.perform(put("/api/books/" + saved.getId() + "/checkout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void checkoutBook_WhenNotAvailable_ShouldReturn409() throws Exception {
        // TODO: Complete this test
        Book saved = bookRepository.findAll().get(0);
        saved.setAvailable(false);
        bookRepository.save(saved);
        mockMvc.perform(put("/api/books/" + saved.getId() + "/checkout"))
                .andExpect(status().isConflict());
    }
}
