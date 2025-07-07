package cz.tony.skyinterview.exception;

/**
 * @author Antonin.Karasek
 * @since 2025-07-07
 */
public class BadRequestException extends RuntimeException {

    private final String message;

    public BadRequestException(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
