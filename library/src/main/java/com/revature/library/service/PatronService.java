package com.revature.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.model.Patron;
import com.revature.library.repository.PatronRepository;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    public PatronService(PatronRepository pr){
        this.patronRepository = pr;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll().stream().toList();
    }

    public Optional<Patron> findById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new PatronNotFoundException(id));
        
        patron.setName(patronDetails.getName());
        patron.setEmail(patronDetails.getEmail());
        
        return patronRepository.save(patron);
    }
    
    public void deletePatron(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new PatronNotFoundException(id);
        }
        patronRepository.deleteById(id);
    }

}
