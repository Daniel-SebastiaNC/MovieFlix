package br.com.movieflix.repository;

import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories(List<Category> categories);
}
