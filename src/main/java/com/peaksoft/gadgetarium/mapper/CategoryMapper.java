package com.peaksoft.gadgetarium.mapper;

import com.peaksoft.gadgetarium.model.dto.CategoryRequest;
import com.peaksoft.gadgetarium.model.dto.CategoryResponse;
import com.peaksoft.gadgetarium.model.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category mapToEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setSubCategory(categoryRequest.getSubCategory());
        return category;
    }

    public CategoryResponse mapToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .subCategory(category.getSubCategory())
                .build();
    }
}
