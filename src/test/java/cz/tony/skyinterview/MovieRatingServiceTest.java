package cz.tony.skyinterview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import cz.tony.skyinterview.entity.Movie;
import cz.tony.skyinterview.entity.Rating;
import cz.tony.skyinterview.entity.User;
import cz.tony.skyinterview.exception.NotFoundException;
import cz.tony.skyinterview.repo.MovieRepository;
import cz.tony.skyinterview.repo.RatingRepository;
import cz.tony.skyinterview.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class MovieRatingServiceTest {

    private static final String TEST_USER_EMAIL = "testUser@sky.com";

    private final MovieRatingService movieRatingService;

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    private final Principal authenticatedUser;

    MovieRatingServiceTest() {
        movieRepository = mock(MovieRepository.class);
        userRepository = mock(UserRepository.class);
        ratingRepository = mock(RatingRepository.class);

        authenticatedUser = mock(Principal.class);

        movieRatingService = new MovieRatingService(movieRepository, userRepository, ratingRepository);
    }

    @BeforeEach
    void setUp() {
        when(authenticatedUser.getName()).thenReturn(TEST_USER_EMAIL);

        var user = new User(TEST_USER_EMAIL, "Test User");
        var movie = new Movie(1L, "Test Movie", List.of(), null);

        when(userRepository.findById(TEST_USER_EMAIL)).thenReturn(Optional.of(user));
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        when(ratingRepository.findByUserAndMovie(user, movie)).thenReturn(Optional.empty());
    }

    @Test
    void rateMovie() {
        movieRatingService.rateMovie(authenticatedUser, 1L, 5);

        // create ArgumentCaptor
        ArgumentCaptor<Rating> argumentCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepository).save(argumentCaptor.capture());
        Rating capturedRating = argumentCaptor.getValue();

        // Verify the captured rating
        assertEquals(TEST_USER_EMAIL, capturedRating.getUser().getEmail());
        assertEquals(1L, capturedRating.getMovie().getId());
        assertEquals(5, capturedRating.getRating());
    }

    @Test
    void deleteRating()  {
        assertThrows(NotFoundException.class, () -> {
            movieRatingService.deleteRating(authenticatedUser, 1L);
        });
    }

}