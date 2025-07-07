package cz.tony.skyinterview.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import cz.tony.skyinterview.dto.MovieDto;
import cz.tony.skyinterview.entity.Movie;
import cz.tony.skyinterview.entity.Rating;
import cz.tony.skyinterview.entity.User;
import org.junit.jupiter.api.Test;

class MovieMapperTest {

    @Test
    void test()  {
        User user = new User();
        user.setEmail("test@user.com");
        user.setName("Test User");

        Rating rating = new Rating();

        rating.setRating(3);
        rating.setUser(user);


        Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("Test Movie");
        movie.setRatings(List.of(rating));
        movie.setAverageRating(3.0f);


        MovieDto movieDto = MovieMapper.INSTANCE.movieToMovieDto(movie);

        assertNotNull(movieDto);
        assertEquals(movie.getId(), movieDto.getId());
        assertEquals(movie.getName(), movieDto.getName());
        assertEquals(movie.getAverageRating(), movieDto.getAverageRating());
        assertNotNull(movieDto.getRatings());
        assertEquals(1, movieDto.getRatings().size());
        assertEquals(rating.getRating(), movieDto.getRatings().get(0).getRating());
        assertNotNull(movieDto.getRatings().get(0).getUser());
        assertEquals(user.getEmail(), movieDto.getRatings().get(0).getUser().getEmail());
        assertEquals(user.getName(), movieDto.getRatings().get(0).getUser().getName());
    }
}