package cz.tony.skyinterview.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import cz.tony.skyinterview.entity.Movie;

/**
 * @author Antonin.Karasek
 * @since 2025-07-03
 */
public class MovieDto {

    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Float averageRating;

    private List<RatingDto> ratings;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.averageRating = movie.getAverageRating();
        this.ratings = movie.getRatings().stream()
                .map(rating -> new RatingDto(rating.getUserEmail(), rating.getRating()))
                .toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public List<RatingDto> getRatings() {
        return ratings;
    }
}
