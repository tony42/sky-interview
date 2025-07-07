package cz.tony.skyinterview.dto;

/**
 * @author Antonin.Karasek
 * @since 2025-07-07
 */
public class RatingDto {

    private String userEmail;
    private int rating;

    public RatingDto(String userEmail, int rating) {
        this.userEmail = userEmail;
        this.rating = rating;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getRating() {
        return rating;
    }

}
