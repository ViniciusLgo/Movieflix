package com.movieflix.controller;

import com.movieflix.controller.request.CategoryRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.repository.CategoryRepository;
import com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

    }

    @PostMapping
    public CategoryResponse saveCategory(@RequestBody CategoryRequest request){
        Category newcategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(newcategory);
        return CategoryMapper.toCategoryResponse(savedCategory);

    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id){
        Optional<Category> optCategory = categoryService.findById(id);
        if (optCategory.isPresent()){
            return CategoryMapper.toCategoryResponse(optCategory.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
    }
}
