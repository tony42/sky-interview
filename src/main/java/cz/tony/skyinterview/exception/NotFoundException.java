package cz.tony.skyinterview.exception;

/**
 * @author Antonin.Karasek
 * @since 2025-07-07
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }

}
