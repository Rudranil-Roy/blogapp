package com.rudra.blogwriter.services;

import com.rudra.blogwriter.entities.CategoryEntity;
import com.rudra.blogwriter.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> listCategories() {
        return categoryRepository.findWithPost();
    }

    @Override
    @Transactional
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        String categoryName = categoryEntity.getName();
        if(categoryRepository.existsByNameIgnoreCase(categoryName)) {
            throw new IllegalArgumentException("Category already exists with name: " + categoryName);
        }
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if(categoryEntity.isPresent()) {
            if(!categoryEntity.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Category already has posts associated with it");
            }
            categoryRepository.deleteById(id);
        }
    }
}
