package br.com.cvc.broker.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuoteHotelNotFoundException extends ResponseStatusException {

    private static final long serialVersionUID = -988901517093043537L;

    public QuoteHotelNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
