package com.revature.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.library.model.Loan;
import com.revature.library.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Loan", description = "Loan management APIs")
public class LoanController {

    private final LoanService ls;

    public LoanController(LoanService ls) {
        this.ls = ls;
    }

    @PostMapping
    @Operation(summary = "Create a loan", description = "Create a new loan for a book and patron")
    @ApiResponse(responseCode = "201", description = "Loan created successfully")
    @ApiResponse(responseCode = "404", description = "Book or Patron not found")
    @ApiResponse(responseCode = "409", description = "Book not available")
    public ResponseEntity<Loan> createLoan(@RequestBody @Valid Loan loan){
        Loan saved = ls.createLoan(loan.getBook().getId(), loan.getPatron().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}/return")
    @Operation(summary = "Return a loan", description = "Mark a loan as returned")
    @ApiResponse(responseCode = "200", description = "Loan returned successfully")
    @ApiResponse(responseCode = "404", description = "Loan not found")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id){
        Loan returned = ls.returnLoan(id);
        return ResponseEntity.ok(returned);
    }

    @GetMapping("/patron/{patronId}")
    @Operation(summary = "Get loans by patron", description = "Retrieve all loans for a specific patron")
    @ApiResponse(responseCode = "200", description = "List of patron's loans")
    public ResponseEntity<List<Loan>> getloansByPatronId(@PathVariable Long patronId){
        List<Loan> loan = ls.getLoansByPatron(patronId);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/active")
    @Operation(summary = "Get active loans", description = "Retrieve all currently active loans")
    @ApiResponse(responseCode = "200", description = "List of active loans")
    public ResponseEntity<List<Loan>> getActiveLoans(){
        return ResponseEntity.ok(ls.getActiveLoans());
    }
}
