package com.revature.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.library.dto.StatsResponse;
import com.revature.library.repository.BookRepository;
import com.revature.library.repository.LoanRepository;
import com.revature.library.repository.PatronRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/stats")
@Tag(name = "Statistics", description = "Library statistics APIs")
public class StatsController {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final LoanRepository loanRepository;
    
    public StatsController(BookRepository bookRepository, PatronRepository patronRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.loanRepository = loanRepository;
    }
    
    @GetMapping
    @Operation(summary = "Get library statistics", description = "Retrieve overall library statistics including book counts, patron counts, and active loans")
    @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully")
    public ResponseEntity<StatsResponse> getStatistics() {
        long totalBooks = bookRepository.count();
        long availableBooks = bookRepository.findByAvailable(true).size();
        long totalPatrons = patronRepository.count();
        long activeLoans = loanRepository.findByReturnDateIsNull().size();
        
        StatsResponse stats = new StatsResponse(totalBooks, availableBooks, totalPatrons, activeLoans);
        return ResponseEntity.ok(stats);
    }
}
