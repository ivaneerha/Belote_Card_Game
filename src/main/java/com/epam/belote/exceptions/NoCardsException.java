package com.epam.belote.exceptions;

public class NoCardsException extends Exception {

    public NoCardsException() {
    }

    public NoCardsException(String message) {
        super(message);
    }

    public NoCardsException(Throwable cause) {
        super(cause);
    }

    public NoCardsException(String message, Throwable cause) {
        super(message, cause);
    }
}
