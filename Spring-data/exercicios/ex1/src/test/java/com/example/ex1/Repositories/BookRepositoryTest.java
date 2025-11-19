package com.example.ex1.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ex1.Entities.Book;
import com.example.ex1.Entities.Person;

@DataJpaTest
public class BookRepositoryTest {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("Testa se é possível salvar um livro")
    void testSaveAndFindBook() {
        // arrange
        Person autor = personRepository.save(new Person("Autor Teste"));
        Book book = new Book("Livro Teste", autor);

        // act
        Book savedBook = bookRepository.save(book); 
        Book foundBook = bookRepository.findBookById(savedBook.getId());

        // verifica resultados
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getTitle()).isEqualTo("Livro Teste");
        assertThat(foundBook.getPerson().getName()).isEqualTo("Autor Teste");
    }

    @Test
    @DisplayName("Testa se é possível buscar todos os livros")
    void testFindAll() {
        // ARRANGE
        Person autor = personRepository.save(new Person("Autor Lista"));
        bookRepository.save(new Book("Livro A", autor));
        bookRepository.save(new Book("Livro B", autor));

        // ACT
        List<Book> books = bookRepository.findAll();

        // ASSERT
        assertThat(books).isNotNull();
        assertThat(books).hasSize(2); 
    }

    @Test
    @DisplayName("Testa se é possível buscar um livro por ID")
    void testFindBookById() {
        // ARRANGE
        Person autor = personRepository.save(new Person("Autor ID Teste"));
        Book bookToSave = new Book("Livro ID Teste", autor);

        // ACT
        Book savedBook = bookRepository.save(bookToSave); 
        Book found = bookRepository.findBookById(savedBook.getId());

        // ASSERT
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Livro ID Teste");
    }

    @Test
    @DisplayName("Testa se retorna null ao buscar um livro por ID inexistente")
    void testFindByIdNotFound() {
        Book book = bookRepository.findBookById(9999L);
        assertThat(book).isNull();
    }
}