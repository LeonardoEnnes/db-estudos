package com.example.ex1.Repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ex1.Entities.Book;
import com.example.ex1.Entities.Person;

@SpringBootTest
public class BookRepositoryTest {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Test
    void testIfCanSaveBook() {
        Person autor = new Person("Autor Teste");
        personRepository.save(autor);

        Book book = new Book("Test Book", autor);
        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());
    }

    @Test
    void testFindAll() {
        List<Book> books = bookRepository.findAll();
        assertNotNull(books);
        assertTrue(books.size() > 0);
    }

    @Test
    void testFindBookById() {
        Book book = bookRepository.findBookById(1);
        assertNotNull(book);
    }

    // do more tests if you want learn more
}
