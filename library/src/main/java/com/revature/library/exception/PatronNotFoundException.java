package com.revature.library.exception;

public class PatronNotFoundException extends RuntimeException{

    public PatronNotFoundException(){
        super();
    }

    public PatronNotFoundException(String message){
        super(message);
    }

    public PatronNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public PatronNotFoundException(Long id) {
        super("Patron not found with id: " + id);
    }

}
