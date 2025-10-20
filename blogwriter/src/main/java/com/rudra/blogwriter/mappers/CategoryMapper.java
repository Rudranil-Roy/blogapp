package com.rudra.blogwriter.mappers;

import com.rudra.blogwriter.dtos.CategoryDto;
import com.rudra.blogwriter.dtos.CreateCategoryRequest;
import com.rudra.blogwriter.entities.CategoryEntity;
import com.rudra.blogwriter.entities.PostEntity;
import com.rudra.blogwriter.entities.PostStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default Long calculatePostCount(List<PostEntity> posts) {
        if(posts==null || posts.isEmpty())
            return 0L;

        return posts.stream()
                .filter(post -> PostStatusEnum.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
