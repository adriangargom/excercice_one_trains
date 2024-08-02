package org.example.exceptions;

public class InvalidInputFormatException extends Exception {

    public InvalidInputFormatException() {
        super();
    }

    public InvalidInputFormatException(String message) {
        super(message);
    }

    public InvalidInputFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputFormatException(Throwable cause) {
        super(cause);
    }

    public InvalidInputFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
