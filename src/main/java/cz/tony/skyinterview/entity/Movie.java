package cz.tony.skyinterview.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * @author Antonin.Karasek
 * @since 2025-07-02
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;
    private Float averageRating;

    public Movie() {
    }

    public Movie(final Long Id, final String name, final List<Rating> ratings, final Float averageRating) {
        this.id = Id;
        this.name = name;
        this.ratings = ratings;
        this.averageRating = averageRating;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(final Float averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(ratings, movie.ratings)
                && Objects.equals(averageRating, movie.averageRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ratings, averageRating);
    }

}
