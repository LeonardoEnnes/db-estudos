package com.example.ex1.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ex1.Entities.Book;
import com.example.ex1.Entities.Person;
import com.example.ex1.Repositories.BookRepository;
import com.example.ex1.Repositories.PersonRepository;

import jakarta.annotation.PostConstruct;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }
    
    // insert books and persons into database for testing the requests in the controller
    @PostConstruct
    public void init() {
        Person person = new Person("Default Person") ;
        Person person2 = new Person("Second Person") ;

        personRepository.save(person);
        personRepository.save(person2);

        bookRepository.save(new Book("The Great Gatsby", person));
        bookRepository.save(new Book("To Kill a Mockingbird", person2));
        bookRepository.save(new Book("1984", person));
        bookRepository.save(new Book("Pride and Prejudice", person2));
        bookRepository.save(new Book("The Catcher in the Rye", person));
        bookRepository.save(new Book("The Hobbit", person2));
        bookRepository.save(new Book("Fahrenheit 451",  person));
        bookRepository.save(new Book("Moby Dick", person2));
        bookRepository.save(new Book("War and Peace", person));
        bookRepository.save(new Book("The Odyssey", person2));
    }

    //
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book registerBook(String title) {
        Book book = new Book(title, null);
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findBookById(id);
    }
}
