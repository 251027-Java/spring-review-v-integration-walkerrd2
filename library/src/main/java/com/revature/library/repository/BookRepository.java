package com.revature.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    public List<Book> findByAuthorContaining(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByAvailable(boolean available);
    List<Book> findByIsbn(String isbn);

}
