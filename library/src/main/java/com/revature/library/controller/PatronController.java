package com.revature.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.library.model.Patron;
import com.revature.library.service.PatronService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/patrons")
@Tag(name = "Patron", description = "Patron management APIs")
public class PatronController {

    private final PatronService pa;

    public PatronController(PatronService pa){
        this.pa=pa;
    }

    @GetMapping
    @Operation(summary = "Get all patrons", description = "Retrieve a list of all patrons")
    @ApiResponse(responseCode = "200", description = "List of patrons")
    public List<Patron> getAllPatrons(){
        return pa.getAllPatrons();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patron by ID", description = "Retrieve a specific patron by their ID")
    @ApiResponse(responseCode = "200", description = "Patron found")
    @ApiResponse(responseCode = "404", description = "Patron not found")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id){
        Optional<Patron> patron = pa.findById(id);
        if(patron.isPresent()){
            return ResponseEntity.ok(patron.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Add a new patron", description = "Create a new patron in the system")
    @ApiResponse(responseCode = "201", description = "Patron created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<Patron> addPatron(@RequestBody @Valid Patron pat){
        Patron saved = pa.addPatron(pat);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patron", description = "Update an existing patron's information")
    @ApiResponse(responseCode = "200", description = "Patron updated successfully")
    @ApiResponse(responseCode = "404", description = "Patron not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patron) {
        return ResponseEntity.ok(pa.updatePatron(id, patron));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patron", description = "Remove a patron from the system")
    @ApiResponse(responseCode = "204", description = "Patron deleted successfully")
    @ApiResponse(responseCode = "404", description = "Patron not found")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        pa.deletePatron(id);
        return ResponseEntity.noContent().build();
    }

}
