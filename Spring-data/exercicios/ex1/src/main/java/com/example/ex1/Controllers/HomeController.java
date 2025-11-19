package com.example.ex1.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ex1.Entities.Book;
import com.example.ex1.Services.BookService;

@RestController
@RequestMapping("/books")
public class HomeController { // trocar para Livro Controller

    private final BookService bookService; // pq deve ser final ??? 

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }
    
    /// add https status code later for educational purposes

    // register books
    @PostMapping("/registerBook")
    public Book registerBook(@RequestBody String title) {

        return bookService.registerBook(title);
    }

    // list books
    @GetMapping("/listBooks")
    public List<Book> listBooks() {
        return bookService.getAllBooks();
    }

    // get book by id
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) { // @PathVariable serve para pegar o id da url
        return bookService.getBookById(id);
    }

}
