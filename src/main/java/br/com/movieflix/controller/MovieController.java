package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping("/add")
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(movieRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(service.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id){
        MovieResponse movieResponse = service.getMovieById(id);
        if (movieResponse != null) {
            return ResponseEntity.ok(movieResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie with id " + id + " not founded");

    }

    @GetMapping("/search")
    public ResponseEntity<?> getByMovieByCategorys(@RequestParam Long category){
        List<MovieResponse> byMovieByCategorys = service.getByMovieByCategorys(category);
        if (byMovieByCategorys != null) {
            return ResponseEntity.ok(byMovieByCategorys);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie with category " + category + " not founded");
    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest){
        MovieResponse movieResponse = service.updateMovie(id, movieRequest);
        if (movieResponse != null) {
            return ResponseEntity.ok(movieResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie with id " + id + " not founded");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long id){
        MovieResponse movieById = service.getMovieById(id);
        if (movieById != null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie with id " + id + " not founded");
    }
}
