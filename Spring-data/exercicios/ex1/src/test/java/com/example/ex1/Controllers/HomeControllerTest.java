package com.example.ex1.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ex1.Services.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BookService bookService;

    @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/1"))
               .andExpect(status().isOk());
    }

    @Test
    void testListBooks() throws Exception {
        mockMvc.perform(get("/listBooks"))
               .andExpect(status().isOk());
    }

    @Test
    void testRegisterBook() throws Exception {
        mockMvc.perform(post("/registerBook")
                .content("New Book") // Alterado de .param() para .content()
                .contentType("text/plain"))
                .andExpect(status().isOk());

    }
}
