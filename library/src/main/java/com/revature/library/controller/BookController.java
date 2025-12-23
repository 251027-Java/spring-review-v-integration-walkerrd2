package com.revature.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.library.model.Book;
import com.revature.library.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/books")
@Tag(name = "Book", description = "Book management APIs")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookServ){
        this.bookService = bookServ;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a list of all books in the library")
    @ApiResponse(responseCode = "200", description = "List of books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieve a specific book by its ID")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        Optional<Book> book = bookService.findById(id);
        return book.map(value -> ResponseEntity.ok(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Create a new book in the library")
    @ApiResponse(responseCode = "201", description = "Book created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book){
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}/checkout")
    @Operation(summary = "Checkout a book", description = "Mark a book as checked out")
    @ApiResponse(responseCode = "200", description = "Book checked out successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @ApiResponse(responseCode = "409", description = "Book not available")
    public ResponseEntity<Book> checkoutBook(@PathVariable Long id){
        Book checkedBook = bookService.checkoutBook(id);
        return ResponseEntity.ok(checkedBook);
    }

    @PutMapping("/{id}/return")
    @Operation(summary = "Return a book", description = "Mark a book as returned")
    @ApiResponse(responseCode = "200", description = "Book returned successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Book> returnBook(@PathVariable Long id){
        Book returned = bookService.returnBook(id);
        return ResponseEntity.ok(returned);
    }

    @GetMapping("/search")
    @Operation(summary = "Search books", description = "Search books by title or author")
    @ApiResponse(responseCode = "200", description = "List of matching books")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        
        if (title != null) {
            return ResponseEntity.ok(bookService.searchByTitle(title));
        } else if (author != null) {
            return ResponseEntity.ok(bookService.searchByAuthor(author));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    
    @GetMapping("/available")
    @Operation(summary = "Get available books", description = "Retrieve all books that are currently available")
    @ApiResponse(responseCode = "200", description = "List of available books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.findAvailableBooks());
    }

}
