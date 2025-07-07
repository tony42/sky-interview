package cz.tony.skyinterview.mapper;

import static org.junit.jupiter.api.Assertions.*;

import cz.tony.skyinterview.entity.Rating;
import cz.tony.skyinterview.entity.User;
import org.junit.jupiter.api.Test;

class RatingMapperTest {

    @Test
    void ratingToRatingDto() {
        User user = new User();
        user.setEmail("test@user.com");
        user.setName("Test User");

        Rating rating = new Rating();

        rating.setRating(3);
        rating.setUser(user);

        var ratingDto = RatingMapper.INSTANCE.ratingToRatingDto(rating);

        assertNotNull(ratingDto);
        assertEquals(rating.getRating(), ratingDto.getRating());
        assertNotNull(ratingDto.getUser());
        assertEquals(rating.getUser().getEmail(), ratingDto.getUser().getEmail());
        assertEquals(rating.getUser().getName(), ratingDto.getUser().getName());
    }

}