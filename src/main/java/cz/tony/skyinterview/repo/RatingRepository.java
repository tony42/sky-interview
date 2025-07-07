package cz.tony.skyinterview.repo;

import java.util.Optional;

import cz.tony.skyinterview.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Antonin.Karasek
 * @since 2025-07-03
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByUserEmailAndMovieId(String userEmail, Long movieId);

}
