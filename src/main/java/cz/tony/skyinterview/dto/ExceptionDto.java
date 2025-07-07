package cz.tony.skyinterview.dto;

/**
 * @author Antonin.Karasek
 * @since 2025-07-07
 */
public class ExceptionDto {

    private String message;
    private String details;

    public ExceptionDto() {
    }

    public ExceptionDto(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
