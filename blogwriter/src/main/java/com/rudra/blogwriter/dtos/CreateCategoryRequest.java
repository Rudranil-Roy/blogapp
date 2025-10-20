package com.rudra.blogwriter.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
    @NotBlank(message = "Filed should not be empty")
    @Size(min = 2, max = 50, message = "Category name should be between {min} and {max} characters")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Can only contain letters, numbers, spaces and hyphens")
    private String name;
}
