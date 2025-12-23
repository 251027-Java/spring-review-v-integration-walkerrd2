package com.revature.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.library.model.Book;
import com.revature.library.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo){
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepo.findById(id);
    }

    public Book addBook(Book book){
        return bookRepo.save(book);
    }

    public Book checkoutBook(Long bookId){
        Optional<Book> book = bookRepo.findById(bookId);
        if(book.isPresent()){
            Book b = book.get();
            b.setAvailable(false);
            return bookRepo.save(b);
        }
        return null;
    }

    public Book returnBook(Long bookId){
        Optional<Book> book = bookRepo.findById(bookId);
        if(book.isPresent()){
            Book bk = book.get();
            bk.setAvailable(true);
            return bookRepo.save(bk);
        }

        return null;

    }

    public List<Book> searchByTitle(String title) {
        return bookRepo.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Book> searchByAuthor(String author) {
        return bookRepo.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<Book> findAvailableBooks() {
        return bookRepo.findByAvailable(true);
    }
    
    public List<Book> findByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

}
