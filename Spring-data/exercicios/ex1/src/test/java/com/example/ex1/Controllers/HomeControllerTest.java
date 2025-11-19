package com.example.ex1.Controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ex1.Entities.Book;
import com.example.ex1.Services.BookService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("GET /books/{id} - Deve retornar um livro específico")
    void testGetBookById() throws Exception {
        Book book = new Book("O Hobbit", null);
        when(bookService.getBookById(anyLong())).thenReturn(book);

        mockMvc.perform(get("/books/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("O Hobbit"));
    }

    @Test
    @DisplayName("GET /books/listBooks - Deve retornar lista de livros")
    void testListBooks() throws Exception {
        // Mock: Simulando que o serviço retorna uma lista
        Book b1 = new Book("Livro A", null);
        when(bookService.getAllBooks()).thenReturn(List.of(b1));

        mockMvc.perform(get("/books/listBooks"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].title").value("Livro A")); // Verifica o JSON
    }

    @Test
    @DisplayName("POST /registerBook - Deve registrar e retornar o livro")
    void testRegisterBook() throws Exception {
        Book savedBook = new Book("Novo Livro", null);
        when(bookService.registerBook("Novo Livro")).thenReturn(savedBook);

        mockMvc.perform(post("/books/registerBook")
               .contentType("application/json")
               .content("Novo Livro"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Novo Livro"));

    }
}
