package com.peaksoft.gadgetarium.service;

import com.peaksoft.gadgetarium.mapper.CategoryMapper;
import com.peaksoft.gadgetarium.model.dto.CategoryRequest;
import com.peaksoft.gadgetarium.model.dto.CategoryResponse;
import com.peaksoft.gadgetarium.model.entities.Category;
import com.peaksoft.gadgetarium.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository repository;

    public CategoryResponse save(CategoryRequest request) {
        Category category = categoryMapper.mapToEntity(request);
        repository.save(category);
        return categoryMapper.mapToResponse(category);
    }

    public CategoryResponse findById(Long id) {
        Category category= repository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Genre not found: " + id));
        return categoryMapper.mapToResponse(category);
    }

    public List<CategoryResponse> findAll(Principal principal) {
        return repository.findAll()
                .stream()
                .map(categoryMapper::mapToResponse).toList();
    }

    public void delete(Long genreId) {
        repository.deleteById(genreId);
    }

    public CategoryResponse update(Long genreId, CategoryRequest request) {
        Category category = repository.findById(genreId)
                .orElseThrow(() -> new RuntimeException(" Genre not found by id: " + genreId));
        category.setName(request.getName());
        category.setSubCategory(request.getSubCategory());
        repository.save(category);
        return categoryMapper.mapToResponse(category);
    }

//    public List<CategoryResponse> searchAndPaginationGenre(String text, int page, int size) {
//        String name = text == null ? "" : text;
//        Pageable pageable = PageRequest.of(page - 1, size);
//        List<Category> categories = repository.searchAndPaginationGenre(name.toUpperCase(), pageable);
//        List<CategoryResponse> responses = new ArrayList<>();
//        for (Category genre : categories) {
//            responses.add(categoryMapper.mapToResponse(genre));
//        }
//        return responses;
//    }
}
