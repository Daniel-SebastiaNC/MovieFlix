package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAllCategorys(){
        return ResponseEntity.ok(categoryService.findAllCategorys());
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(categoryRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        CategoryResponse categoryById = categoryService.findCategoryById(id);
        if (categoryById != null) {
            return ResponseEntity.ok(categoryById);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id " + id + " not founded");

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        CategoryResponse categoryById = categoryService.findCategoryById(id);
        if (categoryById != null) {
            categoryService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category with id " + id + " was deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id " + id + " not founded");

    }
}
