package cz.tony.skyinterview.repo;

import cz.tony.skyinterview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Antonin.Karasek
 * @since 2025-07-02
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
