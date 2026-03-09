package csd230.lab1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalNotFoundAdvice {
    @ExceptionHandler (AcousticGuitarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String acousticGuitarNotFoundHandler(AcousticGuitarNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ElectricGuitarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String electricGuitarNotFoundHandler(ElectricGuitarNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cartNotFoundHandler(CartNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MagazineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String magazineNotFoundHandler(MagazineNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DiscMagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String discMagNotFoundHandler(DiscMagNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ticketNotFoundHandler(TicketNotFoundException ex) {
        return ex.getMessage();
    }

}
