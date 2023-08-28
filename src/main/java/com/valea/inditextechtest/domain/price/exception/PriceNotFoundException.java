package com.valea.inditextechtest.domain.price.exception;

/**
 * Exception thrown when queries doesn't return results.
 */
public class PriceNotFoundException extends Exception {

    public PriceNotFoundException(String message) {
        super(message);
    }
}
