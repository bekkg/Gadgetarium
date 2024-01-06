//package com.peaksoft.gadgetarium.service;
//
//import com.peaksoft.gadgetarium.mapper.DeliveryMapper;
//import com.peaksoft.gadgetarium.repository.DeliveryRepository;
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
//public class DeliveryService {
//    private final DeliveryMapper deliveryMapper;
//    private final DeliveryRepository repository;
//
//    public GenreResponse save(GenreRequest request) {
//        Genre genre = deliveryMapper.mapToEntity(request);
//        repository.save(genre);
//        return deliveryMapper.mapToResponse(genre);
//    }
//
//    public GenreResponse findById(Long id) {
//        Genre genre = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException(" Genre not found: " + id));
//        return deliveryMapper.mapToGenreResponse(genre);
//    }
//
//    public List<GenreResponse> findAll() {
//        return repository.findAll()
//                .stream()
//                .map(deliveryMapper::mapToGenreResponse).toList();
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
//        return deliveryMapper.mapToGenreResponse(olGenre);
//    }
//
//    public List<GenreResponse> searchAndPaginationGenre(String text, int page, int size) {
//        String name = text == null ? "" : text;
//        Pageable pageable = PageRequest.of(page - 1, size);
//        List<Genre> genres = repository.searchAndPaginationGenre(name.toUpperCase(), pageable);
//        List<GenreResponse> responses = new ArrayList<>();
//        for (Genre genre : genres) {
//            responses.add(deliveryMapper.mapToGenreResponse(genre));
//        }
//        return responses;
//    }
//}
