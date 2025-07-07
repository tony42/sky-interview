package cz.tony.skyinterview.mapper;

import cz.tony.skyinterview.dto.RatingDto;
import cz.tony.skyinterview.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Antonin.Karasek
 * @since 2025-09-17
 */
@Mapper(uses = UserMapper.class)
public interface RatingMapper {
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    RatingDto ratingToRatingDto(Rating rating);
}
