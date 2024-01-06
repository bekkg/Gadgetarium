package com.peaksoft.gadgetarium.controller;

import com.peaksoft.gadgetarium.model.dto.CategoryRequest;
import com.peaksoft.gadgetarium.model.dto.CategoryResponse;
import com.peaksoft.gadgetarium.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryservice;

    @PostMapping("/add-categories")
    public ResponseEntity<CategoryResponse> add(@RequestBody CategoryRequest request) {
        CategoryResponse response = categoryservice.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/find-by/{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        return categoryservice.findById(id);
    }

    @GetMapping("/get-all")
    public List<CategoryResponse> findAll(Principal principal) {
        return categoryservice.findAll(principal);
    }

        @PutMapping("/update/{id}")
    public CategoryResponse update(@PathVariable("id") Long id, @RequestBody CategoryRequest request) {
        return categoryservice.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryservice.delete(id);
        return " Application with id: " + id + " successfully deleted";
    }

//    @GetMapping("/search-genre")
//    public List<CategoryResponse> searchAndPaginationGenre(@RequestParam(name = "text", required = false) String text,
//                                                           @RequestParam int page,
//                                                           @RequestParam int size) {
//        return categoryservice.searchAndPaginationGenre(text, page, size);
//    }
}
