package com.peaksoft.gadgetarium.model.dto;

import com.peaksoft.gadgetarium.model.enums.Memory;
import com.peaksoft.gadgetarium.model.enums.OperationSystem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class ProductResponse {
    private Long id;
    private String brandName;
    private String article;
    private String rating;
    private int inStock;
    private int amount;
    private  String screen;
    private String color;
    private LocalDate releaseDate;
    private OperationSystem operatingSystem;
    private Memory memory;
    private int simCard;
    private String  warranty;
    private double price;
    private String processor;
    private int weight;
}
