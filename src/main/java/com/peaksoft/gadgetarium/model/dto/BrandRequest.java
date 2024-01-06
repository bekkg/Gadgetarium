package com.peaksoft.gadgetarium.model.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BrandRequest {
    String name;
    String image;
}
