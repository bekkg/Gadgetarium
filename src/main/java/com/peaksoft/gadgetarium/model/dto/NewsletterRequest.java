package com.peaksoft.gadgetarium.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsletterRequest {
    private String nameSender;
    private String descriptionSender;
    private String startletter;
    private String endletter;
}
