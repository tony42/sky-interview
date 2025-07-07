package cz.tony.skyinterview.dto;

import java.util.List;

/**
 * @author Antonin.Karasek
 * @since 2025-07-03
 */
public class MovieDto {

    private Long id;
    private String name;
    private float averageRating;

    private List<Rating> ratings;

    public MovieDto(final Long id, final String name, final float averageRating, final List<Rating> ratings) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.ratings = ratings;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(final float averageRating) {
        this.averageRating = averageRating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Rating> ratings) {
        this.ratings = ratings;
    }

    public static class Rating {

        private String userEmail;
        private int rating;

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(final String userEmail) {
            this.userEmail = userEmail;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(final int rating) {
            this.rating = rating;
        }
    }
}
