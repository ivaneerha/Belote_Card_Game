package com.epam.belote.exceptions;

public class NotEnoughPlayersException extends Exception {

    public NotEnoughPlayersException() {
    }

    public NotEnoughPlayersException(String message) {
        super(message);
    }

    public NotEnoughPlayersException(Throwable cause) {
        super(cause);
    }

    public NotEnoughPlayersException(String message, Throwable cause) {
        super(message, cause);
    }
}