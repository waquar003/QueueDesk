package com.waquar;

public class InvalidTicketDataException extends Exception {

    private final int lineNumber;

    public InvalidTicketDataException(String message, int lineNumber) {
        super(message);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() { return this.lineNumber; }
}
