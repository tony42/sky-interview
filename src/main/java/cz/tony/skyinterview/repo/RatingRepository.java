package cz.tony.skyinterview.repo;

import java.util.List;
import java.util.Optional;

import cz.tony.skyinterview.entity.Movie;
import cz.tony.skyinterview.entity.Rating;
import cz.tony.skyinterview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Antonin.Karasek
 * @since 2025-07-03
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUserAndMovie(User user, Movie movie);

    List<Rating> findByMovieId(Long movieId);

}
