package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.service.MovieService;
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
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(movieRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(service.getAllMovies());
    }
}
