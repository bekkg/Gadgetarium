//package com.peaksoft.gadgetarium.service;
//
//import com.peaksoft.gadgetarium.mapper.PayCardMapper;
//import com.peaksoft.gadgetarium.repository.PayCardRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PayCardService {
//    private final PayCardMapper payCardMapper;
//    private final PayCardRepository repository;
//
//    public GenreResponse save(GenreRequest request) {
//        Genre genre = payCardMapper.mapToEntity(request);
//        repository.save(genre);
//        return payCardMapper.mapToResponse(genre);
//    }
//
//    public GenreResponse findById(Long id) {
//        Genre genre = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException(" Genre not found: " + id));
//        return payCardMapper.mapToGenreResponse(genre);
//    }
//
//    public List<GenreResponse> findAll() {
//        return repository.findAll()
//                .stream()
//                .map(payCardMapper::mapToGenreResponse).toList();
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
//        return payCardMapper.mapToGenreResponse(olGenre);
//    }
//
//    public List<GenreResponse> searchAndPaginationGenre(String text, int page, int size) {
//        String name = text == null ? "" : text;
//        Pageable pageable = PageRequest.of(page - 1, size);
//        List<Genre> genres = repository.searchAndPaginationGenre(name.toUpperCase(), pageable);
//        List<GenreResponse> responses = new ArrayList<>();
//        for (Genre genre : genres) {
//            responses.add(payCardMapper.mapToGenreResponse(genre));
//        }
//        return responses;
//    }
//}
