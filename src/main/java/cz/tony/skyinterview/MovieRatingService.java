package cz.tony.skyinterview;

import java.util.List;
import java.util.Optional;

import cz.tony.skyinterview.entity.Movie;
import cz.tony.skyinterview.entity.Rating;
import cz.tony.skyinterview.entity.User;
import cz.tony.skyinterview.repo.MovieRepository;
import cz.tony.skyinterview.repo.RatingRepository;
import cz.tony.skyinterview.repo.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author Antonin.Karasek
 * @since 2025-09-17
 */
@Service
public class MovieRatingService {

    public enum MovieSort {
        AVERAGE_RATING_DESC("averageRatingDesc"),
        NO_SORT("noSort");

        private final String sort;

        MovieSort(String sort) {
            this.sort = sort;
        }

        public static MovieSort getBySort(String sort) {
            for (MovieSort movieSort : values()) {
                if (movieSort.sort.equalsIgnoreCase(sort)) {
                    return movieSort;
                }
            }
            return null;
        }
    }

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public MovieRatingService(final MovieRepository movieRepository, final UserRepository userRepository, final RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Movie> getMovies(@NonNull MovieSort sort) {
        if (sort == MovieSort.NO_SORT) {
            return movieRepository.findAll();
        }

        if (sort == MovieSort.AVERAGE_RATING_DESC) {
            return movieRepository.findAllByAverageRatingNotNullOrderByAverageRatingDesc();
        }

        throw new IllegalArgumentException("Unsupported sort: " + sort);
    }

    public Optional<Movie> getMovieById(@NonNull Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Optional<User> getUserByEmail(@NonNull String email) {
        return userRepository.findById(email);
    }

    public void rateMovie(User user, Movie movie, int rating) {
        var existingRating = ratingRepository.findByUserAndMovie(user, movie);
        if (existingRating.isPresent()) {
            var rate = existingRating.get();
            rate.setRating(rating);
            ratingRepository.save(rate);
        } else {
            var rate = new cz.tony.skyinterview.entity.Rating();
            rate.setUser(user);
            rate.setMovie(movie);
            rate.setRating(rating);
            ratingRepository.save(rate);
        }

        updateAverageRating(movie);
    }

    public Optional<Rating> getUserRatingForMovie(User user, Movie movie) {
        return ratingRepository.findByUserAndMovie(user, movie);
    }

    public void deleteRating(User user, Movie movie) {
        var existingRating = ratingRepository.findByUserAndMovie(user, movie);
        existingRating.ifPresent(ratingRepository::delete);

        updateAverageRating(movie);
    }

    // TODO: There certainly is a better way how to do this with a single query
    private void updateAverageRating(final Movie movie) {
        float averageRating = (float) ratingRepository.findByMovieId(movie.getId())
                .stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0f);

        movie.setAverageRating(averageRating);
        movieRepository.save(movie);
    }
}
