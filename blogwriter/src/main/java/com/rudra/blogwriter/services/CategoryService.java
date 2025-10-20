package com.rudra.blogwriter.services;

import com.rudra.blogwriter.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> listCategories();
    CategoryEntity createCategory(CategoryEntity categoryEntity);
}
