package com.example.ex1.Services;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.ex1.Entities.Book;
import com.example.ex1.Repositories.BookRepository;
import com.example.ex1.Repositories.PersonRepository;

@SpringBootTest
public class BookServiceTest {

    
    @Mock
    private BookRepository bookRepository;
    
    @Mock 
    private PersonRepository personRepository; // dependencia de construtor
    
    @InjectMocks
    private BookService bookService; // mockito vai injetar os mocks aqui

    @Test
    void testGetAllBooks() {
        Book bookOne = new Book("Book One", null);
        Book bookTwo = new Book("Book Two", null);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(bookOne, bookTwo));

        List<Book> booksList = bookService.getAllBooks();

        assertEquals(2,  booksList.size());
        verify(bookRepository, times(1)).findAll(); // garantir que o metodo foi chamado uma vez
    }

    @Test
    void testGetBookById() {
        Long id = 1L;
        Book expectedBook = new Book("Book One", null);

        when(bookRepository.findBookById(id)).thenReturn(expectedBook);

        Book result = bookService.getBookById(id);

        assertEquals(expectedBook, result);
        verify(bookRepository).findBookById(id);

    }

    @Test
    void testRegisterBook() {
        String title = "book one";
        Book bookToSave = new Book(title, null);

        // simula que o repositório salva e retorna o livro (com ou sem ID)
        when(bookRepository.save(any(Book.class))).thenReturn(bookToSave); 

        Book result = bookService.registerBook(title);

        assertNotNull(result);
        assertEquals(title, result.getTitle());

        // Verifica se o save foi chamado com um objeto Book que tem o título correto
        verify(bookRepository).save(argThat(book -> book.getTitle().equals(title)));
    }
}
