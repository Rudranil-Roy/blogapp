package com.rudra.blogwriter.controllers;

import com.rudra.blogwriter.dtos.CategoryDto;
import com.rudra.blogwriter.dtos.CreateCategoryRequest;
import com.rudra.blogwriter.entities.CategoryEntity;
import com.rudra.blogwriter.mappers.CategoryMapper;
import com.rudra.blogwriter.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){
        List<CategoryEntity> categories= categoryService.listCategories();
        return ResponseEntity.ok(categories.stream().map(categoryMapper::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
    ){
        CategoryEntity savedCategory= categoryService.createCategory(categoryMapper.toEntity(createCategoryRequest));
        return new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
