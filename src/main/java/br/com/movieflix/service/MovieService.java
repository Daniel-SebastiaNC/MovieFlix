package br.com.movieflix.service;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    public MovieResponse save(MovieRequest movieRequest){
        Movie movie = MovieMapper.toMovie(movieRequest);
        Movie saveMovie = repository.save(movie);
        return MovieMapper.toMovieResponse(saveMovie);
    }

    public List<MovieResponse> getAllMovies(){
        List<Movie> movies = repository.findAll();
        return movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }
}
