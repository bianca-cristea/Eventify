package com.cbianca.eventify.controllers;

import com.cbianca.eventify.dtos.CreateEventRequestDTO;
import com.cbianca.eventify.dtos.CreateEventResponseDTO;
import com.cbianca.eventify.dtos.GetEventDetailsResponseDTO;
import com.cbianca.eventify.dtos.ListEventResponseDTO;
import com.cbianca.eventify.entities.events.CreateEventRequest;
import com.cbianca.eventify.entities.events.Event;
import com.cbianca.eventify.mapper.EventMapper;
import com.cbianca.eventify.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDTO> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDTO createEventRequestDTO) {

        CreateEventRequest createEventRequest =
                eventMapper.fromDTO(createEventRequestDTO);

        UUID userId = parseUserId(jwt);

        Event eventCreated =
                eventService.createEvent(userId, createEventRequest);

        CreateEventResponseDTO responseDTO =
                eventMapper.toDTO(eventCreated);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @GetMapping
    public ResponseEntity<Page<ListEventResponseDTO>> listEvents(@AuthenticationPrincipal Jwt jwt, Pageable pageable){

        UUID userId = parseUserId(jwt);
        Page<Event> events = eventService.listEventsForOrganizer(userId,pageable);
        return ResponseEntity.ok(events.map(eventMapper::toListEventResponseDTO));
    }

    @GetMapping(path = "/{eventId}")
    public  ResponseEntity<GetEventDetailsResponseDTO> getEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId
    ) {

        UUID userId = parseUserId(jwt);
        return eventService.getEventForOrganizer(userId, eventId)
                .map(eventMapper::toGetEventDetailsResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound ().build());

    }

    private UUID parseUserId(Jwt jwt){
        return UUID.fromString(jwt.getSubject());
    }
}
