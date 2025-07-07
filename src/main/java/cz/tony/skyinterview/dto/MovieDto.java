package cz.tony.skyinterview.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import cz.tony.skyinterview.entity.Movie;
import cz.tony.skyinterview.mapper.UserMapper;

/**
 * @author Antonin.Karasek
 * @since 2025-07-03
 */
public class MovieDto {

    private final Long id;
    private final String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Float averageRating;

    private final List<RatingDto> ratings;

    public MovieDto(Long id, String name, Float averageRating, List<RatingDto> ratings) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.ratings = ratings;
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
