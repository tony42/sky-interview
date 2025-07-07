package cz.tony.skyinterview;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Optional;

import cz.tony.skyinterview.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieRatingControllerTest {

    private static final String TEST_USER_EMAIL = "testUser@sky.com";

    private final MovieRatingController movieRatingController;
    private final MovieRatingService movieRatingService;

    private final Principal authenticatedUser;

    MovieRatingControllerTest() {

        authenticatedUser = mock(Principal.class);

        movieRatingService = mock(MovieRatingService.class);
        movieRatingController = new MovieRatingController(movieRatingService);
    }

    @BeforeEach
    void setUp() {
        when(movieRatingService.getMovieById(any())).thenReturn(Optional.empty());
        when(movieRatingService.getUserByEmail(any())).thenReturn(Optional.empty());
        when(authenticatedUser.getName()).thenReturn(TEST_USER_EMAIL);
    }

    @Test
    void deleteRating()  {
        assertThrows(NotFoundException.class, () -> {
            movieRatingController.deleteRating(authenticatedUser, 1L);
        });
    }

}