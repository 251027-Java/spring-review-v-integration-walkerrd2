package com.revature.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.library.exception.BookNotFoundException;
import com.revature.library.exception.LoanNotFoundException;
import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.model.Book;
import com.revature.library.model.Loan;
import com.revature.library.model.Patron;
import com.revature.library.repository.BookRepository;
import com.revature.library.repository.LoanRepository;
import com.revature.library.repository.PatronRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepo, PatronRepository pr, BookRepository br){
        this.loanRepository = loanRepo;
        this.patronRepository = pr;
        this.bookRepository = br;
    }

    public Loan createLoan(Long bookId, Long patronId){
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Patron> patron = patronRepository.findById(patronId);

        if(book.isEmpty()){
            throw new BookNotFoundException("Book id: " +bookId+" not found");
        }
        if(patron.isEmpty()){
            throw new PatronNotFoundException("Patron id: " +patronId+" not found");
        }

        return loanRepository.save(new Loan(book.get(), patron.get()));
    } 

    public Loan returnLoan(Long loanId){
        Optional<Loan> loan = loanRepository.findById(loanId);
        if(loan.isPresent()){
            Loan newLoan = loan.get();
            newLoan.setReturnDate(java.time.LocalDate.now());
            return loanRepository.save(newLoan);
        } else {
            throw new LoanNotFoundException("Loan id: " +loanId+" not found");
        }
    }

    public List<Loan> getActiveLoans(){
        return loanRepository.findAll().stream().toList();
    }

    public List<Loan> getLoansByPatron(Long patronId){
        return loanRepository.findAll().stream().filter(l -> l.getPatron().getId() == patronId).toList();
    }

}
