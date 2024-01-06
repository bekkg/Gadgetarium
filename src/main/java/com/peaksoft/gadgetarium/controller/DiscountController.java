//package com.peaksoft.gadgetarium.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequestMapping("api/discount")
//@RequiredArgsConstructor
//public class DiscountController {
//    private final ApplicationService applicationService;
//
//    @PostMapping("/add")
//    public ResponseEntity<ApplicationResponse> add(@RequestBody ApplicationRequest request) {
//        ApplicationResponse response = applicationService.save(request);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/find-by/{id}")
//    public ApplicationResponse findById(@PathVariable Long id) {
//        return applicationService.findById(id);
//    }
//
//    @GetMapping("/get-all")
//    public List<ApplicationResponse> findAll(Principal principal) {
//        return applicationService.findAll(principal);
//    }
//
//    @PutMapping("/update/{id}")
//    public ApplicationResponse update(@PathVariable("id") Long id, @RequestBody ApplicationRequest request) {
//        return applicationService.update(id, request);
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        applicationService.delete(id);
//        return " Application with id: " + id + " successfully deleted";
//    }
//
//    @GetMapping("/search-genre")
//    public List<GenreResponse> searchAndPaginationGenre(@RequestParam(name = "text", required = false) String text,
//                                                        @RequestParam int page,
//                                                        @RequestParam int size) {
//        return genreService.searchAndPaginationGenre(text, page, size);
//    }
//}
