package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*@GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }*/

    @GetMapping("/all")
    public List<CategoryResponse> getAllCategorys(){
        return categoryService.findAllCategorys();
    }

    @PostMapping("/add")
    public CategoryResponse saveCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.saveCategory(categoryRequest);
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryService.findCategoryById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
    }
}
