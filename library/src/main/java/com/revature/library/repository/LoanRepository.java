package com.revature.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.library.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByReturnDateIsNull();
    
    List<Loan> findByPatronId(Long patronId);

}
