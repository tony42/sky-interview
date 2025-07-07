package cz.tony.skyinterview;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import cz.tony.skyinterview.dto.MovieDto;
import cz.tony.skyinterview.entity.Movie;
import cz.tony.skyinterview.entity.Rating;
import cz.tony.skyinterview.entity.User;
import cz.tony.skyinterview.exception.BadRequestException;
import cz.tony.skyinterview.exception.NotFoundException;
import cz.tony.skyinterview.mapper.MovieMapper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/movies", produces = "application/json")
public class MovieRatingController {

    private final MovieRatingService movieRatingService;

    public MovieRatingController(final MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @GetMapping("")
    public List<MovieDto> getMovies(@RequestParam(value = "sortBy", required = false) String sort) {
        if (ObjectUtils.isEmpty(sort)) {
            return movieRatingService.getMovies(MovieRatingService.MovieSort.NO_SORT).stream()
                    .map(MovieMapper.INSTANCE::movieToMovieDto).toList();
        }


        MovieRatingService.MovieSort movieSort = MovieRatingService.MovieSort.getBySort(sort);

        if (movieSort == null) {
            throw new BadRequestException("Invalid sort parameter: " + sort);
        }

        return movieRatingService.getMovies(movieSort).stream()
                .map(MovieMapper.INSTANCE::movieToMovieDto).toList();
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable("id") Long movieId) {
        return movieRatingService.getMovieById(movieId)
                .map(MovieMapper.INSTANCE::movieToMovieDto)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + movieId));
    }

    @PutMapping("/{id}/rate")
    public MovieDto rateMovie(Principal authenticatedUser,
                           @PathVariable("id") Long movieId,
                           @RequestParam("rating") int rating) {
        Movie movie = movieRatingService.getMovieById(movieId)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + movieId));

        String email = authenticatedUser.getName();

        User user = movieRatingService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));

        if (rating < 1 || rating > 5) {
            throw new BadRequestException("Rating must be between 1 and 5");
        }

        movieRatingService.rateMovie(user, movie, rating);

        return MovieMapper.INSTANCE.movieToMovieDto(movie);
    }

    @DeleteMapping("/{id}/rate")
    public MovieDto deleteRating(Principal authenticatedUser,
                                 @PathVariable("id") Long movieId) {
        Movie movie = movieRatingService.getMovieById(movieId)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + movieId));

        String email = authenticatedUser.getName();

        User user = movieRatingService.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));

        Optional<Rating> rating = movieRatingService.getUserRatingForMovie(user, movie);

        if (rating.isEmpty()) {
            throw new NotFoundException("Rating not found for user: " + email + " and movie: " + movieId);
        }

        movieRatingService.deleteRating(user, movie);

        return MovieMapper.INSTANCE.movieToMovieDto(movie);
    }

}
