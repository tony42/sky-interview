package cz.tony.skyinterview.mapper;

import cz.tony.skyinterview.dto.MovieDto;
import cz.tony.skyinterview.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Antonin.Karasek
 * @since 2025-09-17
 */
@Mapper(uses = RatingMapper.class)
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDto movieToMovieDto(Movie movie);
}
