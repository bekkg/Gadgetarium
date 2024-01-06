package com.peaksoft.gadgetarium.model.dto;

import com.peaksoft.gadgetarium.model.enums.SubCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private String name;
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;
}
