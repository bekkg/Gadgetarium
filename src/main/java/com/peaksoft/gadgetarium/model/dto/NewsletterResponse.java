package com.peaksoft.gadgetarium.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewsletterResponse {
    private Long id;
    private String nameSender;
    private String descriptionSender;
    private String startletter;
    private String endletter;
}
