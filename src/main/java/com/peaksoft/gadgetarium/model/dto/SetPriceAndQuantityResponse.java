package com.peaksoft.gadgetarium.model.dto;

import com.peaksoft.gadgetarium.model.enums.Memory;
import com.peaksoft.gadgetarium.model.enums.OperationMemory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class SetPriceAndQuantityResponse {
    private String brandName;
    private String color;
    @Enumerated(EnumType.STRING)
    private Memory memory;
    @Enumerated(EnumType.STRING)
    private OperationMemory operationMemory;
    private int simCard;
    private LocalDate releaseDate;
    private int totalPrice;
    private int quantity;
}
