package com.peaksoft.gadgetarium.model.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SetDescription {
    private String video;
    private String pdf;
    private String description;
}
