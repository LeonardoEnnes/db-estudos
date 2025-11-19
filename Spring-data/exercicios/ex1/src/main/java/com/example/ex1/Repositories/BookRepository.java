package com.example.ex1.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ex1.Entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();
    Book findBookById(long id);
}
