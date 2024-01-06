//package com.peaksoft.gadgetarium.service;
//
//import com.peaksoft.gadgetarium.mapper.BasketMapper;
//import com.peaksoft.gadgetarium.repository.BasketRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//@RequiredArgsConstructor
//public class BasketService {
//    private final BasketMapper basketMapper;
//    private final BasketRepository repository;
//
//    public GenreResponse save(GenreRequest request) {
//        Genre genre = genreMapper.mapToEntity(request);
//        repository.save(genre);
//        return genreMapper.mapToResponse(genre);
//    }
//
//    public GenreResponse findById(Long id) {
//        Genre genre = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException(" Genre not found: " + id));
//        return genreMapper.mapToGenreResponse(genre);
//    }
//
//    public List<GenreResponse> findAll() {
//        return repository.findAll()
//                .stream()
//                .map(genreMapper::mapToGenreResponse).toList();
//    }
//
//    public void delete(Long genreId) {
//        repository.deleteById(genreId);
//    }
//
//    public GenreResponse update(Long genreId, GenreRequest request) {
//        Genre olGenre = repository.findById(genreId)
//                .orElseThrow(() -> new RuntimeException(" Genre not found by id: " + genreId));
//        olGenre.setName(request.getName());
//        olGenre.setDescription(request.getDescription());
//        repository.save(olGenre);
//        return genreMapper.mapToGenreResponse(olGenre);
//    }
//
//    public List<GenreResponse> searchAndPaginationGenre(String text, int page, int size) {
//        String name = text == null ? "" : text;
//        Pageable pageable = PageRequest.of(page - 1, size);
//        List<Genre> genres = repository.searchAndPaginationGenre(name.toUpperCase(), pageable);
//        List<GenreResponse> responses = new ArrayList<>();
//        for (Genre genre : genres) {
//            responses.add(genreMapper.mapToGenreResponse(genre));
//        }
//        return responses;
//    }
//}
