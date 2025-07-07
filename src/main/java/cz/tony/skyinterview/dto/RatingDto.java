package cz.tony.skyinterview.dto;

/**
 * @author Antonin.Karasek
 * @since 2025-07-07
 */
public class RatingDto {

    private final UserDto user;
    private final int rating;

    public RatingDto(UserDto user, int rating) {
        this.user = user;
        this.rating = rating;
    }

    public UserDto getUser() {
        return user;
    }

    public int getRating() {
        return rating;
    }

}
