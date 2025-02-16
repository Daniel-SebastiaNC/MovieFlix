package br.com.movieflix.service;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> findAllCategorys(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryMapper::toCategoryResponse).collect(Collectors.toList());
    }

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.toCategory(categoryRequest);
        Category categorySave = categoryRepository.save(category);
        return CategoryMapper.toCategoryResponse(categorySave);
    }

    public CategoryResponse findCategoryById(Long id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.map(CategoryMapper::toCategoryResponse).orElse(null);

    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
