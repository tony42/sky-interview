package cz.tony.skyinterview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Antonin.Karasek
 * @since 2025-07-02
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Float averageRating;

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

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(final Float averageRating) {
        this.averageRating = averageRating;
    }

}
