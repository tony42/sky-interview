package cz.tony.skyinterview;

import java.security.Principal;
import java.util.List;

import cz.tony.skyinterview.entity.Movie;
import cz.tony.skyinterview.entity.Rating;
import cz.tony.skyinterview.repo.MovieRepository;

import cz.tony.skyinterview.repo.RatingRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Antonin.Karasek
 * @since 2025-06-30
 */
@RestController
@RequestMapping(value = "/movie", produces = "application/json")
public class MovieRatingService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public MovieRatingService(MovieRepository movieRepository, final RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }


    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + movieId));
    }

    @PutMapping("/{id}/rate")
    public Movie rateMovie(Principal authenticatedUser,
                           @PathVariable("id") Long movieId,
                           @RequestParam("rating") int rating) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + movieId));

        String email = authenticatedUser.getName();

        ratingRepository.findByUserEmailAndMovieId(email, movieId)
                .ifPresentOrElse(existingRating -> {
                    // Update existing rating
                    existingRating.setRating(rating);
                    ratingRepository.save(existingRating);
                }, () -> {
                    // Create new rating if it doesn't exist
                    Rating newRating = new Rating();
                    newRating.setUserEmail(email);
                    newRating.setMovieId(movieId);
                    newRating.setRating(rating);
                    ratingRepository.save(newRating);
                });

        return movie; // TODO: return movie Dto
    }
}
