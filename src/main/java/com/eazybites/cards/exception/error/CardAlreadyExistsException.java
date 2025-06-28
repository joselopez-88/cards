package com.eazybites.cards.exception.error;

public class CardAlreadyExistsException extends RuntimeException {
    private static final String message = "Card already registered with given mobileNumber %s";
    public CardAlreadyExistsException(String mobileNumber) {
        super(String.format(message, mobileNumber));
    }

}
