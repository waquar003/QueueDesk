package com.queuedesk;

import lombok.Getter;

@Getter
public class InvalidTicketDataException extends Exception {
    private final int lineNumber;

    public InvalidTicketDataException(String message, int lineNumber) {
        super(message);
        this.lineNumber = lineNumber;
    }
}
