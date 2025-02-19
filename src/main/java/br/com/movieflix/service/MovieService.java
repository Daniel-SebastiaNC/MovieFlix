package br.com.movieflix.service;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.repository.CategoryRepository;
import br.com.movieflix.repository.MovieRepository;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    public MovieResponse save(MovieRequest movieRequest){
        //Mapper Movie
        Movie movie = MovieMapper.toMovie(movieRequest);

        //Setter Categories and Streamings
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findstreamings(movie.getStreamings()));

        //Save
        Movie saveMovie = movieRepository.save(movie);

        //Mapper Response
        return MovieMapper.toMovieResponse(saveMovie);
    }

    public List<MovieResponse> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public MovieResponse getMovieById(Long id){
        return movieRepository.findById(id).map(MovieMapper::toMovieResponse).orElse(null);
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoryFound = new ArrayList<>();

        categories.forEach(category -> categoryRepository.findById(category.getId()).ifPresent(categoryFound::add));

        return categoryFound;
    }

    private List<Streaming> findstreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();

        streamings.forEach(streaming -> streamingRepository.findById(streaming.getId()).ifPresent(streamingsFound::add));

        return streamingsFound;
    }
}
