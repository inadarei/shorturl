package dev.jawn.shorturl.exception;

public class DuplicateShortlinkException extends RuntimeException {
    public DuplicateShortlinkException(String message) {
        super(message);
    }
}
