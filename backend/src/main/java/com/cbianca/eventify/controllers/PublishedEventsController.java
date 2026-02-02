package com.cbianca.eventify.controllers;

import com.cbianca.eventify.dtos.GetPublishedEventDetailsResponseDTO;
import com.cbianca.eventify.dtos.ListEventResponseDTO;
import com.cbianca.eventify.dtos.ListPublishedEventResponseDTO;
import com.cbianca.eventify.entities.events.Event;
import com.cbianca.eventify.mapper.EventMapper;
import com.cbianca.eventify.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/published-events")
@RequiredArgsConstructor
public class PublishedEventsController {

    private final EventService eventService;
    private final EventMapper eventMapper;
    @GetMapping
    public ResponseEntity<Page<ListPublishedEventResponseDTO>> listPublishedEvents(
            @RequestParam(required = false) String q,
            Pageable pageable){

        Page<Event> events;
        if(q != null && !q.trim().isEmpty()){
            events = eventService.searchPublishedEvents(q, pageable);
        } else {
            events = eventService.listPublishedEvents(pageable);
        }

        return ResponseEntity.ok(events.map(eventMapper::toListPublishedEventResponseDTO));
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<GetPublishedEventDetailsResponseDTO> getPublishedEventDetails(
            @PathVariable UUID eventId
    ){
        return eventService.getPublishedEvent(eventId)
                .map(eventMapper :: toGetPublishedEventDetailsResponseDTO)
                .map(ResponseEntity :: ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
