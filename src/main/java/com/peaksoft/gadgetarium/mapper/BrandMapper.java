package com.peaksoft.gadgetarium.mapper;

import com.peaksoft.gadgetarium.model.dto.BrandRequest;
import com.peaksoft.gadgetarium.model.dto.BrandResponse;
import com.peaksoft.gadgetarium.model.entities.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand mapToEntity(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setName(brandRequest.getName());
        brand.setImage(brandRequest.getImage());
        return brand;
    }

    public BrandResponse mapToResponse(Brand brand) {
        return BrandResponse.builder()
                .name(brand.getName())
                .image(brand.getImage())
                .build();
    }
}
