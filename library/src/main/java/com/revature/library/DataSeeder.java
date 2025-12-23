package com.revature.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.library.model.Book;
import com.revature.library.repository.BookRepository;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(BookRepository bookRepo){
        return args -> {
            bookRepo.save(new Book("Clean code", "Robert Martin", "123456789"));
            bookRepo.save(new Book("The Pragmatic Programmer", "David Thomas", "0987654321"));
            bookRepo.save(new Book("Design Patterns", "Gang of Four", "1122334455"));
            bookRepo.save(new Book("Effective Java", "Joshua Bloch", "554432167"));
            bookRepo.save(new Book("Spring in Action", "Craig Walls", "6767676767"));
        };
    }



}
