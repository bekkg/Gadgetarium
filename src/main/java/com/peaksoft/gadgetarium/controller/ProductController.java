package com.peaksoft.gadgetarium.controller;

import com.peaksoft.gadgetarium.model.dto.*;
import com.peaksoft.gadgetarium.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping("/save-product")
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest){
        ProductResponse response = productService.create(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/setDescription/{id}")
    public SetDescriptionResponse setDescription(@PathVariable("id")Long id, @RequestBody SetDescription setDescription){
        return productService.setDescription(id, setDescription);
    }

    @PatchMapping("/setPriceAndQuantity/{id}")
    public SetPriceAndQuantityResponse setPriceAndQuantity(@PathVariable("id")Long id, @RequestBody SetPriceAndQuantity setPriceAndQuantity){
        return productService.setPriceAndQuantity(id, setPriceAndQuantity);
    }

   @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")Long id){
        productService.delete(id);
        return "Delete product with id:" + id +" successfully delete";
   }

   @GetMapping("/get-allProduct")
    public List<ProductResponse> getAllProduct(){
        return productService.getAll();
   }

   @GetMapping("/search-product/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.getProductById(id);
   }

   @PutMapping("/compare-oroduct/{id}") // этот метод сравнивает продуктa.
    public List<ProductResponse> compare_product(@PathVariable("id") Long id, Principal principal){
        return  productService.compare_product(id,principal);
   }
    @GetMapping("/search-product-by-filter")
    public List<ProductResponse> searchAndPaginationProduct(@RequestParam String category,
                                                                @RequestParam double min_price,
                                                                @RequestParam double max_price,@RequestParam String color,
                                                                @RequestParam String operationMemory,
                                                                @RequestParam String operationSystem, @RequestParam int page,
                                                                @RequestParam int size,Principal principal) {
        return productService.searchAndPaginationProduct(category, min_price, max_price, color, operationMemory,
                operationSystem, page, size,principal);
    }
    @DeleteMapping("/delete-products")
    public String deleteProductInCompare(Principal principal){
        productService.deleteProductInCompare(principal);
        return " Successful delete! ";
    }
    @GetMapping("/get-all-products-by-category")
    public List<ProductResponse> getAllProductByCategory(@RequestParam("category") String category,@RequestParam(value = "difference",required = false)boolean difference, Principal principal){
        return productService.getProductByCategory(category,difference,principal);
    }

}
