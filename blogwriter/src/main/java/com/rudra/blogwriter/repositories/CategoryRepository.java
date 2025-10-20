package com.rudra.blogwriter.repositories;

import com.rudra.blogwriter.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    @Query("SELECT c FROM CategoryEntity c LEFT JOIN FETCH c.posts")
    List<CategoryEntity> findWithPost();

    boolean existsByNameIgnoreCase(String name);
}
