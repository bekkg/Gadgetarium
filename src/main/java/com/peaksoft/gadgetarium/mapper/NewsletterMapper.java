package com.peaksoft.gadgetarium.mapper;


import com.peaksoft.gadgetarium.model.dto.NewsletterRequest;
import com.peaksoft.gadgetarium.model.dto.NewsletterResponse;
import com.peaksoft.gadgetarium.model.entities.Newsletter;
import org.springframework.stereotype.Component;

@Component
public class NewsletterMapper {
    public Newsletter mapToEntity(NewsletterRequest request) {
        Newsletter newsletter = new Newsletter();
        newsletter.setNameSender(request.getNameSender());
        newsletter.setDescriptionSender(request.getDescriptionSender());
        newsletter.setStartletter(request.getStartletter());
        newsletter.setEndletter(request.getEndletter());
        return newsletter;
    }

    public NewsletterResponse mapToResponse(Newsletter newsletter) {
        return NewsletterResponse
                .builder()
                .id(newsletter.getId())
                .nameSender(newsletter.getNameSender())
                .descriptionSender(newsletter.getDescriptionSender())
                .startletter(newsletter.getStartletter())
                .endletter(newsletter.getEndletter())
                .build();
    }
}
